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

public class LoginActivity extends AppCompatActivity {
EditText txtUserLogin;
EditText txtPassLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUserLogin = findViewById(R.id.txtUserLogin);
        txtPassLogin = findViewById(R.id.txtPassLogin);
        TextView txtCrearUsuario = findViewById(R.id.txtCrearUsuario);
        txtCrearUsuario.setPaintFlags(txtCrearUsuario.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        txtCrearUsuario.setOnClickListener(v->{
            Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
            startActivity(intent);
        });

        Button btnAcceder = findViewById(R.id.btnAcceder);
        btnAcceder.setOnClickListener(v->{
            if(UserADO.validateUser(this, txtUserLogin.getText().toString(), Encryptation.encrypt(txtPassLogin.getText().toString()))){
                CheckBox ch = findViewById(R.id.checkRecordar);
                if(ch.isChecked()){
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("name",txtUserLogin.getText().toString());
                    editor.apply();
                }
                UserController.userStatic = txtUserLogin.getText().toString();
                Intent intent = new Intent(getApplicationContext(), MainScreenActivity.class);
                startActivity(intent);
                finish();
            } else{
                Alerts.launchDialogFields(this,"Usuario o contraseña mal","OK");
            }
        });
    }
}