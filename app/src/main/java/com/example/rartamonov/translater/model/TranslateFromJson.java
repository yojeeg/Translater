package com.example.rartamonov.translater.model;

public class TranslateFromJson { // разбор JSON перевода
    private Integer code;
    private String lang;
    private String[] text;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getText() {
        return this.text.length>0?this.text[0]:"";
    }

    public void setText(String[] text) {
        this.text = text;
    }
}
