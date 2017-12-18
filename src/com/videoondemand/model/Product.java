package com.videoondemand.model;

import java.time.LocalDateTime;

/**
 * Created by Simone on 28/11/2017.
 */
public class Product {
    private int id;
    private String title;
    private int id_genre;
    private int year;
    private String film_director;
    private String cast;
    private int duration;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime lastModifyDate;
    private String cover;

    public Product(String title, int id_genre, int year) {
        this.title = title;
        this.id_genre = id_genre;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getGenre() {
        return id_genre;
    }

    public void setGenre(int genre) {
        this.id_genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFilm_director() {
        return film_director;
    }

    public String getCast() {
        return cast;
    }

    public int getDuration() {
        return duration;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastModifyDate() {
        return lastModifyDate;
    }

    public String getCover (){return cover;}

    public void setCover (String cover){
        this.cover = cover;
    }
}
