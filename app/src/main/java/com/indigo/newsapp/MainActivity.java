package com.indigo.newsapp;


import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.indigo.newsapp.adapter.ViewPagerAdapter;
import com.indigo.newsapp.fragment.FragmentNewsList;


public class MainActivity extends AppCompatActivity {
    private androidx.appcompat.widget.Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();
    }


    public void bind() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(FragmentNewsList.newInstance("Business"), "Business");
        adapter.addFragment(FragmentNewsList.newInstance("Entertainment"), "Entertainment");
        adapter.addFragment(FragmentNewsList.newInstance("Health"), "Health");
        adapter.addFragment(FragmentNewsList.newInstance("science"), "Science");
        adapter.addFragment(FragmentNewsList.newInstance("sports"), "Sports");
        adapter.addFragment(FragmentNewsList.newInstance("technology"), "Technology");
        viewPager.setAdapter(adapter);
    }


}
