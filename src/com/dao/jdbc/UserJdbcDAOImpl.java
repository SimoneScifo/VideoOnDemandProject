package com.dao.jdbc;

import com.dao.UserDAO;
import com.videoondemand.model.Role;
import com.videoondemand.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserJdbcDAOImpl implements UserDAO {

    private static UserJdbcDAOImpl instance;

    public static synchronized UserJdbcDAOImpl getInstance(){
        if(instance==null){
            instance = new UserJdbcDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Role> findAll() {
        return null;
    }

    public User findByCredentials(String username, String password){
        try(Connection con = JdbcDAOFactory.getConnection()){
            String query = "SELECT * FROM user WHERE username=? and password=?";
            PreparedStatement pStmt = con.prepareStatement(query);
            pStmt.setString(1, username);
            pStmt.setString(2, password);
            ResultSet rs =  pStmt.executeQuery();
            rs.next();
            String usernameNew = rs.getString("username");
            String passwordNew = rs.getString("password");
            int id_role = rs.getInt("id_role");
            User u = new User(usernameNew, passwordNew, id_role);
            return u;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
