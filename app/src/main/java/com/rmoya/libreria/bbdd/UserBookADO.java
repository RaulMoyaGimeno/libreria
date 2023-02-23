package com.rmoya.libreria.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rmoya.libreria.model.Book;
import com.rmoya.libreria.model.UserBook;

import java.util.ArrayList;
import java.util.List;

public class UserBookADO {

    public static List<UserBook> orderByFav(Context context){
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

    public static void insertUserBooks(Context context, List <UserBook>lista){
        try (SQLiteDatabase db = DBInit.abrirBD(context)) {

            for (UserBook ub : lista) {
                ContentValues cv = new ContentValues();
                cv.put("user", ub.getUser());
                cv.put("title", ub.getTitle());
                cv.put("fav", ub.getFav());
                cv.put("read", ub.getRead());
                cv.put("reading", ub.getReading());
                cv.put("discard", ub.getDiscard());
                cv.put("liked", ub.getLiked());
                long id = db.insert("UserBook", null, cv);
            }
        }
    }

    public static void updateUserBook(Context context,UserBook ub){

        try(SQLiteDatabase db = DBInit.abrirBD(context)){
            ContentValues cv = new ContentValues();
            cv.put("user", ub.getUser());
            cv.put("title", ub.getTitle());
            cv.put("fav", ub.getFav());
            cv.put("read", ub.getRead());
            cv.put("reading", ub.getReading());
            cv.put("discard", ub.getDiscard());
            cv.put("liked", ub.getLiked());

            db.update("UserBook",cv,"user=?",new String[]{String.valueOf(ub.getUser())});
        }
    }
    public static UserBook getByTitleAndUser(Context context, String usuario,String titulo){

        String sql="SELECT * FROM UserBook WHERE user=? AND title =?";

        try(SQLiteDatabase db = DBInit.abrirBD(context)){
            Cursor cursor = db.rawQuery(sql,new String[]{usuario,titulo});

            if(cursor.moveToNext()){
                UserBook ub = new UserBook();
                ub.setId(cursor.getInt(0));
                ub.setUser(cursor.getString(1));
                ub.setTitle(cursor.getString(2));
                ub.setFav(cursor.getInt(3));
                ub.setRead(cursor.getInt(4));
                ub.setReading(cursor.getInt(5));
                ub.setDiscard(cursor.getInt(6));
                ub.setLiked(cursor.getInt(7));

                return ub;
            }else
                return null;
        }
    }
    public static List<UserBook> getByUser(Context context,String usuario){
        List<UserBook> books = new ArrayList<>();
        String[] args = {usuario};
        String sql ="SELECT * FROM UserBook WHERE user=?";

        try(SQLiteDatabase db =DBInit.abrirBD(context)){
            Cursor cursor =db.rawQuery(sql,args);

            while(cursor.moveToNext()){
                UserBook ub = new UserBook();
                ub.setId(cursor.getInt(0));
                ub.setUser(cursor.getString(1));
                ub.setTitle(cursor.getString(2));
                ub.setFav(cursor.getInt(3));
                ub.setRead(cursor.getInt(4));
                ub.setReading(cursor.getInt(5));
                ub.setDiscard(cursor.getInt(6));
                ub.setLiked(cursor.getInt(7));
                books.add(ub);
            }
        }
        return books;
    }
}
