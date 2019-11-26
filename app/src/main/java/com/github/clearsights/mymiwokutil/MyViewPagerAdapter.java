package com.github.clearsights.mymiwokutil;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.github.clearsights.mymiwokstudy.ColorsFragment;
import com.github.clearsights.mymiwokstudy.FamilyFragment;
import com.github.clearsights.mymiwokstudy.NumbersFragment;
import com.github.clearsights.mymiwokstudy.PhraseFragment;

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
