package com.dao;

import com.videoondemand.model.Genre;

import java.util.List;

public interface GenreDAO {
    public boolean add(Genre g);
    public List<Genre> findAll();
}