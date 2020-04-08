package com.esgi.group5.jeeproject.repositories.users;

import com.esgi.group5.jeeproject.DAL.UserDAL;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public abstract class AUserRepositoryTest {
    protected UserDAL dal;
    protected IUserRepository userRepository;
    private String name = "test1";

    protected void setup(){
        dal = mock(UserDAL.class);
    }

    @Test
    void should_be_empty_after_init(){
        when(dal.findAll()).thenReturn(new ArrayList<>());
        assertThat(userRepository.get().isEmpty());
    }

    @Test
    void should_add_new_user_and_returns_new_user_id(){
        User u = new User();
        u.setName(name);
        u.setId((long) 1);
        when(dal.save(u)).thenReturn(u);
        long id = userRepository.add(u);
        assertEquals(u.getId(), id);
    }

    @Test
    void should_get_must_have_1_user_after_adding_1_user(){
        List<User> mock = new ArrayList<>();
        User u = new User();
        u.setName(name);
        u.setId((long) 1);
        mock.add(u);
        when(dal.save(u)).thenReturn(u);
        when(dal.findAll()).thenReturn(mock);

        userRepository.add(u);
        assertEquals(1, userRepository.get().size());
    }

    @Test
    void should_new_user_can_be_returned_by_get(){
        User u = new User();
        u.setName(name);
        u.setId((long) 1);
        when(dal.save(u)).thenReturn(u);

        long id = userRepository.add(u);

        when(dal.findById(id)).thenReturn(java.util.Optional.of(u));
        User u2 = userRepository.get(id);

        assertNotNull(u2);
        assertEquals(name, u2.getName());
    }
}
