package com.rmoya.libreria.model;

import java.io.Serializable;

public class Book implements Serializable {

    private String book_title;
    private String author;
    private String edition;
    private String openurl;
    private String pdf_url;
    private String isbn;

    public Book(String book_title, String author, String edition, String openurl, String pdf_url, String isbn) {
        this.book_title = book_title;
        this.author = author;
        this.edition = edition;
        this.openurl = openurl;
        this.pdf_url = pdf_url;
        this.isbn = isbn;
    }

    public Book(){};

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getOpenurl() {
        return openurl;
    }

    public void setOpenurl(String openurl) {
        this.openurl = openurl;
    }

    public String getPdf_url() {
        return pdf_url;
    }

    public void setPdf_url(String pdf_url) {
        this.pdf_url = pdf_url;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" +
                "book_title='" + book_title + '\'' +
                ", author='" + author + '\'' +
                ", edition='" + edition + '\'' +
                ", openurl='" + openurl + '\'' +
                ", pdf_url='" + pdf_url + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
