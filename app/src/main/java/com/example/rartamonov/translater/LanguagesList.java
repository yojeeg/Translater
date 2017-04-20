package com.example.rartamonov.translater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LanguagesList extends AppCompatActivity { // Activity для вывода списка языков

    @BindView(R.id.close_activity)
    ImageButton closeActivity;

    @OnClick(R.id.close_activity)
    public void onClickCloseActivity(View v) {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.languagelist);
        ButterKnife.bind(this);

        final String textToSwitchLang = getIntent().getStringExtra("languageSwitcher");
        final ArrayList<String> listOfLangs = getIntent().getStringArrayListExtra("list");

        final ListView listView = (ListView) findViewById(R.id.listView);
        final TextView textView = (TextView) findViewById(R.id.languageSwitcher);
        textView.setText(textToSwitchLang);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemClicked = ((TextView) view).getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("lang", itemClicked);
                setResult(RESULT_OK, intent); // Отправляем результат обратно в TabMain
                finish();
            }
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfLangs);
        listView.setAdapter(adapter);

    }
}
