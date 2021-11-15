package com.example.text2;

public class Book {

    public String Url;
    public String title;
    public String author;
    public String image;
    public String data_publishing;


    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public Book(String Url , String title, String author, String image, String data_publishing) {
        this.Url = Url;
        this.title = title;
        this.author = author;
        this.image = image;
        this.data_publishing = data_publishing;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getData_publishing() {
        return data_publishing;
    }

    public void setData_publishing(String data_publishing) {
        this.data_publishing = data_publishing;
    }


}
