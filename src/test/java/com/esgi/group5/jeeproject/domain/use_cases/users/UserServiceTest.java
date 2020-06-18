package com.esgi.group5.jeeproject.domain.use_cases.users;

import org.junit.jupiter.api.BeforeEach;

public class UserServiceTest extends AUserServiceTest {

    @BeforeEach
    @Override
    protected void setup() {
        super.setup();
        registerUserService = new RegisterUserService(userRepository);
    }
}
