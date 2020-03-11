package com.esgi.group5.jeeproject.repositories.users;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public abstract class AUserRepositoryTest {
    protected IUserRepository userRepository;
    private String name = "test1";

    abstract void setup();

    @Test
    void should_be_empty_after_init(){
        assertThat(userRepository.get().isEmpty());
    }

    @Test
    void should_add_new_user_and_returns_new_user_id(){
        assertThat(userRepository.get().isEmpty());
        userRepository.add(new User(name));
        assertThat(userRepository.get().size() == 1);
        User u = userRepository.get(0);
        assertThat(u != null);
        assertThat(name.equals(u.getName()));
    }
}
