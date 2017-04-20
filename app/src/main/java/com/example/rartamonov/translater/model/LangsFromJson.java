package com.example.rartamonov.translater.model;

import java.util.List;
import java.util.Map;

public class LangsFromJson { // Разбор JSON списка языков

    private List<String> dirs;
    private Map<String,String> langs;

    public LangsFromJson(List<String> dirs, Map<String, String> langs) {
        this.dirs = dirs;
        this.langs = langs;
    }

    public List<String> getDirs() {
        return dirs;
    }

    public Map<String, String> getLangs() {
        return langs;
    }

    public void setDirs(List<String> dirs) {
        this.dirs = dirs;
    }

    public void setLangs(Map<String, String> langs) {
        this.langs = langs;
    }
}
