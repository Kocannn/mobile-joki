package com.semesta.icnema_uts;
public class Movie {
    private String title;
    private int imageResource;

    public Movie(String title, int imageResource) {
        this.title = title;
        this.imageResource = imageResource;
    }

    public String getTitle() {
        return title;
    }

    public int getImageResource() {
        return imageResource;
    }
}