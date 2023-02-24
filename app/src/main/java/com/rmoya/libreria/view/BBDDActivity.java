package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.UserBookADO;
import com.rmoya.libreria.controller.UserAdapter;
import com.rmoya.libreria.controller.adapter.BBDDAdapter;
import com.rmoya.libreria.model.UserBook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BBDDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbddactivity);

        List<UserBook> books = UserBookADO.getByUser(this, UserAdapter.userStatic);

        RecyclerView recycler = findViewById(R.id.recyclerBbdd);
        recycler.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(layoutManager);

        recycler.setAdapter(new BBDDAdapter(books));

        TextView txtBuscar = findViewById(R.id.editTextTextPersonName);

        txtBuscar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // No se requiere ninguna acción antes de cambiar el texto
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recycler.setAdapter(new BBDDAdapter(books.stream().filter(userBook -> userBook.getTitle().startsWith(String.valueOf(s))).collect(Collectors.toList())));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });
    }
}