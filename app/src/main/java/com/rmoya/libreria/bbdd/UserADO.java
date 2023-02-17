package com.rmoya.libreria.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.rmoya.libreria.model.User;

public class UserADO {
    private static Context context;

    public static boolean insertUser(User user) {
        try (SQLiteDatabase db = DBInit.abrirBD(context, DBInit.DBNAME)) {
            ContentValues cv = new ContentValues();
            cv.put("name", user.getName());
            cv.put("user", user.getUser());
            cv.put("password", user.getPassword());
            long id = db.insert("User", null, cv);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
