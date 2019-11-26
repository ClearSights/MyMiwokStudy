package com.github.clearsights.mymiwokstudy;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.clearsights.mymiwokutil.MyViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        MyViewPagerAdapter myViewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());

        // setup
        mViewPager.setAdapter(myViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
