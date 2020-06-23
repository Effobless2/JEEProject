package com.esgi.group5.jeeproject.domain.use_cases.users;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class RegisterUserTest {
    protected RegisterUser registerUser;

    protected UserRepository userRepository;

    @BeforeEach
    protected void setup(){
        userRepository = mock(UserRepository.class);
        registerUser = new RegisterUser(userRepository);
    }

    @Test
    void should_add_new_user_and_returns_new_user_id(){
        ArrayList<User> mockUsers = new ArrayList<>();
        given(userRepository.getAllUsers()).willReturn(mockUsers);
        for(long i = 0; i < 10; i++){
            String name = "test";
            String curName = name + i;
            User test = new User();
            test.setId(i);
            test.setName(curName);
            mockUsers.add(test);

            given(userRepository.createUser(test)).willReturn(test);
            User u = registerUser.register(test);
            assertNotNull(u);
        }
    }
}
