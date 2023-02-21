package com.rmoya.libreria.bbdd;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rmoya.libreria.model.Book;
import com.rmoya.libreria.model.UserBook;

import java.util.ArrayList;
import java.util.List;

public class UserBookADO {

    public List<UserBook> getAll(Context context){
        List<UserBook> books = new ArrayList<>();
        String sql ="SELECT title, fav FROM UserBook GROUP by title order by fav DESC";

        try(SQLiteDatabase db =DBInit.abrirBD(context)){
            Cursor cursor =db.rawQuery(sql,null);

            while(cursor.moveToNext()){
                UserBook userbook = new UserBook(
                        0, "", cursor.getString(0), 0, 0,
                        0,0, cursor.getInt(1));

                books.add(userbook);
            }
        }
        return books;
    }
}
