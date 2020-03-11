package com.esgi.group5.jeeproject.services.users;

import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import com.esgi.group5.jeeproject.services.UserService;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class UserServiceTest extends AUserServiceTest {

    @BeforeEach
    @Override
    protected void setup() {
        super.setup();
        userService = new UserService(userRepository);
    }
}
