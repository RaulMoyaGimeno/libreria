package com.rmoya.libreria.controller;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rmoya.libreria.R;
import com.rmoya.libreria.model.BibliotecaJson;
import com.rmoya.libreria.model.Book;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class JsonController{

    private final Context context;

    public JsonController(Context context){
        this.context = context;
    }








    public List<Book> descargar() {
        String content;
        List<Book> books = new ArrayList<>();
        try {
            content = descargarTexto(context.getString(R.string.url));
            Log.i("Json: ", content);
            content = content.replace("\"compiled\": true,", "");
            Log.i("Json: ", content);
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, Book>>(){}.getType();
            Map<String, Book> libros = gson.fromJson(content, mapType);

            BibliotecaJson biblioteca = new BibliotecaJson(libros);
            books = biblioteca.getBooks();
            Log.i("Count: ", String.valueOf(books.size()));
            for (Book book : books) {
                Log.i("Book: ", book.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    @NonNull
    public static String descargarTexto(String url) throws IOException {
        URL url1 = new URL(url);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url1.openConnection();
        try{
            InputStream is = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }finally{
            urlConnection.disconnect();
        }
    }
}
