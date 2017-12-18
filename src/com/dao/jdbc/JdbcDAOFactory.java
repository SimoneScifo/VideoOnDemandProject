package com.dao.jdbc;

import com.dao.FactoryDAO;
import com.dao.FilmDAO;
import com.dao.GenreDAO;
import com.dao.UserDAO;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcDAOFactory extends FactoryDAO {

    private static JdbcDAOFactory instance;

    private JdbcDAOFactory(){}

    public static synchronized JdbcDAOFactory getInstance() {
        if(instance==null){
            instance = new JdbcDAOFactory();
        }
        return instance;
    }

    @Override
    public FilmDAO getFilmDAO() {
        return FilmJdbcDAOImpl.getInstance();
    }

    @Override
    public GenreDAO getGenreDAO() {
        return GenreJdbcDAOImpl.getInstance();
    }

    @Override
    public UserDAO getUserDAO() {
        return UserJdbcDAOImpl.getInstance();
    }

    public static Connection getConnection(){
        try{
            InitialContext ic = new InitialContext();
            DataSource ds = (DataSource) ic.lookup("java:/comp/env/jdbc/myvideoondemand");
            return ds.getConnection();
        }catch (NamingException ne){
            ne.printStackTrace();
        }
        catch (SQLException sqle){
            sqle.printStackTrace();
        }
        return null;
    }
}
