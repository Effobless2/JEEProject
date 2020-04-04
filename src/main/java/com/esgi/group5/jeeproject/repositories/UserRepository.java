package com.esgi.group5.jeeproject.repositories;

import com.esgi.group5.jeeproject.DAL.UserRepositoryDAL;
import com.esgi.group5.jeeproject.DAO.UserDAO;
import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.repositories.contracts.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserRepository implements IUserRepository {

    @Autowired
    private UserRepositoryDAL userRepositoryDAL;

    private List<User> db;

   /* @Override
    public int add(User user) {
        db.add(user);
        return db.size() - 1;
    }*/

    @Override
    public int add(User user) {
        UserDAO userDAO = modelToDao(user);
        userRepositoryDAL.save(userDAO);
        return db.size() - 1;
    }

    private UserDAO modelToDao(User userModel){
        UserDAO userDAO = new UserDAO();
        userDAO.setName(userModel.getName());
        userDAO.setEmail(userModel.getEmail());
        userDAO.setPassword(userModel.getPassword());
        return userDAO;
    }

    public UserRepository() {
        db = new ArrayList<>();
    }

    @Override
    public List<User> get() {
        return db;
    }

    @Override
    public User get(int id) {
        return db.get(id);
    }
}
