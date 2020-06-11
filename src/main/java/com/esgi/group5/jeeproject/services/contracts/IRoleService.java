package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Role;

public interface IRoleService {
    Role getAdminRole();
    Role getUserRole();


}
