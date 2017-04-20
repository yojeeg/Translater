package com.example.rartamonov.translater.service;


import com.example.rartamonov.translater.controller.HttpClient;
import com.example.rartamonov.translater.model.DictFromJson;
import com.example.rartamonov.translater.model.LangsFromJson;
import com.example.rartamonov.translater.model.TranslateFromJson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Observable;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class API { // класс для работы с API yandex. Используются библиотеки retrofit2+reactivex.

    public interface YandexService {

        @POST("translate")
        Observable<TranslateFromJson> getParametresTranslate(@Query("key") String key, @Query("text") String text, @Query("lang") String lang);

        @POST("getLangs")
        Observable<LangsFromJson> getParametresLang(@Query("key") String key, @Query("ui") String param);

        @POST("lookup")
        Observable<DictFromJson> getParametresDict(@Query("key") String key, @Query("text") String text, @Query("lang") String lang, @Query("ui") String ui, @Query("flags") Integer flags);
    }

    public static YandexService getTranslate(){
        return getRetrofit("https://translate.yandex.net/api/v1.5/tr.json/").create(YandexService.class);
    }

    public static YandexService getDictionary(){
        return getRetrofit("https://dictionary.yandex.net/api/v1/dicservice.json/").create(YandexService.class);
    }

    private static Retrofit getRetrofit(String BaseURL){
        return new Retrofit.Builder()
                .client(HttpClient.getInstance())
                .baseUrl(BaseURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

}
