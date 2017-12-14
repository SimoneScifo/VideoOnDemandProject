package com.dao.memory;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.GenreDAO;
import com.dao.jdbc.GenreJdbcDAOImpl;

/**
 * Created by Simone on 28/11/2017.
 */
public class MemoryDAOFactory extends FactoryDAO {
    private static MemoryDAOFactory instance;
    private MemoryDAOFactory(){

    }

    public static synchronized MemoryDAOFactory getInstance(){
        if(instance==null){
            instance = new MemoryDAOFactory();
        }
        return instance;
    }

    @Override
    public FilmDAO getFilmDAO() {
        return FilmMemoryDAOImpl.getInstance();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return GenreJdbcDAOImpl.getInstance();
    }
}
