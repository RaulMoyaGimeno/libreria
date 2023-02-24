package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.UserADO;
import com.rmoya.libreria.controller.UserController;
import com.rmoya.libreria.util.Alerts;
import com.rmoya.libreria.util.Encryptation;
import com.rmoya.libreria.util.Intents;

public class LoginActivity extends AppCompatActivity {
    EditText txtUserLogin;
    EditText txtPassLogin;
    Intents intents = new Intents();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUserLogin = findViewById(R.id.txtUserLogin);
        txtPassLogin = findViewById(R.id.txtPassLogin);
        TextView txtCrearUsuario = findViewById(R.id.txtCrearUsuario);
        txtCrearUsuario.setPaintFlags(txtCrearUsuario.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        txtCrearUsuario.setOnClickListener(v -> {
            intents.launchActivity(this, NewUserActivity.class);
        });

        Button btnAcceder = findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(v -> {
            if (UserADO.validateUser(this, txtUserLogin.getText().toString(), Encryptation.encrypt(txtPassLogin.getText().toString()))) {
                CheckBox checkRecordar = findViewById(R.id.checkRecordar);
                if (checkRecordar.isChecked()) {
                    sharePreferences();
                }
                UserController.userStatic = txtUserLogin.getText().toString();
                intents.launchActivity(this, MainScreenActivity.class);
                finish();
            } else {
                Alerts.launchDialogFields(this, getString(R.string.usuario_contrasena_mal), getString(R.string.cerrar));
            }
        });
    }

    private void sharePreferences() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", txtUserLogin.getText().toString());
        editor.apply();
    }
}