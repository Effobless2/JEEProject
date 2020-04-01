package com.esgi.group5.jeeproject.repositories.users;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        int id = userRepository.add(new User(name));
        assertEquals(0, id);
    }

    @Test
    void should_get_must_have_1_user_after_adding_1_user(){
        int id = userRepository.add(new User(name));
        assertEquals(1, userRepository.get().size());
    }

    @Test
    void should_new_user_can_be_returned_by_get(){
        int id = userRepository.add(new User(name));
        User u = userRepository.get(id);
        assertNotNull(u);
        assertEquals(name, u.getName());
    }
}
