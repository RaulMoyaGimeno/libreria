package com.rmoya.libreria.bbdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBInit {

    public static final String DBNAME = "libreria.db";

    public static void inicializarSqlite(Context context,  int idRes){
        File dbFile = context.getDatabasePath(DBNAME);

        if (!dbFile.exists()){
            try (InputStream is = context.getResources().openRawResource(idRes);
                 FileOutputStream fos = new FileOutputStream(dbFile)) {
                byte[] buffer = new byte[1024];
                int c = 0;

                while ((c = is.read(buffer)) > 0){
                    fos.write(buffer, 0, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static SQLiteDatabase abrirBD(Context context){
        SQLiteDatabase db = SQLiteDatabase.openDatabase(
                context.getApplicationContext().getDatabasePath(DBNAME).getAbsolutePath(),
                null,
                SQLiteDatabase.OPEN_READWRITE);
        return db;
    }
}
