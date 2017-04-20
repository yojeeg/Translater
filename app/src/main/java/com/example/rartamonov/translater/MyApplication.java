package com.example.rartamonov.translater;

import android.app.Application;

import com.example.rartamonov.translater.controller.HttpClient;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application{

    @Override
    public final void onCreate(){
        super.onCreate();

        Realm.init(this); // инициализация Realm
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);


        // прогреваем singleton HttpClient
        HttpClient.getInstance();

    }

}
