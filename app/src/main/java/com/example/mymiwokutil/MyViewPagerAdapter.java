package com.example.mymiwokutil;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mymiwokstudy.ColorsFragment;
import com.example.mymiwokstudy.FamilyFragment;
import com.example.mymiwokstudy.NumbersFragment;
import com.example.mymiwokstudy.PhraseFragment;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    private int PAGE_NUM = 4;
    private String[] PAGE_TITLES = new String[]{"Numbers", "Family", "Colors", "Phrases"};

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new NumbersFragment();
            case 1:
                return new FamilyFragment();
            case 2:
                return new ColorsFragment();
            case 3:
                return new PhraseFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return PAGE_NUM;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return PAGE_TITLES[position];
    }
}
