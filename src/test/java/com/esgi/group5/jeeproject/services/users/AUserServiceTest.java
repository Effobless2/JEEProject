package com.esgi.group5.jeeproject.services.users;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import com.esgi.group5.jeeproject.services.contracts.IRoleService;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class AUserServiceTest {
    protected IUserService userService;

    protected IUserRepository userRepository;
    protected IRoleService roleService;

    protected void setup(){
        userRepository = mock(IUserRepository.class);
        roleService = mock(IRoleService.class);
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
        for(long i = 0; i < 10; i++){
            String name = "test";
            String curName = name + i;
            User test = new User();
            test.setId(i);
            test.setName(curName);
            mockUsers.add(test);

            given(userRepository.add(test)).willReturn(test);
            User u = userService.add(test);
            assertEquals(i, u.getId());
            assertEquals(i + 1, userService.get().size());

            given(userRepository.get(u.getId())).willReturn(test);
            User res = userService.get(u.getId());
            assertNotNull(res);
            assertEquals(curName, res.getName());
        }
    }
}
