package com.rmoya.libreria.controller.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rmoya.libreria.R;
import com.rmoya.libreria.model.Book;

import java.io.Serializable;
import java.util.List;


public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.ViewHolder> implements Serializable {

    private final List<Book> LibroLista;

    public JsonAdapter(List<Book> libroLista) {LibroLista = libroLista;}

    @NonNull
    @Override
    public JsonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonAdapter.ViewHolder holder, int position) {

        Book libro = LibroLista.get(position);



        holder.itemView.setOnClickListener(v->{

            //Intent intent = new Intent(v.getContext(),;
            //intent.putExtra("posicion", position);
            //intent.putExtra("array", (Serializable) estaciones);
            //v.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {return LibroLista.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
