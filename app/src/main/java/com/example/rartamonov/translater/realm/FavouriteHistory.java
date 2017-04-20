package com.example.rartamonov.translater.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FavouriteHistory extends RealmObject { // Таблица для Realm. Тут хранится Избранное и История.

    @PrimaryKey
    private String wordFrom;
    private String wordTo;
    private String direct;
    private Boolean history; // отображаем в истории
    private Boolean favourite; // отображаем в избранном

    public String getWordFrom() {
        return wordFrom;
    }

    public void setWordFrom(String wordFrom) {
        this.wordFrom = wordFrom;
    }

    public String getWordTo() {
        return wordTo;
    }

    public void setWordTo(String wordTo) {
        this.wordTo = wordTo;
    }

    public String getDirect() {
        return direct;
    }

    public void setDirect(String direct) {
        this.direct = direct;
    }

    public Boolean getHistory() {
        return history;
    }

    public void setHistory(Boolean history) {
        this.history = history;
    }

    public Boolean getFavourite() {
        return favourite;
    }

    public void setFavourite(Boolean favourite) {
        this.favourite = favourite;
    }
}
