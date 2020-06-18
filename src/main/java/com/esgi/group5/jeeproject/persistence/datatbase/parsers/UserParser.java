package com.esgi.group5.jeeproject.persistence.datatbase.parsers;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.UserDAO;

public class UserParser {

    public static User parse(UserDAO userDAO) {
        User result = new User();

        result.setId(userDAO.getId());
        result.setName(userDAO.getName());
        result.setEmail(userDAO.getEmail());
        result.setAvatarUrl(userDAO.getAvatarUrl());

        return result;
    }

    public static UserDAO parse(User user) {
        UserDAO result = new UserDAO();

        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setAvatarUrl(user.getAvatarUrl());

        return result;
    }
}
