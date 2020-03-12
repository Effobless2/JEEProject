package com.esgi.group5.jeeproject.repositories.users;

import com.esgi.group5.jeeproject.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

public class UserRepositoryTest extends AUserRepositoryTest {

    @Override
    @BeforeEach
    void setup() {
        //userRepository = mock(UserRepository.class);
        userRepository = new UserRepository();
    }
}
