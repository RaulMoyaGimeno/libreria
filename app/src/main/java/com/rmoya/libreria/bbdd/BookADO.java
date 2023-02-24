package com.rmoya.libreria.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rmoya.libreria.model.Book;

import java.util.ArrayList;
import java.util.List;


public class BookADO {


    public static void insertBooks(Context context, List <Book>lista){
        try (SQLiteDatabase db = DBInit.abrirBD(context)) {

            for (Book book : lista) {
                ContentValues cv = new ContentValues();
                cv.put("book_tiitle", book.getBook_title());
                cv.put("author", book.getAuthor());
                cv.put("edition", book.getEdition());
                cv.put("openurl", book.getOpenurl());
                cv.put("pdf_url", book.getPdf_url());
                cv.put("isbn", book.getIsbn());
                long id = db.insert("Book", null, cv);
            }
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
    }

    public static Book getByTitle(Context context, String titulo){

        String sql ="SELECT * FROM Book WHERE book_tiitle=? ";
        String[] args = { titulo };
        Book book = new Book();

        try(SQLiteDatabase db =DBInit.abrirBD(context)){
            Cursor cursor =db.rawQuery(sql,args);

            while(cursor.moveToNext()){

                book.setBook_title(cursor.getString(0));
                book.setAuthor(cursor.getString(1));
                book.setEdition(cursor.getString(2));
                book.setOpenurl(cursor.getString(3));
                book.setPdf_url(cursor.getString(4));
                book.setIsbn(cursor.getString(5));

            }

        }catch (Exception e){
            Log.i("Error",e.toString());
        }
        return book;
    }

    public static boolean existBook(Context context,String titulo){
        String sql = "SELECT * FROM Book WHERE book_tiitle = ?";
        String[] args = {titulo};

        try (SQLiteDatabase db = DBInit.abrirBD(context)){
            Cursor cursor = db.rawQuery(sql, args);
            int count = cursor.getCount();

            if (count ==0){
                return false;
            }
            else
                return true;
        }catch (Exception e){
            Log.i("Error",e.toString());
        }
        return false;
    }
}
