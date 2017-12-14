package com.dao;

import com.dao.jdbc.JdbcDAOFactory;
import com.dao.memory.MemoryDAOFactory;

/**
 * Created by Simone on 28/11/2017.
 */
public abstract class FactoryDAO {
    public enum Type{
        MEM,JDBC
    }
    public abstract FilmDAO getFilmDAO();
    public abstract GenreDAO getGenreDAO();

    public static FactoryDAO getFactoryDAO(Type type){
        switch (type){
            case MEM:
                return MemoryDAOFactory.getInstance();
            case JDBC:
                return JdbcDAOFactory.getInstance();
            default: return null;
        }
    }
}
