package com.esgi.group5.jeeproject.DAL;

import com.esgi.group5.jeeproject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDAL extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.label = :label")
    Optional<Role> findRoleByLabel(@Param("label") String label);
}
