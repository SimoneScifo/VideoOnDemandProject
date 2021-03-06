package com.dao.jdbc;

import com.dao.FilmDAO;
import com.sun.org.apache.regexp.internal.RE;
import com.videoondemand.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmJdbcDAOImpl implements FilmDAO {
    private static FilmJdbcDAOImpl instance;

    private FilmJdbcDAOImpl() {
    }

    public static synchronized FilmJdbcDAOImpl getInstance() {
        if (instance == null) {
            instance = new FilmJdbcDAOImpl();
        }
        return instance;
    }

    @Override
    public void insert(Product f) {
        try (Connection con = JdbcDAOFactory.getConnection()) {
            String query = "INSERT INTO film (title, id_genre, year, cover) VALUES (?,?,?,?)";
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setString(1, f.getTitle());
            pStmt.setInt(2, f.getGenre());
            pStmt.setInt(3, f.getYear());
            pStmt.setString(4,f.getCover());
            pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll(String order) {
        List<Product> products = new ArrayList<>();
        try (Connection con = JdbcDAOFactory.getConnection()) {
            String query = "SELECT * FROM film";
            switch (order){
                case "yearAsc": query+=" ORDER BY year ASC";
                break;
                case "titleDesc": query+=" ORDER BY title DESC";
                break;
            }
            PreparedStatement pStmt = con.prepareStatement(query);
            ResultSet rs = pStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                int id_genre = rs.getInt("id_genre");
                int year = rs.getInt("year");
                String cover = rs.getString("cover");
                Product p = new Product(title, id_genre, year);
                p.setId(id);
                p.setCover(cover);
                products.add(p);
            }
            return products;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(int idInput) {
        try (Connection con = JdbcDAOFactory.getConnection()) {
            String query = "SELECT * FROM film WHERE id=?";
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setInt(1, idInput);
            ResultSet rs = pStmt.executeQuery();
            rs.next();
            int id = rs.getInt("id");
            String title = rs.getString("title");
            int id_genre = rs.getInt("id_genre");
            int year = rs.getInt("year");
            String cover = rs.getString("cover");
            Product p = new Product(title, id_genre, year);
            p.setId(id);
            p.setCover(cover);
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Product f) {
        try (Connection con = JdbcDAOFactory.getConnection()) {
            String query = "UPDATE film SET title=?, id_genre=?, year=?, cover=? WHERE id=?";
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setString(1, f.getTitle());
            pStmt.setInt(2, f.getGenre());
            pStmt.setInt(3, f.getYear());
            pStmt.setString(4,f.getCover());
            pStmt.setInt(5,f.getId());
            pStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Product f) {
        try (Connection con = JdbcDAOFactory.getConnection()) {
            System.out.println(f.getId());
            String query = "DELETE FROM film WHERE id=?";
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setInt(1,f.getId());
            pStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
