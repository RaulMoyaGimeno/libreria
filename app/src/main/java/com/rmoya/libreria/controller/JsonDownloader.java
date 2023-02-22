package com.rmoya.libreria.controller;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rmoya.libreria.controller.adapter.JsonAdapter;
import com.rmoya.libreria.model.Book;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class JsonDownloader extends AsyncTask<Void, Void, List<Book>> {

    private final String URL = "https://raw.githubusercontent.com/RaulMoyaGimeno/libreria/main/books.json";

    private RecyclerView recyclerView;

    public JsonDownloader(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    @Override
    protected List<Book> doInBackground(Void... voids) {
        String content;
        Map<String, Book> books = new HashMap<>();
        try {
            content = descargarTexto(URL);
            Gson gson = new Gson();
            Type mapType = new TypeToken<Map<String, Book>>(){}.getType();
            books = gson.fromJson(content, mapType);
            Log.i("Count: ", String.valueOf(books.size()));
            for (Book book : books.values()) {
                Log.i("Book: ", book.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(books.values());
    }

    @Override
    protected void onPostExecute(List<Book> books) {
        super.onPostExecute(books);
        recyclerView.setAdapter(new JsonAdapter(books));
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
