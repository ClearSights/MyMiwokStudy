package com.github.clearsights.mymiwokstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PhraseActivity extends AppCompatActivity {

    private static String LOG_TAG = "PhraseActivity";

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
                .replace(R.id.container, new PhraseFragment())
                .commit();
    }
}