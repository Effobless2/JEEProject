package com.esgi.group5.jeeproject.repositories;
/*
import com.esgi.group5.jeeproject.repositories.contracts.IRoleRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RoleRepository implements IRoleRepository {
    private JpaRoleRepository jpaRoleRepository;

    public RoleRepository(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public RoleDAO get(String label) {
        Optional<RoleDAO> role = jpaRoleRepository.findRoleByLabel(label);
        if (role.isEmpty()) {
            return add(label);
        }
        return role.get();
    }

    @Override
    public RoleDAO add(String label) {
        RoleDAO r = new RoleDAO();
        r.setLabel(label);
        return jpaRoleRepository.save(r);
    }
}
*/