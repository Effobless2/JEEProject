package com.esgi.group5.jeeproject.repositories.contracts;

import com.esgi.group5.jeeproject.models.Role;

public interface IRoleRepository {
    Role get(String label);
    Role add(String label);
}
