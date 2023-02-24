package com.rmoya.libreria.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListBooks {

    private static List<Book> books = new ArrayList<>();

    private static long position;

    public static void init(ArrayList<Book> book){
        books = book;
    }

    public static boolean isEmpty(){
        return books.isEmpty();
    }

    public static List<Book> get50first(){
        position = 50;
        return books.stream().limit(50).collect(Collectors.toList());
    }

    public static List<Book> get50previous(){
        if(position == 0){
            position = books.size() - 50;
        }
        else {
            position -= 50;
            if (position < 0) {
                position = 0;
            }
        }
        return books.stream().skip(position).limit(50).collect(Collectors.toList());
    }

    public static List<Book> get50next(){
        if(position == books.size()){
            position = 50;
        }
        else {
            position += 50;
            if (position > books.size()) {
                position = books.size();
            }
        }
        return books.stream().skip(position - 50).limit(50).collect(Collectors.toList());
    }
}
