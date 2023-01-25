package edu.sabanciuniv.beyzagul_demir_hw2;

import java.io.Serializable;

public class NewsItem implements Serializable {
    private int id;
    private String title;
    private String text;
    private String date;
    private String img;
    private String catName;

    public NewsItem(int id, String title, String text, String date, String img, String catName) {
        this.id = id;
        this.title = title;
        this.text = text;
        date = date.substring(0, 10);
        String year = date.substring(0, 4);
        String month = date.substring(5,7);
        String day = date.substring(8);
        date = day + "/" + month + "/" + year;
        this.date = date;
        this.img = img;
        this.catName = catName;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getDate() {
        return date;
    }

    public String getImg() {
        return img;
    }

    public String getCatName() {
        return catName;
    }
}