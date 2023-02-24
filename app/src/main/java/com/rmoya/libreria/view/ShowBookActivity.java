package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.UserBookADO;

import com.rmoya.libreria.controller.UserController;
import com.rmoya.libreria.model.Book;
import com.rmoya.libreria.model.UserBook;

public class ShowBookActivity extends AppCompatActivity {

    private Book book;
    private UserBook userBook;
    private String user;
    private TextView txtBookTitleShow;
    private TextView txtAuthorShow;
    private TextView txtEditionShow;
    private TextView txtOpenUrlShow;
    private TextView pdfUrlShow;
    private TextView txtIsbnShow;
    private CheckBox checkFavs;
    private CheckBox checkRead;
    private CheckBox checkReading;
    private CheckBox checkDiscard;
    private CheckBox checkLike;
    private CheckBox checkDislike;
    private Button btnGuardarCambios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_book);

        txtBookTitleShow=findViewById(R.id.txtBookTitleShow);
        txtAuthorShow=findViewById(R.id.txtAuthorShow);
        txtEditionShow=findViewById(R.id.txtEditionShow);
        txtOpenUrlShow=findViewById(R.id.txtOpenUrlShow);
        pdfUrlShow=findViewById(R.id.pdfUrlShow);
        txtIsbnShow=findViewById(R.id.txtIsbnShow);
        checkFavs=findViewById(R.id.checkFavs);
        checkRead=findViewById(R.id.checkRead);
        checkReading=findViewById(R.id.checkReading);
        checkDiscard=findViewById(R.id.checkDiscard);
        checkLike=findViewById(R.id.checkLike);
        checkDislike=findViewById(R.id.checkDislike);
        btnGuardarCambios=findViewById(R.id.btnGuardarCambios);


        Intent intent= getIntent();
        book= (Book) intent.getSerializableExtra("libro");
        userBook=(UserBook) intent.getSerializableExtra("userbook");
        user= UserController.userStatic;
        showData();
        cargarWeb(book.getOpenurl());

        if(UserBookADO.existUserBook(this,userBook.getUser(),userBook.getTitle())){
            //Si el userbook existe
            stateUserBook(userBook);
            updateCheck(userBook);
            UserBookADO.updateUserBook(this,userBook);
        }else{
            //Crear un nuevo user book
            userBook= new UserBook();
            userBook.setTitle(book.getBook_title());
            userBook.setUser(user);
            updateCheck(userBook);
        }
        btnGuardarCambios.setOnClickListener(v -> {
            if(UserBookADO.existUserBook(this,userBook.getUser(),userBook.getTitle())){
                //Si el userbook existe
                UserBookADO.updateUserBook(this,userBook);
            }else{
                //Crear un nuevo user book
                UserBookADO.insertUserBook(this,userBook);
            }
        });
    }


    private void cargarWeb(String atributo){
        if(!txtOpenUrlShow.getText().equals("")){
            txtOpenUrlShow.setOnClickListener(v->{
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(atributo.replace("", "")));
                startActivity(browserIntent);
            });
        }

    }
    private void showData(){
        txtBookTitleShow.setText(book.getBook_title());
        txtAuthorShow.setText(book.getAuthor());
        txtEditionShow.setText(book.getEdition());
        txtOpenUrlShow.setText(book.getOpenurl());
        pdfUrlShow.setText(book.getPdf_url());
        txtIsbnShow.setText(book.getIsbn());
    }

    private void stateUserBook(UserBook ub){
        stateAtribute(ub.getFav(),checkFavs);
        stateAtribute(ub.getDiscard(),checkDiscard);
        stateAtribute(ub.getLiked(),checkLike);
        stateAtribute(ub.getLiked(),checkDislike);
        stateAtribute(ub.getRead(),checkRead);
        stateAtribute(ub.getReading(),checkReading);
    }

    private void stateAtribute(int atributo, CheckBox check){
        if(atributo==1){
            check.setChecked(true);
        }else{
            check.setChecked(false);
        }
    }

    private void updateCheck(UserBook ub){

        if(checkFavs.isChecked()){
            ub.setFav(1);
        }else{
            ub.setFav(0);
        }
        if(checkLike.isChecked()){
            ub.setLiked(1);
            checkDislike.setChecked(false);
        }else if(checkDislike.isChecked()){
            ub.setLiked(-1);
            checkLike.setChecked(false);
        }else{
            ub.setLiked(0);
        }
        if(checkDiscard.isChecked()){
            ub.setDiscard(1);
        }else{
            ub.setDiscard(0);
        }
        if(checkRead.isChecked()){
            ub.setRead(1);
        }else{
            ub.setRead(0);
        }
        if(checkReading.isChecked()){
            ub.setReading(1);
        }else{
            ub.setReading(0);
        }
    }


}