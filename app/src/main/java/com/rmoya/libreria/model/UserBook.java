package com.rmoya.libreria.model;

import com.rmoya.libreria.controller.UserAdapter;

import java.io.Serializable;

public class UserBook implements Serializable {

    private int id;
    private String user;
    private String title;
    private int fav;
    private int read;
    private int reading;
    private int discard;
    private int liked;

    public UserBook(String title){
        this.title = title;
        user = UserAdapter.userStatic;
        reading = 1;
    }

    public UserBook(){}

    public UserBook(int id, String user, String title, int fav, int read, int reading, int discard, int liked) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.fav = fav;
        this.read = read;
        this.reading = reading;
        this.discard = discard;
        this.liked = liked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getTitle() {
        return title;
    }

    public int getFav() {
        return fav;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public int getReading() {
        return reading;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public int getDiscard() {
        return discard;
    }

    public void setDiscard(int discard) {
        this.discard = discard;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }
}
