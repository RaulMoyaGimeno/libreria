package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.UserADO;
import com.rmoya.libreria.model.User;
import com.rmoya.libreria.util.Alerts;
import com.rmoya.libreria.util.Encryptation;

public class NewUserActivity extends AppCompatActivity {
EditText txtNombreRegistro;
EditText txtUserRegistro;
EditText txtPass1Registro;
EditText txtPass2Registro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        txtNombreRegistro = findViewById(R.id.txtNombreRegistro);
        txtUserRegistro = findViewById(R.id.txtUserRegistro);
        txtPass1Registro = findViewById(R.id.txtPass1Registro);
        txtPass2Registro = findViewById(R.id.txtPass2Registro);


        Button btnRegistrar = findViewById(R.id.btnRegistrar);

        btnRegistrar.setOnClickListener(v->{
           if(UserADO.insertUser(this, new User(txtNombreRegistro.getText().toString(),txtUserRegistro.getText().toString(), Encryptation.encrypt(txtPass1Registro.getText().toString())))){
               Alerts.launchDialogFields(this, "Registro ok","Cerrar");
           } else{
               Alerts.launchDialogFields(this, "Registro mal","Cerrar");
           }
        });
    }
}