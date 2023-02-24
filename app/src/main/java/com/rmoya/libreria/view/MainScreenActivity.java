package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import com.rmoya.libreria.R;
import com.rmoya.libreria.util.Intents;

import java.io.Serializable;

public class MainScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        Button btnAbrirJson = findViewById(R.id.btnAbrirJson);
        Button btnAbrirBbdd = findViewById(R.id.btnAbrirBbdd);
        Button btnAbrirFavs = findViewById(R.id.btnAbrirFavs);
        Button btnAbrirLikes = findViewById(R.id.btnAbrirLikes);
        Button btnCerrarSesion = findViewById(R.id.btnCerrarSesion);

        Intents intents = new Intents();

        btnAbrirJson.setOnClickListener(v -> {
            intents.launchActivity(this,JsonActivity.class, getString(R.string.json));
        });

        btnAbrirBbdd.setOnClickListener(v -> {
            intents.launchActivity(this, BBDDActivity.class);
        });

        btnAbrirFavs.setOnClickListener(v -> {
            intents.launchActivity(this, JsonActivity.class, getString(R.string.favs));
        });

        btnAbrirLikes.setOnClickListener(v -> {
            intents.launchActivity(this, JsonActivity.class, getString(R.string.likes));
        });

        btnCerrarSesion.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            sharedPreferences.edit().clear().apply();
            intents.launchActivity(this, LoginActivity.class);
            finish();
        });

    }


}