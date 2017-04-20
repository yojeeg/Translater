package com.example.rartamonov.translater.realm;


import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Directions extends RealmObject{ // Таблица Realm, здесь хранятся возможные направления переводов (заполняется из API)

    @Required
    private String from;
    @Required
    private String to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
