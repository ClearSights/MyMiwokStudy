package com.github.clearsights.mymiwokstudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class NumbersActivity extends AppCompatActivity {

    private static String LOG_TAG = "NumbersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new NumbersFragment())
                .commit();
    }
}
