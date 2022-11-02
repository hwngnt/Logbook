package com.example.logbook;

public class image {
    private int id;
    private String url;

    public image(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public image(){
        this.id = 0;
        this.url = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
