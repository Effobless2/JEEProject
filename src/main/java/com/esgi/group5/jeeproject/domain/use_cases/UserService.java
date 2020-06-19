package com.esgi.group5.jeeproject.domain.use_cases;
/*
import com.esgi.group5.jeeproject.persistence.datatbase.daos.RoleDAO;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.UserDAO;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final IUserRepository repository;
    private final IRoleService roleService;

    @Value("${beerer.admin.email}")
    private String ADMIN_EMAIL;

    @Override
    public UserDAO add(UserDAO user) {
        return repository.add(user);
    }

    @Override
    public List<UserDAO> get() {
        return repository.get();
    }

    @Override
    public UserDAO get(Long id) {
        return repository.get(id);
    }

    @Override
    public UserDAO connect(UserDAO user) {
        UserDAO result;
        UserDAO searchResult = repository.getByGoogleId(user.getGoogleId());
        if(searchResult == null){
            Collection<RoleDAO> roles = new ArrayList<>();
            if(user.getEmail().equals(ADMIN_EMAIL))
                roles.add(roleService.getAdminRole());
            else
                roles.add(roleService.getUserRole());
            user.setRoles(roles);
            result = repository.add(user);
        } else {
            searchResult.setEmail(user.getEmail());
            searchResult.setName(user.getName());
            searchResult.setAvatarUrl(user.getAvatarUrl());
            repository.update(searchResult);
            result = searchResult;
        }
        return result;

    }
}
*/