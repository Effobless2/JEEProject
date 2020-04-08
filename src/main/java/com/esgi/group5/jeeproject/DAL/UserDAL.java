package com.esgi.group5.jeeproject.DAL;

import com.esgi.group5.jeeproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAL extends JpaRepository<User, Long> {
}