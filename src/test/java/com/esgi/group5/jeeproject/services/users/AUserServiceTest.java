package com.esgi.group5.jeeproject.services.users;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import com.esgi.group5.jeeproject.services.contracts.IUserService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class AUserServiceTest {
    private String name = "test1";
    protected IUserService userService;

    protected IUserRepository userRepository;

    protected void setup(){
        userRepository = mock(IUserRepository.class);
    }

    @Test
    void should_be_empty_after_init(){
        assertThat(userService.get().isEmpty());
    }

    @Test
    void should_add_new_user_and_returns_new_user_id(){
        assertThat(userService.get().isEmpty());
        userService.add(new User(name));
        assertThat(userService.get().size() == 1);
        User u = userService.get(0);
        assertThat(u != null);
    }
}
