package com.dao;

import com.videoondemand.model.Role;
import com.videoondemand.model.User;

import java.util.List;

public interface UserDAO {
    List<Role> findAll();
    User findByCredentials(String username, String password);
}
