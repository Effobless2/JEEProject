package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.DAL.RoleDAL;
import com.esgi.group5.jeeproject.models.Role;
import com.esgi.group5.jeeproject.repositories.contracts.IRoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRepository implements IRoleRepository {
    private RoleDAL roleDAL;

    public RoleRepository(RoleDAL roleDAL) {
        this.roleDAL = roleDAL;
    }

    @Override
    public Role get(String label) {
        Optional<Role> role = roleDAL.findRoleByLabel(label);
        if (role.isEmpty()) {
            return add(label);
        }
        return role.get();
    }

    @Override
    public Role add(String label) {
        Role r = new Role();
        r.setLabel(label);
        return roleDAL.save(r);
    }
}
