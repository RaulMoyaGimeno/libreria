package com.rmoya.libreria.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ListBooks {

    private static List<Book> books = new ArrayList<>();

    private static long position = 0;
    private static final long MAX_BOOKS = 10;

    public static void init(@NonNull ArrayList<Book> book){
        books = book;
    }

    public static void changeState(int positionAdapter){
        books.get((int) (positionAdapter + position)).changeSelected();
    }

    public static boolean isSelected(int positionAdapter){
        return books.get((int) (positionAdapter + position)).isSelected();
    }

    public static List<Book> getSelectedBooks(){
        return books.stream()
                .filter(Book::isSelected)
                .peek(Book::changeSelected)
                .collect(Collectors.toList());
    }

    public static List<Book> filterByTitle(String title){
        return books.stream()
                .filter(book ->
                        book.getBook_title().toUpperCase(Locale.ROOT)
                                .startsWith(title.toUpperCase(Locale.ROOT)))
                .collect(Collectors.toList());
    }

    public static List<Book> get50first(){
        return getFromPosition();
    }

    public static List<Book> get50previous(){
        position = position == 0 ? books.size() - MAX_BOOKS : position - MAX_BOOKS;
        if (position < 0) position = 0;
        return getFromPosition();
    }

    public static List<Book> get50next(){
        position = position >= books.size() - MAX_BOOKS ? 0 : position + MAX_BOOKS;
        return getFromPosition();
    }

    private static List<Book> getFromPosition(){
        return books.stream()
                .skip(position)
                .limit(MAX_BOOKS)
                .collect(Collectors.toList());
    }
}
