package com.example.rartamonov.translater;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

import com.example.rartamonov.translater.adapter.Pager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;


public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private  MainActivity context;

    private TabLayout tabLayout;
    @BindView(R.id.pager)
    ViewPager viewPager;

    @OnTouch(R.id.pager)
    public boolean onTouchPager() {
        return true; // no swap
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        ButterKnife.bind(this);

        // создадим нижний tablayout: главная, избранное, настройки
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);

        TabLayout.Tab tab1 = tabLayout.newTab();
        tab1.setIcon(R.drawable.ic_main);
        setColorTab(tab1, R.color.black);

        TabLayout.Tab tab2 = tabLayout.newTab();
        tab2.setIcon(R.drawable.ic_favorite);
        setColorTab(tab2, R.color.border);

        TabLayout.Tab tab3 = tabLayout.newTab();
        tab3.setIcon(R.drawable.ic_settings);
        setColorTab(tab3, R.color.border);

        tabLayout.addTab(tab1); // добавляем объявленные табы
        tabLayout.addTab(tab2);
        tabLayout.addTab(tab3);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        Pager adapter = new Pager(getSupportFragmentManager(), tabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(this);

        // подсвечиваем/гасим иконки в зависимости от выбранной, скрываем клавиатуру при нажатии
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                setColorTab(tab, R.color.black);
                hideKeyword();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                setColorTab(tab, R.color.border);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);
            }
        });

    }

    public void hideKeyword() { // убирает клавиатуру
        if (context.getCurrentFocus() != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }


    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    public void setColorTab(TabLayout.Tab tab, int color){
        Drawable drawable = tab.getIcon();
        if (drawable!=null){
        drawable.setColorFilter(ContextCompat.getColor(getApplicationContext(),color), PorterDuff.Mode.SRC_IN);
        }
    }

}

