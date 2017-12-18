package com.dao.dto;

import com.videoondemand.model.Role;
import com.videoondemand.model.User;

public class UserDTO {
    String username;
    String password;
    int id_role;
    int active;
    Role role;

    public UserDTO (){}

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.id_role = user.getId_role();
        this.active = user.getActive();

    }
}
