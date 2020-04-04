package com.esgi.group5.jeeproject.DAL;

import com.esgi.group5.jeeproject.DAO.UserDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryDAL extends JpaRepository<UserDAO, Long> {

}