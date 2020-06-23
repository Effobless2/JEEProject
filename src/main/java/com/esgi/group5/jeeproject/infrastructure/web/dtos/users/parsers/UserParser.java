package com.esgi.group5.jeeproject.infrastructure.web.dtos.users.parsers;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.users.GoogleAccountDTO;
import com.esgi.group5.jeeproject.infrastructure.web.dtos.users.UserWithTokenDTO;

public class UserParser {
    public static User parse(GoogleAccountDTO googleAccountDTO) {
        User result = new User();

        result.setName(googleAccountDTO.getName());
        result.setEmail(googleAccountDTO.getEmail());
        result.setAvatarUrl(googleAccountDTO.getAvatarUrl());

        return result;
    }
    public static User parse(UserWithTokenDTO userWithTokenDTO){
        User result = new User();

        result.setId(userWithTokenDTO.getId());
        result.setName(userWithTokenDTO.getName());
        result.setEmail(userWithTokenDTO.getEmail());
        result.setAvatarUrl(userWithTokenDTO.getAvatarUrl());

        return result;
    }

    public static UserWithTokenDTO parse(User user){
        UserWithTokenDTO result = new UserWithTokenDTO();

        result.setId(user.getId());
        result.setName(user.getName());
        result.setEmail(user.getEmail());
        result.setAvatarUrl(user.getAvatarUrl());

        return result;
    }
}
