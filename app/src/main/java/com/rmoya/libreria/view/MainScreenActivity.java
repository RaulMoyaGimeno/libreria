package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import com.rmoya.libreria.R;

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

        btnAbrirJson.setOnClickListener(v -> {
            launchActivity(JsonActivity.class, getString(R.string.json));
        });

        btnAbrirBbdd.setOnClickListener(v -> {
            launchActivity(BBDDActivity.class);
        });

        btnAbrirFavs.setOnClickListener(v -> {
            launchActivity(JsonActivity.class, getString(R.string.favs));
        });

        btnAbrirLikes.setOnClickListener(v -> {
            launchActivity(JsonActivity.class, getString(R.string.likes));
        });

        btnCerrarSesion.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            sharedPreferences.edit().clear().apply();
            launchActivity(LoginActivity.class);
            finish();
        });

    }

    public void launchActivity(Class clase, String mostrar) {
        Intent intent = new Intent(getApplicationContext(), clase);
        intent.putExtra(getString(R.string.mostrar), mostrar);
        startActivity(intent);
    }

    public void launchActivity(Class clase) {
        Intent intent = new Intent(getApplicationContext(), clase);
        startActivity(intent);
    }
}