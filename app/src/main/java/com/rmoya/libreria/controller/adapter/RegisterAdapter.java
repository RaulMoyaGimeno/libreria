package com.rmoya.libreria.controller.adapter;

import android.content.Context;

import com.rmoya.libreria.bbdd.BookADO;
import com.rmoya.libreria.bbdd.UserBookADO;
import com.rmoya.libreria.model.Book;
import com.rmoya.libreria.model.UserBook;

import java.util.ArrayList;

public interface RegisterAdapter {

    void onRegister();

    default void register(Context context, Book book, UserBook userBook){
        ArrayList<UserBook> userBooks = new ArrayList<>();
        userBooks.add(userBook);
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);
        if(UserBookADO.existUserBook(context, userBook.getUser(), userBook.getTitle())){
            UserBookADO.insertUserBooks(context, userBooks);
        }
        if(BookADO.existBook(context, book.getBook_title())){
            BookADO.insertBooks(context, books);
        }
    }
}