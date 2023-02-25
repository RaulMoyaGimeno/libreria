package com.rmoya.libreria.controller.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.UserBookADO;
import com.rmoya.libreria.controller.JsonDownloader;
import com.rmoya.libreria.model.Book;
import com.rmoya.libreria.model.ListBooks;
import com.rmoya.libreria.model.User;
import com.rmoya.libreria.model.UserBook;
import com.rmoya.libreria.util.Alerts;
import com.rmoya.libreria.util.Notificate;
import com.rmoya.libreria.view.BBDDActivity;
import com.rmoya.libreria.view.ShowBookActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JsonAdapter extends RecyclerView.Adapter<JsonAdapter.ViewHolder> implements RegisterAdapter {

    private final List<Book> libroLista;
    private List<UserBook> userBooks;
    private Context context;
    public JsonAdapter(List<Book> libroLista, Context context) {
        this.libroLista = libroLista;
        this.context = context;
        userBooks = UserBookADO.orderByFav(context);
    }

    private Set<Integer> posiciones = new HashSet<>();
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
        holder.checkGuardar.setChecked(book.isSelected());
        holder.txtAutor.setText(book.getAuthor());


        holder.itemView.setOnClickListener(v->{

            Intent intent = new Intent(v.getContext(), ShowBookActivity.class);
            intent.putExtra("userbook",new UserBook(book.getBook_title()));
            intent.putExtra("libro",book);
            v.getContext().startActivity(intent);

        });

        userBooks.stream()
                .filter(userBook -> userBook.getTitle()
                .equals(book.getBook_title()))
                .findFirst()
                .ifPresent(userBook -> {
                    holder.txtlike.setText(String.valueOf(userBook.getLiked()));
                    holder.txtfav.setText(String.valueOf(userBook.getFav()));
                });



    }

    @Override
    public int getItemCount() {return libroLista.size();}

    @Override
    public void onRegister() {

        Log.i("count",posiciones.size() + "");

        for (Book book : ListBooks.getSelectedBooks()) {
            UserBook userbook = new UserBook(book.getBook_title());
            register(context, book, userbook);
        }

        Alerts.launchDialogFields(context, context.getString(R.string.libros_guardados), context.getString(R.string.cerrar));
        Notificate.notify(context, context.getString(R.string.libros_title), context.getString(R.string.libros_guardados));

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo;
        CheckBox checkGuardar;
        TextView txtAutor;
        TextView txtlike;
        TextView txtfav;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            checkGuardar = itemView.findViewById(R.id.checkGuardar);
            txtAutor = itemView.findViewById(R.id.txtAutor);

            checkGuardar.setOnCheckedChangeListener((buttonView, isChecked) -> ListBooks.changeState(getAdapterPosition()));

        }
    }
}
