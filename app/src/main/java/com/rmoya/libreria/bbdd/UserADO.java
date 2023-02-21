package com.rmoya.libreria.bbdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.rmoya.libreria.model.User;

public class UserADO {


    public static boolean insertUser(Context context,User user) {
        try (SQLiteDatabase db = DBInit.abrirBD(context)) {
            ContentValues cv = new ContentValues();
            cv.put("name", user.getName());
            cv.put("user", user.getUser());
            cv.put("password", user.getPassword());
            db.insert("User", null, cv);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;

        }
    }

    public static boolean validateUser(Context context,String usuario,String pass){
        String sql = "SELECT * FROM User WHERE user = ? AND password =?";
        String[] args = { usuario, pass };

        try (SQLiteDatabase db = DBInit.abrirBD(context)){
            Cursor cursor = db.rawQuery(sql, args);
            int count = cursor.getCount();

            if (count ==0){
                return false;
            }
            else
                return true;
        }
    }
    public static boolean existUser(Context context,String usuario){
        String sql = "SELECT * FROM User WHERE user = ?";
        String[] args = { usuario};

        try (SQLiteDatabase db = DBInit.abrirBD(context)){
            Cursor cursor = db.rawQuery(sql, args);
            int count = cursor.getCount();

            if (count ==0){
                return false;
            }
            else
                return true;
        }
    }
}
