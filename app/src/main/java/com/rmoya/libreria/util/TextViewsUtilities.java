package com.rmoya.libreria.util;

import android.widget.EditText;

public class TextViewsUtilities {
    public static boolean checkTxtEmpty(EditText txt) {
        return "".equals(txt.getText().toString().trim());
    }
}
