package com.facade;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.UserDAO;
import com.dao.dto.DTOAssembler;
import com.dao.dto.FilmDTO;
import com.sun.security.sasl.ntlm.FactoryImpl;
import com.videoondemand.model.Genre;
import com.videoondemand.model.User;

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
    public List<FilmDTO> findAll(String order) {
        return new DTOAssembler().getFilmsDTO(order);
    }

    @Override
    public FilmDTO findByID(int id) {
        FilmDAO filmDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getFilmDAO();
        return new DTOAssembler().getFilmDTO(filmDAO.findById(id));
    }

    @Override
    public void update(FilmDTO film) {
        FilmDAO filmDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getFilmDAO();
        filmDAO.update(new DTOAssembler().getFilm(film));
    }

    @Override
    public void delete(FilmDTO film) {
        FilmDAO filmDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getFilmDAO();
        filmDAO.delete(new DTOAssembler().getFilm(film));
    }

    @Override
    public User findByCredentials(String username,String password) {
        UserDAO userDAO = FactoryDAO.getFactoryDAO(FactoryDAO.Type.JDBC).getUserDAO();
        User u = userDAO.findByCredentials(username,password);
        return u;
    }

    @Override
    public Map findAllGenres() {
        return new FilmDTO().getGenresMap();
    }
}
