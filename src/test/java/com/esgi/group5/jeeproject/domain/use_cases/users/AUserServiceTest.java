package com.esgi.group5.jeeproject.domain.use_cases.users;

import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class AUserServiceTest {
    protected RegisterUserService registerUserService;

    protected UserRepository userRepository;

    protected void setup(){
        userRepository = mock(UserRepository.class);
    }

    @Test
    void should_add_new_user_and_returns_new_user_id(){
        ArrayList<User> mockUsers = new ArrayList<>();
        given(userRepository.get()).willReturn(mockUsers);
        for(long i = 0; i < 10; i++){
            String name = "test";
            String curName = name + i;
            User test = new User();
            test.setId(i);
            test.setName(curName);
            mockUsers.add(test);

            given(userRepository.create(test)).willReturn(test);
            User u = registerUserService.register(test);
            assertEquals(i + 1, userRepository.get().size());
        }
    }
}
