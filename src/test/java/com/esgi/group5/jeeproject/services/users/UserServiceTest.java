package com.esgi.group5.jeeproject.services.users;

import com.esgi.group5.jeeproject.services.UserService;
import org.junit.jupiter.api.BeforeEach;

public class UserServiceTest extends AUserServiceTest {

    @BeforeEach
    @Override
    protected void setup() {
        super.setup();
        userService = new UserService(userRepository, roleService);
    }
}
