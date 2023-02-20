package com.rmoya.libreria.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.rmoya.libreria.model.Book;

import java.util.List;


public class BookADO {
    private static Context context;

    public static void insertBooks(List <Book>lista){
        try (SQLiteDatabase db = DBInit.abrirBD(context)) {

            for (Book book : lista) {
                ContentValues cv = new ContentValues();
                cv.put("book_title", book.getBook_title());
                cv.put("author", book.getAuthor());
                cv.put("edition", book.getEdition());
                cv.put("openurl", book.getOpenurl());
                cv.put("pdfurl", book.getPdf_url());
                cv.put("isbn", book.getIsbn());
                long id = db.insert("Book", null, cv);
            }


        }
    }
}
