package com.rmoya.libreria.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.jetbrains.annotations.Contract;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class Encryptation {

    private final static String KEY = "p+EC1a=_xlhivu@roXlt";

    private Encryptation(){}

    @NonNull
    @Contract(" -> new")
    private static SecretKeySpec generateKey() throws NoSuchAlgorithmException, UnsupportedEncodingException{
        byte[] bytes = KEY.getBytes(StandardCharsets.UTF_8);
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        bytes = md.digest(bytes);
        bytes = Arrays.copyOf(bytes, 16);
        return new SecretKeySpec(bytes, "AES");
    }

    @Nullable
    public static String encrypt(@NonNull String text){
        try {
            SecretKeySpec secretKeySpec = generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            byte[] bytes = text.getBytes(StandardCharsets.UTF_8);
            byte[] encrypted = cipher.doFinal(bytes);
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException ex) {
            Logger.getLogger(Encryptation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Nullable
    public static String decrypt(String encryptedData){
        try {
            SecretKeySpec secretKey = generateKey();
            Cipher cipher = Cipher.getInstance("AES/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedData);
            return new String(cipher.doFinal(encryptedBytes));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchPaddingException ex) {
            Logger.getLogger(Encryptation.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
