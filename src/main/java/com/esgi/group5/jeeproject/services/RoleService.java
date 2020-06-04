package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Role;
import com.esgi.group5.jeeproject.repositories.contracts.IRoleRepository;
import com.esgi.group5.jeeproject.services.contracts.IRoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements IRoleService {
    private IRoleRepository roleRepository;

    public RoleService(IRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    private Role get(String label) {
        return roleRepository.get(label);
    }

    private Role add(String label) {
        return roleRepository.add(label);
    }

    @Override
    public Role getAdminRole() {
        return get("ROLE_ADMIN");
    }

    @Override
    public Role getUserRole() {
        return get("ROLE_USER");
    }
}
