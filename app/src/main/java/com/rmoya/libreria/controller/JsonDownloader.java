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

    private final String URL = "https://storage.googleapis.com/kagglesdsdata/datasets/624161/1112931/Books.json?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=gcp-kaggle-com%40kaggle-161607.iam.gserviceaccount.com%2F20230216%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20230216T145810Z&X-Goog-Expires=259200&X-Goog-SignedHeaders=host&X-Goog-Signature=964ae26aa3b4aff1a94a46482a889525adde601389d63ef49057d6a84a381c12bf1efe385b7443c62b468344efbfb39e6a543843273521aacdcbd652c1129a093052194c8e00c8716421bda19ff1cc5ba3b26a01f85be4d13c098e2dd98ba979a0ed4db11f50226da94e30a90a8828699a8bba4a305858ffd407df72da7c38a63e0e85dcae852a6172a78dfb870f1a6ff0087ad1fcfc245e1cc9d6fe349caccc8b93ff79874db3682fd7f56398122814501aa85d2924764a52bc6cb65ef997bc54b96837173613ff3d442dab399281d5a0239746b7aed7f5c65e21f221690150a5c16afdceb53d2654ded78bd52cb6123c7f5d9ec44e3c740f5ba1648b80717c";

    private RecyclerView recyclerView;

    public JsonDownloader(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }

    @Override
    protected List<Book> doInBackground(Void... voids) {
        String content;
        Map<String, Book> books = new HashMap<>();
        try {
            content = descargarTexto(URL).replace("\"compiled\": true,", "");
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
