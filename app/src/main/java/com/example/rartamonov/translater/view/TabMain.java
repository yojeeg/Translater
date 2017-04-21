package com.example.rartamonov.translater.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.ScrollingMovementMethod;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.rartamonov.translater.LanguagesList;
import com.example.rartamonov.translater.R;
import com.example.rartamonov.translater.helper.RealmWorkingWithTables;
import com.example.rartamonov.translater.realm.Directions;
import com.example.rartamonov.translater.realm.FavouriteHistory;
import com.example.rartamonov.translater.realm.Langs;
import com.example.rartamonov.translater.service.API;
import com.example.rartamonov.translater.model.DictFromJson;
import com.example.rartamonov.translater.model.LangsFromJson;
import com.example.rartamonov.translater.model.TranslateAndDictionaty;
import com.example.rartamonov.translater.model.TranslateFromJson;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import butterknife.OnFocusChange;
import butterknife.OnTextChanged;
import butterknife.OnTouch;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmResults;

import static android.content.Context.INPUT_METHOD_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class TabMain extends Fragment {

    @BindView(R.id.buttonFrom)
    Button buttonFrom;

    @OnClick(R.id.buttonFrom)
    public void onClickButtonFrom() {
        sendDataToActivity(labelLangFrom, 1);
    }

    @BindView(R.id.buttonTo)
    Button buttonTo;

    @OnClick(R.id.buttonTo)
    public void onClickButtonTo() {
        sendDataToActivity(labelLangTo, 2);
    }

    @BindView(R.id.switchLanguage)
    ImageButton buttonSwitch;

    @OnClick(R.id.switchLanguage)
    public void onClickSwitchShowDict() {
        switchLanguage();
    }

    @BindView(R.id.addToFav)
    ToggleButton addToFav;

    @OnClick(R.id.addToFav)
    public void onClickAddToFav() { // добавляем/убираем слово в Избранное
        String text = editText.getText().toString().trim();
        String direct = langFromCode.toUpperCase() + "-" + langToCode.toUpperCase();
        if (!(translatedText.isEmpty() && text.isEmpty())) {
            if (addToFav.isChecked()) {
                RealmWorkingWithTables.addToFavouriteOrHistory(realm, text, translatedText, direct, true, true);
            } else {
                RealmWorkingWithTables.addToFavouriteOrHistory(realm, text, translatedText, direct, false, true);
            }
        }
    }

    @BindView(R.id.textView)
    TextView textView;

    @OnTouch(R.id.textView)
    public boolean onTouchTextView() {
        // уберем клавиатуру
        InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
        View currentFocus = getActivity().getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }

        return false;
    }

    @BindView(R.id.editText2)
    EditText editText;

    @OnTextChanged(R.id.editText2)
    public void onTextChangedEditText() { // запускает перевод слова и вывод статьи или чистит textView
        addToFav.setChecked(false);

        if (editText.getText().toString().isEmpty()) {
            textView.setText("");
            toolsPanel.setVisibility(View.INVISIBLE);
        } else {
            if (!isUseReturn()) {
                getTranslate(); // получение перевода
                toolsPanel.setVisibility(View.VISIBLE);
            }
        }
    }

    @OnEditorAction(R.id.editText2)
    public boolean onEditorAction(int actionId) {
        if ((actionId == EditorInfo.IME_ACTION_DONE)
                &&isUseReturn()
                &&!editText.getText().toString().isEmpty()) {
            getTranslate(); // получение перевода
            toolsPanel.setVisibility(View.VISIBLE);
            return true;
        }
        return false;
    }

    @OnFocusChange(R.id.editText2)
    public void onFocusChange(boolean hasFocus) { // на потерю фокуса пишем а историю и подменяем рамку на серую
        if (!hasFocus) {
            editText.setBackground(context.getDrawable(R.drawable.ic_square));
            String textFrom = editText.getText().toString().trim();
            if (!textFrom.isEmpty()) {
                RealmWorkingWithTables.addToFavouriteOrHistory(realm, textFrom.trim(), translatedText, langFromCode.toUpperCase() + "-" + langToCode.toUpperCase(), isExistInFav(translatedText, textFrom.trim()), true);
            }
        } else {
            editText.setBackground(context.getDrawable(R.drawable.ic_square_yellow));
            ifExistInFavThenChecked(translatedText.trim(), editText.getText().toString().trim());
        }

    }

    private Integer intCurrentApiVersion = Build.VERSION.SDK_INT;

    private Realm realm;

    private Context context;

    Map<String, String> listOfLangs = new TreeMap<>(); // языки уже сразу же будут упорядочены по возрастанию
    ArrayList<String> listOfDirections = new ArrayList<>();
    private static String langFromCode;
    private static String langToCode;

    public static final String myPrefs = "myprefs";
    public static final String keyShowDict = "showDict";
    public static final String keyUseReturn = "useReturn";
    private static final String keyLangFrom = "lnagFrom";
    private static final String keyLangTo = "langTo";
    private SharedPreferences sharedPreferences;

    private String urlTranslate;
    private String urlDict;
    private String API_TRANSLATE;
    private String API_DICTIONARY;

    private String translatedText = "";

    private String labelLangFrom;
    private String labelLangTo;

    private ConstraintLayout toolsPanel;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        context = getContext();

        API_TRANSLATE = getResources().getString(R.string.API_key_trans);
        API_DICTIONARY = getResources().getString(R.string.API_key_dict);

        realm = Realm.getDefaultInstance();

        sharedPreferences = context.getSharedPreferences(myPrefs, MODE_PRIVATE);

        String defaultLangFrom = getResources().getString(R.string.default_lang_from);
        String defaultLangTo = getResources().getString(R.string.default_lang_to);

        if (sharedPreferences.contains(keyLangFrom)) {
            langFromCode = sharedPreferences.getString(keyLangFrom, defaultLangFrom);
        } else {
            langFromCode = defaultLangFrom;
            editSharedPreferencesString(keyLangFrom, langFromCode);
        }

        if (sharedPreferences.contains(keyLangTo)) {
            langToCode = sharedPreferences.getString(keyLangTo, defaultLangTo);
        } else {
            langToCode = defaultLangTo;
            editSharedPreferencesString(keyLangTo, langToCode);
        }

        // Загрузим языки, если это необходимо
        loadLangs();
        // Проверим и запишем направления перевода
        loadDirections();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frg_main, container, false);

        ButterKnife.bind(this, view);

        urlTranslate = getResources().getString(R.string.url_translate);
        urlDict = getResources().getString(R.string.url_dict);

        labelLangFrom = getResources().getString(R.string.label_lang_from);
        labelLangTo = getResources().getString(R.string.label_lang_to);

        toolsPanel = (ConstraintLayout) view.findViewById(R.id.tools);

        textView.setMovementMethod(new ScrollingMovementMethod());

        String textToButtonFrom = ValueOfCodeLang(langFromCode);
        String textToButtonTo = ValueOfCodeLang(langToCode);
        if (textToButtonFrom.isEmpty()) {
            buttonFrom.setText(getResources().getString(R.string.default_lang_from_value));
        } else buttonFrom.setText(textToButtonFrom);
        if (textToButtonTo.isEmpty()) {
            buttonTo.setText(getResources().getString(R.string.default_lang_to_value));
        } else {
            buttonTo.setText(textToButtonTo);
        }

        editText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_ENTER)&&isUseReturn()&&!editText.getText().toString().isEmpty()) {
                    getTranslate(); // получение перевода
                    toolsPanel.setVisibility(View.VISIBLE);
                    return true;
                }
                return false;
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;

        String res = data.getStringExtra("lang");
        if (requestCode == 1) { // язык ввода
            langFromCode = changeLanguage(buttonTo, buttonFrom, res, langFromCode);
            editSharedPreferencesString(keyLangFrom, langFromCode); // пишем в настройки

            if (editText.getText().toString().isEmpty()) {
                textView.setText("");
                toolsPanel.setVisibility(View.INVISIBLE);
            } else {
                toolsPanel.setVisibility(View.VISIBLE);
                getTranslate();
            }
        } else if (requestCode == 2) { // язык перевода
            langToCode = changeLanguage(buttonFrom, buttonTo, res, langToCode);
            editSharedPreferencesString(keyLangTo, langToCode);

            if (editText.getText().toString().isEmpty()) {
                textView.setText("");
                toolsPanel.setVisibility(View.INVISIBLE);
            } else {
                toolsPanel.setVisibility(View.VISIBLE);
                getTranslate();
            }
        }
    }

    // если выбрали такой же язык, как и смежный, то просто меняем языки
    public boolean makeCheckForSameLanguage(String res, Button button) {
        if (res.toLowerCase().equals(button.getText().toString().toLowerCase())) {
            switchLanguage();
            return true;
        }
        return false;
    }

    // метод на смену языка
    public String changeLanguage(Button button1, Button button2, String res, String lang) {
        if (makeCheckForSameLanguage(res, button1)) return lang;

        button2.setText(res);
        return listOfLangs.get(res);
    }

    public void switchLanguage() {
        String textButtonFrom = buttonFrom.getText().toString();
        String saveLangFrom = langFromCode;
        buttonFrom.setText(buttonTo.getText());
        langFromCode = langToCode;
        editSharedPreferencesString(keyLangFrom, langFromCode);
        buttonTo.setText(textButtonFrom);
        langToCode = saveLangFrom;
        editSharedPreferencesString(keyLangTo, langToCode);
        editText.setText(translatedText);
    }

    // отправка данных в активити выбора языка
    public void sendDataToActivity(String label, int requestCode) {
        Intent intent = new Intent(context, LanguagesList.class);
        intent.putExtra("languageSwitcher", label);
        intent.putStringArrayListExtra("list", new ArrayList(listOfLangs.keySet()));
        startActivityForResult(intent, requestCode);
    }

    public void getTranslate() {
        loadJson_translate(editText.getText().toString(), langFromCode + "-" + langToCode);
    }

    private void loadJson_translate(String text, String lang) {

        if (isShowDict() && isHaveDirect()) { // если глобальная настройка Показывать словарь включена и имется такое направление перевода
            getTranslateAndDict(text, lang);
        } else { // если словарь не выводим, то просто выведем перевод
            getOnlyTranslate(text, lang);
        }

    }

    public void loadDataToTextView(TranslateAndDictionaty translateAndDictionaty) {
        String textFrom = editText.getText().toString();
        translatedText = translateAndDictionaty.getTranslateFromJson().getText();
        String translatedTextToView = "<big>" + translatedText + "</big><br></br><br></br>";
        // в зависимости от версии sdk разные реализации fromHtml
        StringBuffer dictFromJson = translateAndDictionaty.getDictFromJson().getText(translateAndDictionaty.getDictFromJson());
        Spanned textHtml = intCurrentApiVersion >= 24 ? Html.fromHtml("<big>" + translatedTextToView + dictFromJson.toString() + "</big>", Html.FROM_HTML_MODE_COMPACT) : Html.fromHtml("<big>" + translatedTextToView + dictFromJson.toString() + "</big>");
        textView.setText(textHtml);
        ifExistInFavThenChecked(translatedText.trim(), textFrom.trim());
    }

    public void loadDataToTextView(TranslateFromJson translateFromJson) {
        String textFrom = editText.getText().toString();
        translatedText = translateFromJson.getText();
        Spanned textHtml = intCurrentApiVersion >= 24 ? Html.fromHtml("<big>" + translatedText + "</big>", Html.FROM_HTML_MODE_COMPACT) : Html.fromHtml("<big>" + translatedText + "</big>");
        textView.setText(textHtml);
        ifExistInFavThenChecked(translatedText.trim(), textFrom.trim()); // проверка на то, что слово есть в списке избранного
    }

    public void loadDataToTextView(String text) {
        textView.setText(text);
    }

    public void getOnlyTranslate(String text, String lang) {
        API.getTranslate().getParametresTranslate(API_TRANSLATE, text, lang)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<TranslateFromJson>() {
                    @Override
                    public void onNext(TranslateFromJson translateFromJson) {
                        loadDataToTextView(translateFromJson);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getTranslateAndDict(String text, String lang) {
        Observable.zip(
                API.getTranslate().getParametresTranslate(API_TRANSLATE, text, lang),
                API.getDictionary().getParametresDict(API_DICTIONARY, text, lang, "ru", 2), // ui=ru - Язык интерфейса пользователя, на котором будут отображаться названия частей речи в словарной статье; SHORT_POS = 0x0002 - отображать названия частей речи в краткой форме;
                new BiFunction<TranslateFromJson, DictFromJson, TranslateAndDictionaty>() {
                    @Override
                    public TranslateAndDictionaty apply(@NonNull TranslateFromJson translateFromJson, @NonNull DictFromJson dictFromJson) throws Exception {
                        return new TranslateAndDictionaty(translateFromJson, dictFromJson);
                    }
                }
        )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TranslateAndDictionaty>() {
                    @Override
                    public void accept(@NonNull TranslateAndDictionaty translateAndDictionaty) throws Exception {
                        loadDataToTextView(translateAndDictionaty);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        loadDataToTextView("Данное направление перевода не поддерживается"); // обработать ошибку Bad request 400 пока не удается
                    }
                });
    }

    public void ifExistInFavThenChecked(String translate, String word) {
        RealmResults<FavouriteHistory> res = realm.where(FavouriteHistory.class).equalTo("wordFrom", word).equalTo("favourite", true).findAll();
        if (!res.isEmpty()) {
            for (int i = 0; i < res.size(); i++) {
                FavouriteHistory row = res.get(i);
                if (row.getWordFrom().equals(word) && row.getWordTo().equals(translate)) {
                    addToFav.setChecked(true);
                }
            }
        }
    }

    public Boolean isExistInFav(String translate, String word) {
        RealmResults<FavouriteHistory> res = realm.where(FavouriteHistory.class).equalTo("wordFrom", word).equalTo("favourite", true).findAll();
        if (!res.isEmpty()) {
            for (int i = 0; i < res.size(); i++) {
                FavouriteHistory row = res.get(i);
                if (row.getWordFrom().equals(word) && row.getWordTo().equals(translate)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean isHaveDirect() {
        return listOfDirections.contains(langFromCode + "-" + langToCode);
    }

    public Boolean isShowDict() {
        sharedPreferences = context.getSharedPreferences(myPrefs, MODE_PRIVATE);
        if (sharedPreferences.contains(keyShowDict)) {
            return sharedPreferences.getBoolean(keyShowDict, true);
        }
        return true;
    }

    public Boolean isUseReturn() {
        sharedPreferences = context.getSharedPreferences(myPrefs, MODE_PRIVATE);
        if (sharedPreferences.contains(keyUseReturn)) {
            return sharedPreferences.getBoolean(keyUseReturn, false);
        }
        return false;
    }

    public void loadLangs() {
        RealmResults<Langs> langsRealm = realm.where(Langs.class).findAll();

        if (langsRealm.isEmpty()) {

            API.getTranslate().getParametresLang(API_TRANSLATE, "ru")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<LangsFromJson>() {
                        @Override
                        public void onNext(final LangsFromJson langsFromJson) {

                            for (Map.Entry<String, String> entry : langsFromJson.getLangs().entrySet()) {
                                listOfLangs.put(entry.getValue(), entry.getKey());
                            }
                            realm.executeTransaction(new Realm.Transaction() {
                                @Override
                                public void execute(Realm realm) {
                                    for (Map.Entry<String, String> entry : listOfLangs.entrySet()) {
                                        Langs langs = new Langs();
                                        langs.setCode(entry.getValue());
                                        langs.setDefinition(entry.getKey());
                                        realm.insertOrUpdate(langs);
                                    }

                                }
                            });

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });

        } else {

            for (int i = 0; i < langsRealm.size(); i++) {
                listOfLangs.put(langsRealm.get(i).getDefinition(), langsRealm.get(i).getCode());
            }
        }
    }

    public void loadDirections() {
        RealmResults<Directions> directions = realm.where(Directions.class).findAll();
        if (directions.size() == 0) {

            API.getTranslate().getParametresLang(API_TRANSLATE, "ru")
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableObserver<LangsFromJson>() {
                        @Override
                        public void onNext(LangsFromJson langsFromJson) {
                            realm.beginTransaction();
                            for (String row : langsFromJson.getDirs()) {
                                Directions directions = new Directions();
                                directions.setFrom(row.substring(0, row.indexOf("-")));
                                directions.setTo(row.substring(row.indexOf("-") + 1));
                                realm.insertOrUpdate(directions);
                                listOfDirections.add(directions.getFrom() + "-" + directions.getTo());
                            }
                            realm.commitTransaction();
                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        } else {
            for (int i = 0; i < directions.size(); i++) {
                Directions row = directions.get(i);
                listOfDirections.add(row.getFrom() + "-" + row.getTo());
            }
        }
    }

    public String ValueOfCodeLang(String code) { // название языка по его коду

        for (Map.Entry<String, String> entry : listOfLangs.entrySet()) {
            if (entry.getValue().equals(code)) {
                return entry.getKey();
            }
        }

        return "";
    }

    private void editSharedPreferencesString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}

