package com.esgi.group5.jeeproject.services.users;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class AUserServiceTest {
    private String name = "test";
    protected IUserService userService;

    protected IUserRepository userRepository;

    protected void setup(){
        userRepository = mock(IUserRepository.class);
    }

    @Test
    void should_be_empty_after_init(){
        given(userRepository.get()).willReturn(new ArrayList<>());
        assertTrue(userService.get().isEmpty());
    }

    @Test
    void should_add_new_user_and_returns_new_user_id(){
        ArrayList<User> mockUsers = new ArrayList<>();
        given(userRepository.get()).willReturn(mockUsers);
        for(int i = 0; i < 10; i++){
            String curName = name + i;
            User test = new User(curName);
            mockUsers.add(test);

            given(userRepository.add(test)).willReturn(i);
            int id = userService.add(test);
            assertEquals(i, id);
            assertEquals(i + 1, userService.get().size());

            given(userRepository.get(id)).willReturn(test);
            User u = userService.get(id);
            assertNotNull(u);
            assertEquals(curName, u.getName());
        }
    }
}
