package com.rmoya.libreria.controller;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rmoya.libreria.model.Book;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class JsonDownloader extends AsyncTask<List<Book>, Void, Void> {

    private final String URL = "https://raw.githubusercontent.com/RaulMoyaGimeno/libreria/main/books.json";

    @Override
    protected Void doInBackground(List<Book>... books) {
        String content;
        Map<String, Book> booksMap;
        try {
            content = descargarTexto(URL);
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, Book>>(){}.getType();
            booksMap = gson.fromJson(content, mapType);
            Log.i("Count: ", String.valueOf(booksMap.size()));
            for (Book book : booksMap.values()) {
                Log.i("Book: ", book.toString());
                books[0].add(book);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    private static String descargarTexto(String url) throws IOException {
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
