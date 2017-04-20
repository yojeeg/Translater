package com.example.rartamonov.translater.realm;

import io.realm.RealmObject;
import io.realm.annotations.Required;

public class Langs extends RealmObject { // Таблица для Realm. Тут хранится список языков и их кодов (из API)

    @Required
    private String code;
    @Required
    private  String definition;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }
}
