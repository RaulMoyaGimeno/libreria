package com.rmoya.libreria.util;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import com.rmoya.libreria.R;

public class Intents extends AppCompatActivity {
    public  void launchActivity(Context context, Class<?> clase, String mostrar) {
        Intent intent = new Intent(context, clase);
        Resources resources = context.getResources();
        intent.putExtra(resources.getString(R.string.mostrar), mostrar);
        context.startActivity(intent);
    }

    public  void launchActivity(Context context, Class<?> clase) {
        Intent intent = new Intent(context, clase);
        context.startActivity(intent);
    }
}
