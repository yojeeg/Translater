package com.example.rartamonov.translater.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.rartamonov.translater.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.MODE_PRIVATE;

public class TabTools extends Fragment {

    private SharedPreferences sharedPreferences;
    public static final String myPrefs = "myprefs";
    public static final String keyShowDict = "showDict";

    private Context context;

    @BindView(R.id.switch_show_dict) SwitchCompat switchShowDict;
    @OnClick(R.id.switch_show_dict)
    public void onClickSwitchShowDict() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(keyShowDict, switchShowDict.isChecked());
        editor.apply();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        context = getContext();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frg_tools, container, false);
        ButterKnife.bind(this, view);

        sharedPreferences = context.getSharedPreferences(myPrefs, MODE_PRIVATE);
        if (sharedPreferences.contains(keyShowDict)) {
            switchShowDict.setChecked(sharedPreferences.getBoolean(keyShowDict, true));
        } else
        {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean(keyShowDict, switchShowDict.isChecked());
            editor.apply();
        }

        return view;
    }
}
