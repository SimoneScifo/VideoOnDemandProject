package com.dao.dto;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.jdbc.JdbcDAOFactory;
import com.videoondemand.model.Genre;
import com.videoondemand.model.Product;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmDTO {
    public int id;
    public String title;
    public int id_genre;
    public Genre genre;
    public int year;
    public String film_director;
    public String cast;
    public int duration;
    public String description;
    public LocalDateTime creationDate;
    public LocalDateTime lastModifyDate;
    public String cover;

    public FilmDTO (){}

    public FilmDTO (Product film){
        this.id = film.getId();
        this.title = film.getTitle();
        this.id_genre = film.getGenre();
        this.genre = getGenresMap().get(id_genre);
        this.year = film.getYear();
        this.film_director = film.getFilm_director();
        this.cast = film.getCast();
        this.duration = film.getDuration();
        this.description = film.getDescription();
        this.creationDate = film.getCreationDate();
        this.lastModifyDate = film.getLastModifyDate();
        this.cover = film.getCover();
    }

    public Map<Integer,Genre> getGenresMap(){
        List<Genre> genreList = JdbcDAOFactory.getInstance().getGenreDAO().findAll();
        Map<Integer,Genre> genreMap = new HashMap<>();
        for(Genre g : genreList){
            genreMap.put(g.getId(),g);
        }
        return genreMap;
        //TODO POSSIBILE MODIFICA CON STREAM
    }



}
