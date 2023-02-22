package com.rmoya.libreria.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.rmoya.libreria.R;
import com.rmoya.libreria.bbdd.BookADO;
import com.rmoya.libreria.controller.JsonDownloader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonActivity extends AppCompatActivity {
    public List<BookADO> libro = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);



    }
}