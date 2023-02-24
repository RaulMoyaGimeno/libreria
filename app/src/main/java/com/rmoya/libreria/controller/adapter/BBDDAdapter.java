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
import com.rmoya.libreria.bbdd.BookADO;
import com.rmoya.libreria.model.UserBook;
import com.rmoya.libreria.view.ShowBookActivity;

import java.util.List;

public class BBDDAdapter extends RecyclerView.Adapter<BBDDAdapter.ViewHolder>{

    private final List<UserBook> list;

    public BBDDAdapter(List<UserBook> libroLista) {list = libroLista;}

    @NonNull
    @Override
    public BBDDAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_bbdd_layout, parent, false);

        return new BBDDAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BBDDAdapter.ViewHolder holder, int position) {

        UserBook userBook = list.get(position);
        holder.txtTitulo.setText(userBook.getTitle());

        if(userBook.getFav() == 0) holder.cbFavs.setVisibility(View.INVISIBLE);
        if(userBook.getLiked() <= 0) holder.cbLikes.setVisibility(View.INVISIBLE);
        if(userBook.getLiked() >= 0) holder.cbDislikes.setVisibility(View.INVISIBLE);
        if(userBook.getRead() == 0) holder.cbFinished.setVisibility(View.INVISIBLE);
        if(userBook.getReading() == 0) holder.cbReading.setVisibility(View.INVISIBLE);
        if(userBook.getDiscard() == 0) holder.cbCancel.setVisibility(View.INVISIBLE);


        holder.itemView.setOnClickListener(v->{

            Intent intent = new Intent(v.getContext(), ShowBookActivity.class);
            intent.putExtra("userBook",userBook);
            intent.putExtra("book", BookADO.getByTitle(v.getContext(), userBook.getTitle()));
            v.getContext().startActivity(intent);
        });


    }

    @Override
    public int getItemCount() {return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitulo;
        CheckBox cbFavs;
        CheckBox cbLikes;
        CheckBox cbDislikes;
        CheckBox cbCancel;
        CheckBox cbReading;
        CheckBox cbFinished;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitulo = itemView.findViewById(R.id.txtTituloBbdd);
            cbFavs = itemView.findViewById(R.id.checkBox);
            cbLikes = itemView.findViewById(R.id.checkBox5);
            cbDislikes = itemView.findViewById(R.id.checkBox6);
            cbCancel = itemView.findViewById(R.id.checkBox4);
            cbReading = itemView.findViewById(R.id.checkBox3);
            cbFinished = itemView.findViewById(R.id.checkBox2);

        }
    }
}
