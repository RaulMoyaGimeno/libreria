package com.rmoya.libreria.controller.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rmoya.libreria.R;
import com.rmoya.libreria.controller.JsonDownloader;
import com.rmoya.libreria.model.Book;
import com.rmoya.libreria.model.UserBook;
import com.rmoya.libreria.view.BBDDActivity;

import java.io.Serializable;
import java.util.List;

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.ViewHolder> implements RegisterAdapter {

    private final List<Book> LibroLista;

    public JsonAdapter(List<Book> libroLista) {LibroLista = libroLista;}


    @NonNull
    @Override
    public JsonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_checkbox_layout, parent, false);
        return new JsonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonAdapter.ViewHolder holder, int position) {
        Book libro = new Book();



        holder.txtTitulo.setText(libro.getBook_title());
        //holder.txtFavs.setText(libro.getFav());
        holder.txtAutor.setText(libro.getAuthor());
        //holder.txtLikes.setText(libro.getLiked());


        holder.itemView.setOnClickListener(v->{

            Intent intent = new Intent(v.getContext(), BBDDActivity.class);
            intent.putExtra("array",libro);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {return LibroLista.size();}

    @Override
    public void onRegister() {

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo;
        TextView txtFavs;
        CheckBox checkGuardar;
        TextView txtAutor;
        TextView txtLikes;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            txtFavs = itemView.findViewById(R.id.txtFavs);
            checkGuardar = itemView.findViewById(R.id.checkGuardar);
            txtAutor = itemView.findViewById(R.id.txtAutor);
            txtLikes = itemView.findViewById(R.id.txtLikes);
        }
    }
}
