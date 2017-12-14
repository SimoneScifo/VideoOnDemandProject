package com.dao.jdbc;

import com.dao.GenreDAO;
import com.videoondemand.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreJdbcDAOImpl implements GenreDAO {
    private static GenreJdbcDAOImpl instance;

    private GenreJdbcDAOImpl (){}
    public static synchronized GenreJdbcDAOImpl getInstance(){
        if(instance==null) {
            instance = new GenreJdbcDAOImpl();
        }
        return instance;
    }

    @Override
    public boolean add(Genre g) {
        try(Connection con= JdbcDAOFactory.getConnection()){
            String query = "insert into genre (name) values (?)";
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setString(1,g.getName());
            ResultSet rs = pStmt.executeQuery();
            return true;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Genre> findAll() {
        List<Genre> list = new ArrayList<>();
        try(Connection con= JdbcDAOFactory.getConnection()){
            String query = "SELECT * FROM genre";
            PreparedStatement pSmt= con.prepareStatement(query);
            ResultSet rs = pSmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Date creationDate = rs.getDate("creationDate");
                Date lastModifyDate = rs.getDate("lastModifyDate");

                Genre g = new Genre(name,description,creationDate,lastModifyDate);
                g.setId(id);
                list.add(g);
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
