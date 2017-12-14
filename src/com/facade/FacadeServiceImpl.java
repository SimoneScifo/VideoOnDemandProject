package com.facade;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.dto.DTOAssembler;
import com.dao.dto.FilmDTO;
import com.videoondemand.model.Genre;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FacadeServiceImpl implements FacadeService {
    public static FacadeServiceImpl instance;

    private FacadeServiceImpl(){}

    public static synchronized FacadeServiceImpl getInstance() {
        if(instance==null){
            instance= new FacadeServiceImpl();
        }
        return instance;
    }

    @Override
    public void insert(FilmDTO film) {
        FilmDAO filmDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getFilmDAO();
        filmDAO.insert(new DTOAssembler().getFilm(film));
    }

    @Override
    public List<FilmDTO> findAll() {
        return new DTOAssembler().getFilmsDTO();
    }

    @Override
    public FilmDTO findByID(int id) {
        FilmDAO filmDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getFilmDAO();
        return new DTOAssembler().getFilmDTO(filmDAO.findById(id));
    }

    @Override
    public void update(FilmDTO film) {
        FilmDAO filmDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getFilmDAO();
        filmDAO.delete(new DTOAssembler().getFilm(film));
    }

    @Override
    public void delete(FilmDTO film) {
        FilmDAO filmDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getFilmDAO();
        filmDAO.delete(new DTOAssembler().getFilm(film));
    }

    @Override
    public Map findAllGenres() {
        return new FilmDTO().getGenresMap();
    }


}
