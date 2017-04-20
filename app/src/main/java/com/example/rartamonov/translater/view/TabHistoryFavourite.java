package com.example.rartamonov.translater.view;

import android.content.DialogInterface;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.rartamonov.translater.R;
import com.example.rartamonov.translater.adapter.RecyclerViewDataAdapter;
import com.example.rartamonov.translater.realm.FavouriteHistory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import io.realm.Case;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class TabHistoryFavourite extends Fragment { // Фрагмент для отображения настроек

    @BindView(R.id.delete)
    ImageButton deleteHistory;

    @OnClick(R.id.delete)
    public void onClickDeleteHistory() {
        ad.show(); // показываем allert dialog
    }

    @BindView(R.id.findHistory)
    EditText findHistory;

    @OnTextChanged(R.id.findHistory)
    public void onTextChangedFindHistory() {
        String textFind = findHistory.getText().toString().trim();
        if (!textFind.isEmpty()) {
            if (tabFav.isSelected()) {
                setResultWhithFind(colomnFav, textFind); // результат с отбором по поиску
            } else {
                setResultWhithFind(colomnHist, textFind);
            }
        } else {
            if (tabFav.isSelected()) {
                setResult(colomnFav);
            } else {
                setResult(colomnHist);
            }
        }

    }

    @BindView(R.id.tabLayout_fav)
    TabLayout tabLayoutFav;

    private TabLayout.Tab tabFav;
    private TabLayout.Tab tabHist;
    private RecyclerView recycler_his_fav;
    private RecyclerViewDataAdapter mAdapter;
    private RealmResults<FavouriteHistory> mResults;
    private AlertDialog.Builder ad;
    private String messageDialog;
    private String colomnFav;
    private String colomnHist;
    private String msgCleanFav;
    private String msgCleanHist;
    private String columnWordForm;
    private String msgYes;
    private String msgNo;

    private Realm realm;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        realm = Realm.getDefaultInstance();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_history_favourite, container, false);
        ButterKnife.bind(this, view);

        colomnFav = getResources().getString(R.string.column_fav);
        colomnHist = getResources().getString(R.string.column_hist);
        msgCleanFav = getResources().getString(R.string.clean_fav);
        msgCleanHist = getResources().getString(R.string.clean_hist);
        columnWordForm = getResources().getString(R.string.column_word_from);
        msgYes = getResources().getString(R.string.msgYes);
        msgNo = getResources().getString(R.string.msgNo);

        // при отображении грузим в RecyclerView данные по истории из Realm
        mResults = realm.where(FavouriteHistory.class).equalTo(colomnHist, true).findAllSortedAsync(columnWordForm);
        mResults.addChangeListener(new RealmChangeListener<RealmResults<FavouriteHistory>>() {
            @Override
            public void onChange(RealmResults<FavouriteHistory> element) {
                mAdapter.notifyDataSetChanged();
            }
        });

        tabFav = tabLayoutFav.newTab().setText(R.string.favourite);
        tabHist = tabLayoutFav.newTab().setText(R.string.history);
        tabLayoutFav.addTab(tabHist);
        tabLayoutFav.addTab(tabFav);

        recycler_his_fav = (RecyclerView) view.findViewById(R.id.view_history_favourite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycler_his_fav.setLayoutManager(linearLayoutManager);
        mAdapter = new RecyclerViewDataAdapter(inflater, realm, mResults);
        mAdapter.setHasStableIds(true);
        recycler_his_fav.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        if (tabFav.isSelected()) {
            messageDialog = msgCleanFav; // в зависимости от выбранного таба меняем сообщение для вывода диалога
        } else {
            messageDialog = msgCleanHist;
        }
        ad = new AlertDialog.Builder(getContext());
        ad.setMessage(messageDialog);
        ad.setPositiveButton(msgYes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (tabHist.isSelected()) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<FavouriteHistory> rowHistory = realm.where(FavouriteHistory.class).equalTo(colomnHist, true).findAll();
                            for (int i = rowHistory.size() - 1; i >= 0; i--) {
                                FavouriteHistory row = rowHistory.get(i);
                                row.setHistory(false);
                                realm.insertOrUpdate(row);
                            }
                            clearAllUnchecked();
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                } else if (tabFav.isSelected()) {
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            RealmResults<FavouriteHistory> rowFavourite = realm.where(FavouriteHistory.class).equalTo(colomnFav, true).findAll();
                            for (int i = rowFavourite.size() - 1; i >= 0; i--) {
                                FavouriteHistory row = rowFavourite.get(i);
                                row.setFavourite(false);
                                realm.insertOrUpdate(row);
                            }
                            clearAllUnchecked();
                        }
                    });
                    mAdapter.notifyDataSetChanged();
                }
            }
        });
        ad.setNegativeButton(msgNo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        ad.setCancelable(false);

        // в зависимости от выбранного Tab будем менять список из Realm Favourite с отбором по флагам
        tabLayoutFav.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.equals(tabFav)) {
                    setResult(colomnFav);
                    findHistory.setHint(getResources().getString(R.string.fav_hint));
                    messageDialog = msgCleanFav;
                    ad.setMessage(messageDialog);
                } else {
                    setResult(colomnHist);
                    findHistory.setHint(getResources().getString(R.string.hist_hint));
                    messageDialog = msgCleanHist;
                    ad.setMessage(messageDialog);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;

    }

    public void setResult(String column) {
        mResults = realm.where(FavouriteHistory.class).equalTo(column, true).findAllSorted(columnWordForm);
        mAdapter.setResults(mResults);
    }

    public void setResultWhithFind(String column, String textFind) {
        mResults = realm.where(FavouriteHistory.class).equalTo(column, true).contains(columnWordForm, textFind, Case.INSENSITIVE).findAllSorted(columnWordForm);
        mAdapter.setResults(mResults);
    }

    public void clearAllUnchecked() {
        realm.where(FavouriteHistory.class).equalTo(colomnFav, false).equalTo(colomnHist, false).findAll().deleteAllFromRealm();
    }
}

