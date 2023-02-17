package com.rmoya.libreria.util;

import android.content.Context;

public class Alerts {
    public static void launchDialogFields(Context context, String text, String button){
        new androidx.appcompat.app.AlertDialog.Builder(context)
                .setMessage(text)
                .setNegativeButton(button, null)
                .show();
    }
}
