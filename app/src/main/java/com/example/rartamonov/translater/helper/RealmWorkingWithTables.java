package com.example.rartamonov.translater.helper;

import com.example.rartamonov.translater.realm.FavouriteHistory;
import io.realm.Realm;

public  class RealmWorkingWithTables { // поиощник для работы с таблицами Realm

    public static void addToFavouriteOrHistory(Realm realm, String from, String to, String direction, Boolean fav, Boolean history){
        realm.beginTransaction();

        FavouriteHistory favourite = new FavouriteHistory();
        favourite.setWordFrom(from.trim());
        favourite.setWordTo(to.trim());
        favourite.setDirect(direction);
        favourite.setFavourite(fav);
        favourite.setHistory(history);
        realm.insertOrUpdate(favourite);
        realm.commitTransaction();
    }
}
