package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.DBInit;
import com.rmoya.libreria.controller.JsonDownloader;
import com.rmoya.libreria.controller.UserController;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new JsonDownloader().execute();
        DBInit.inicializarSqlite(this,R.raw.libreria);

        Class<?> clase = LoginActivity.class;

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        if(sp.contains("name")){
            clase = MainScreenActivity.class;
            UserController.userStatic = sp.getString("name","undefined");
        }

        Handler handler = new Handler(Looper.myLooper());

        Class<?> finalClase = clase;
        handler.postDelayed(() -> {
            Intent intent = new Intent(getApplicationContext(), finalClase);
            startActivity(intent);
            finish();
        }, 1500);
    }
}