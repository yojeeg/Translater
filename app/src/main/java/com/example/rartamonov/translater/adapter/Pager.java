package com.example.rartamonov.translater.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.example.rartamonov.translater.view.TabTools;
import com.example.rartamonov.translater.view.TabHistoryFavourite;
import com.example.rartamonov.translater.view.TabMain;

public class Pager extends FragmentStatePagerAdapter {

    private int tabCount;

    public Pager(FragmentManager fm, int tabCount){

        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return new TabMain();
            case 1:
                return new TabHistoryFavourite();
            case 2:
                return new TabTools();
            default:
                return null;
        }
    }

     @Override
     public int getCount(){
        return tabCount;
     }

}

