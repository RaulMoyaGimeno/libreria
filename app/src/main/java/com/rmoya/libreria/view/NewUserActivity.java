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
        btnRegistrar.setOnClickListener(v -> {
            compruebaCampos();
        });
    }

    private void compruebaCampos() {
        if (compruebaTxtVacio(txtNombreRegistro)) {
            Alerts.launchDialogFields(this, getString(R.string.nombre_no_introducido), getString(R.string.cerrar));
        } else if (compruebaTxtVacio(txtUserRegistro)) {
            Alerts.launchDialogFields(this, getString(R.string.user_no_introducido), getString(R.string.cerrar));
        } else if (UserADO.existUser(this, txtUserRegistro.getText().toString())) {
            Alerts.launchDialogFields(this, getString(R.string.user_existente), getString(R.string.cerrar));
        } else if (compruebaTxtVacio(txtPass1Registro)) {
            Alerts.launchDialogFields(this,getString(R.string.pass_no_introducido), getString(R.string.cerrar));
        } else if (!comprobarContraseñas()) {
            Alerts.launchDialogFields(this, getString(R.string.pass_no_coinciden), getString(R.string.cerrar));
        } else {
            insertarUsuario();
            borrarCampos();
        }
    }

    private void insertarUsuario() {
        if (UserADO.insertUser(this, new User(txtNombreRegistro.getText().toString(), txtUserRegistro.getText().toString(), Encryptation.encrypt(txtPass1Registro.getText().toString())))) {
            Alerts.launchDialogFields(this, getString(R.string.user_registrado), getString(R.string.cerrar));
        } else {
            Alerts.launchDialogFields(this, getString(R.string.user_no_registrado), getString(R.string.cerrar));
        }
    }

    private boolean compruebaTxtVacio(EditText txt) {
        return "".equals(txt.getText().toString().trim());
    }

    private boolean comprobarContraseñas() {
        return txtPass1Registro.getText().toString().equals(txtPass2Registro.getText().toString());
    }

    private void borrarCampos() {
        txtPass1Registro.setText("");
        txtUserRegistro.setText("");
        txtPass2Registro.setText("");
        txtNombreRegistro.setText("");
    }
}