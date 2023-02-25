package com.rmoya.libreria.controller.adapter;

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
import com.rmoya.libreria.bbdd.BookADO;
import com.rmoya.libreria.model.Book;
import com.rmoya.libreria.model.User;
import com.rmoya.libreria.model.UserBook;
import com.rmoya.libreria.util.Alerts;
import com.rmoya.libreria.util.Notificate;
import com.rmoya.libreria.view.BBDDActivity;
import com.rmoya.libreria.view.ShowBookActivity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class BetterAdapter extends RecyclerView.Adapter<BetterAdapter.ViewHolder> implements RegisterAdapter {

    private final List<UserBook> libroLista;
    private Context context;
    public BetterAdapter(List<UserBook> libroLista, Context context) {
        this.libroLista = libroLista;
        this.context = context;
    }

    private Set<Integer> posiciones = new HashSet<>();
    @NonNull
    @Override
    public BetterAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_checkbox_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BetterAdapter.ViewHolder holder, int position) {

        UserBook book = libroLista.get(position);


        holder.txtTitulo.setText(book.getTitle());
        holder.txtlike.setText(String.valueOf(book.getLiked()));
        holder.txtfavs.setText(String.valueOf(book.getFav()));


        holder.itemView.setOnClickListener(v->{

            Intent intent = new Intent(v.getContext(), ShowBookActivity.class);
            intent.putExtra("userbook",book);
            Book books = BookADO.getByTitle(v.getContext(), book.getTitle());
            intent.putExtra("libro", books);
            v.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {return libroLista.size();}

    @Override
    public void onRegister() {

        Log.i("count",posiciones.size() + "");

        for (Integer i : posiciones) {
            Book book = BookADO.getByTitle(context,libroLista.get(i).getTitle());
            register(context,book,new UserBook(book.getBook_title()));
        }

        Alerts.launchDialogFields(context, context.getString(R.string.libros_guardados), context.getString(R.string.cerrar));
        Notificate.notify(context, context.getString(R.string.libros_title), context.getString(R.string.libros_guardados));

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitulo;
        CheckBox checkGuardar;
        TextView txtlike;
        TextView txtfavs;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTitulo);
            checkGuardar = itemView.findViewById(R.id.checkGuardar);
            txtlike = itemView.findViewById(R.id.txtLikes);
            txtfavs = itemView.findViewById(R.id.txtFavs);

            checkGuardar.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        posiciones.add(getAdapterPosition());
                    }else{
                        posiciones.remove(getAdapterPosition());
                    }
                }
            });

        }
    }
}
