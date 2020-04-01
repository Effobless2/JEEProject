package com.esgi.group5.jeeproject.repositories.users;

import com.esgi.group5.jeeproject.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;

public class UserRepositoryTest extends AUserRepositoryTest {

    @Override
    @BeforeEach
    void setup() {
        userRepository = new UserRepository();
    }
}
