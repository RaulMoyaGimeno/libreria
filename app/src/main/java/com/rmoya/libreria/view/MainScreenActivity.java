package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;

import com.rmoya.libreria.R;

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
            launchActivity(JsonActivity.class);
        });

        btnAbrirBbdd.setOnClickListener(v -> {
            launchActivity(BBDDActivity.class);
        });


        btnAbrirFavs.setOnClickListener(v -> {
            launchActivity(FavsActivity.class);
        });
        
        btnAbrirLikes.setOnClickListener(v -> {
            launchActivity(LikesActivity.class);
        });

        btnCerrarSesion.setOnClickListener(v -> {
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            sharedPreferences.edit().clear().apply();
            launchActivity(LoginActivity.class);
            finish();
        });

    }

    public void launchActivity(Class clase) {
        Intent intent = new Intent(getApplicationContext(), clase);
        startActivity(intent);
    }
}