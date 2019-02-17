package com.example.mymiwokstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textNumbers, textFamily, textColors, textPhrases;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI
        textNumbers = (TextView) findViewById(R.id.numbers);
        textFamily = (TextView) findViewById(R.id.family);
        textColors = (TextView) findViewById(R.id.colors);
        textPhrases = (TextView) findViewById(R.id.phrases);

        textNumbers.setOnClickListener(this);
        textFamily.setOnClickListener(this);
        textColors.setOnClickListener(this);
        textPhrases.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent activityIntent;
        switch (v.getId()) {
            case R.id.numbers:
                activityIntent = new Intent(this, NumbersActivity.class);
                startActivity(activityIntent);
                break;
            case R.id.family:
                activityIntent = new Intent(this, FamilyActivity.class);
                startActivity(activityIntent);
                break;
            case R.id.colors:
                activityIntent = new Intent(this, ColorsActivity.class);
                startActivity(activityIntent);
                break;
            case R.id.phrases:
                activityIntent = new Intent(this, PhraseActivity.class);
                startActivity(activityIntent);
                break;
            default:
        }
    }
}
