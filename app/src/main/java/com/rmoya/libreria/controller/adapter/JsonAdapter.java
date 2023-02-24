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
import com.rmoya.libreria.view.ShowBookActivity;

import java.io.Serializable;
import java.util.List;

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.ViewHolder> implements RegisterAdapter {

    private final List<Book> libroLista;

    public JsonAdapter(List<Book> libroLista) {this.libroLista = libroLista;}


    @NonNull
    @Override
    public JsonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_checkbox_layout, parent, false);
        return new JsonAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JsonAdapter.ViewHolder holder, int position) {

        Book book = libroLista.get(position);


        holder.txtTitulo.setText(book.getBook_title());
        //holder.txtFavs.setText(libro.getFav());
        holder.txtAutor.setText(book.getAuthor());
        //holder.txtLikes.setText(libro.getLiked());


        holder.itemView.setOnClickListener(v->{
            Intent intent = new Intent(v.getContext(), ShowBookActivity.class);
            intent.putExtra("libro",book);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {return libroLista.size();}

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
