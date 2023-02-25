package com.rmoya.libreria.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rmoya.libreria.controller.adapter.BBDDAdapter;
import com.rmoya.libreria.controller.adapter.BetterAdapter;
import com.rmoya.libreria.controller.adapter.JsonAdapter;
import com.rmoya.libreria.model.ListBooks;
import com.rmoya.libreria.model.UserBook;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class TitleFilter implements TextWatcher {

    RecyclerView recyclerView;
    Class<?> adapter;
    List<UserBook> list;
    Context context;

    public TitleFilter(Context context, RecyclerView recyclerView, Class<?> adapter, List<UserBook> list){
        this.recyclerView = recyclerView;
        this.adapter = adapter;
        this.list = list;
        this.context = context;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(adapter == JsonAdapter.class){
            recyclerView.setAdapter(new JsonAdapter(ListBooks.filterByTitle(String.valueOf(s)), context));
        }
        if(adapter == BetterAdapter.class){
            recyclerView.setAdapter(new BetterAdapter(filterList(s), context));
        }
        if(adapter == BBDDAdapter.class){
            recyclerView.setAdapter(new BBDDAdapter(filterList(s)));
        }

    }

    @NonNull
    private List<UserBook> filterList(CharSequence s) {
        return list.stream()
                .filter(userBook ->
                        userBook.getTitle().toUpperCase(Locale.ROOT)
                                .startsWith(String.valueOf(s).toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
