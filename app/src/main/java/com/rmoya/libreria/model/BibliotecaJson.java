package com.rmoya.libreria.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BibliotecaJson {

    private boolean compiled;
    private Map<String, Book> libros;

    public BibliotecaJson(Map<String, Book> books) {
        libros = books;
    }

    public List<Book> getBooks(){
        List<Book> books = new ArrayList<>();
        for (String key : libros.keySet()) {
            books.add(libros.get(key));
        }
        return books;
    }
}
