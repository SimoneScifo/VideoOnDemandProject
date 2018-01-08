package com.facade;

import com.dao.dto.FilmDTO;
import com.videoondemand.model.Genre;
import com.videoondemand.model.User;

import java.util.List;
import java.util.Map;

public interface FacadeService {
    void insert (FilmDTO film);
    List<FilmDTO> findAll(String order);
    FilmDTO findByID(int id);
    void update (FilmDTO film);
    void delete (FilmDTO film);
    Map findAllGenres();
    User findByCredentials(String username, String password);
}
