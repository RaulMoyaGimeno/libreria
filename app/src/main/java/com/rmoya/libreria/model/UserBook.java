package com.rmoya.libreria.model;

public class UserBook {

    private final int id;
    private final String user;
    private final String title;
    private int fav;
    private int read;
    private int reading;
    private int discard;
    private int liked;

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

    /*private UserBook(@NonNull Builder builder) {
        id = builder.id;
        user = builder.user;
        title = builder.title;
        fav = builder.fav;
        read = builder.read;
        reading = builder.reading;
        discard = builder.discard;
        liked = builder.liked;
    }

    public static class Builder{
        private final int id;
        private final String user;
        private final String title;

        //Optional
        private int fav = 0;
        private int read = 0;
        private int reading = 0;
        private int discard = 0;
        private int liked = 0;

        public Builder(int id, String user, String title) {
            this.id = id;
            this.user = user;
            this.title = title;
        }

        public Builder fav(){
            fav = 1;
            return this;
        }

        public Builder read(){
            read = 1;
            return this;
        }

        public Builder reading(){
            reading = 1;
            return this;
        }

        public Builder discard(){
            discard = 1;
            return this;
        }

        public Builder liked(){
            liked = 1;
            return this;
        }

        public Builder disliked(){
            liked = -1;
            return this;
        }

        public UserBook build(){
            return new UserBook(this);
        }
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

    public int getRead() {
        return read;
    }

    public int getReading() {
        return reading;
    }

    public int getDiscard() {
        return discard;
    }

    public int getLiked() {
        return liked;
    }

    public void setFav(int fav) {
        this.fav = fav;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public void setReading(int reading) {
        this.reading = reading;
    }

    public void setDiscard(int discard) {
        this.discard = discard;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }*/
}
