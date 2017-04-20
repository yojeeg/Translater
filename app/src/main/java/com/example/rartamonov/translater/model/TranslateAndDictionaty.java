package com.example.rartamonov.translater.model;


public class TranslateAndDictionaty { // вспомогательный класс для объединения результатов выполнения запросов к получения перевода и словарной статьи
    private TranslateFromJson translateFromJson;
    private DictFromJson dictFromJson;

    public TranslateAndDictionaty(TranslateFromJson translateFromJson, DictFromJson dictFromJson) {
        this.translateFromJson = translateFromJson;
        this.dictFromJson = dictFromJson;
    }

    public TranslateFromJson getTranslateFromJson() {
        return translateFromJson;
    }

    public DictFromJson getDictFromJson() {
        return dictFromJson;
    }
}
