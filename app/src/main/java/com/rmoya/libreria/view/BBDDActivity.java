package com.rmoya.libreria.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.UserBookADO;
import com.rmoya.libreria.controller.UserController;
import com.rmoya.libreria.controller.adapter.BBDDAdapter;
import com.rmoya.libreria.model.UserBook;
import com.rmoya.libreria.util.TitleFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BBDDActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbddactivity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        List<UserBook> books = UserBookADO.getByUser(this, UserController.userStatic);
        RecyclerView recycler = initRecycler(books);

        TextView txtBuscar = findViewById(R.id.editTextTextPersonName);
        txtBuscar.addTextChangedListener(new TitleFilter(this, recycler, BBDDAdapter.class, books));
    }

    @NonNull
    private RecyclerView initRecycler(List<UserBook> books) {
        RecyclerView recycler = findViewById(R.id.recyclerBbdd);
        recycler.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recycler.setLayoutManager(layoutManager);

        recycler.setAdapter(new BBDDAdapter(books));
        return recycler;
    }
}