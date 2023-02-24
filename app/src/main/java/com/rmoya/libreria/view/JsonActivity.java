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
import android.widget.ImageButton;
import android.widget.ImageView;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.BookADO;
import com.rmoya.libreria.bbdd.UserBookADO;
import com.rmoya.libreria.controller.JsonDownloader;
import com.rmoya.libreria.controller.adapter.BetterAdapter;
import com.rmoya.libreria.controller.adapter.JsonAdapter;
import com.rmoya.libreria.controller.adapter.RegisterAdapter;
import com.rmoya.libreria.model.Book;
import com.rmoya.libreria.model.ListBooks;

import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {
    private String libro;
    Button btnguardar;
    private List<Book> lista = new ArrayList<>();
    private EditText txtbuscar;
    private RegisterAdapter adapter;
    private ImageView imgderecha;
    private ImageView imgizquierda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        txtbuscar = findViewById(R.id.txtBuscarJson);
        btnguardar = findViewById(R.id.btnGuardarJson);
        imgderecha = findViewById(R.id.imgDerecha);
        imgizquierda = findViewById(R.id.imgIzquierda);

        Intent intent = getIntent();

        RecyclerView recycler = findViewById(R.id.recyclerJson);
        recycler.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(layoutManager);

       libro = intent.getStringExtra(getString(R.string.mostrar));
        if(libro.equals(getString(R.string.json))){
            Log.i("fsfs","Hello");

            adapter = new JsonAdapter(ListBooks.get50first(),this);

        }else if(libro.equals(getString(R.string.likes))){

         adapter = new BetterAdapter(UserBookADO.orderByFav(this));

        }else{

         adapter = new BetterAdapter(UserBookADO.orderByFav(this));

        }
        recycler.setAdapter((RecyclerView.Adapter) adapter);

        txtbuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                adapter = new JsonAdapter(ListBooks.filterByTitle(String.valueOf(s)),getApplicationContext());

                recycler.setAdapter((RecyclerView.Adapter) adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {}

        });

        btnguardar.setOnClickListener(v->{
           adapter.onRegister();
        });

       imgderecha.setOnClickListener(v->{
           adapter = new JsonAdapter(ListBooks.get50next(),this);
       });

       imgizquierda.setOnClickListener(v->{
           adapter = new JsonAdapter(ListBooks.get50previous(),this);
       });

    }
}