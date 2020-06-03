package com.codinguniverse.jokeui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayJokeActivity extends AppCompatActivity {

    public static final String EXTRA_JOKE = "joke";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_JOKE)){
            TextView jokeView = findViewById(R.id.joke_view);
            jokeView.setText(intent.getStringExtra(EXTRA_JOKE));
        }
    }
}