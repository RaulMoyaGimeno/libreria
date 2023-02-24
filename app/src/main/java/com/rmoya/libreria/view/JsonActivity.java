package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.UserBookADO;
import com.rmoya.libreria.controller.JsonDownloader;
import com.rmoya.libreria.controller.adapter.BetterAdapter;
import com.rmoya.libreria.controller.adapter.JsonAdapter;
import com.rmoya.libreria.controller.adapter.RegisterAdapter;
import com.rmoya.libreria.model.Book;

import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {
    private String libro;
    Button btnguardar;
    private List<Book> lista = new ArrayList<>();
    private EditText txtbuscar;
    private CheckBox seleccion;
    private RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        txtbuscar = findViewById(R.id.txtBuscarJson);
        btnguardar = findViewById(R.id.btnGuardarJson);

        Intent intent = getIntent();

        RecyclerView recycler = findViewById(R.id.recyclerJson);
        recycler.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(layoutManager);

       libro = intent.getStringExtra(getString(R.string.mostrar));
        if(libro.equals(getString(R.string.json))){
            Log.i("fsfs","Hello");

            if(JsonDownloader.books.isEmpty()){
                new JsonDownloader().execute();
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            adapter = new JsonAdapter(JsonDownloader.books);
        }else if(libro.equals(getString(R.string.likes))){
         adapter = new BetterAdapter(UserBookADO.orderByFav(this));
        }else {
         adapter = new BetterAdapter(UserBookADO.orderByFav(this));
        }
        recycler.setAdapter(adapter);

        txtbuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se requiere ninguna acción antes de cambiar el texto
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Este método se activará cada vez que el usuario escriba o borre texto en el EditText
                // Aquí puedes agregar el código para buscar en tu base de datos o servidor los resultados que coincidan con el texto ingresado
                // Después de obtener los resultados, puedes mostrarlos en una lista o en otro elemento de tu interfaz de usuario
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        btnguardar.setOnClickListener(v->{


        });


    }
}