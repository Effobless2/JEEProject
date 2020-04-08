package com.esgi.group5.jeeproject.DAL;

import com.esgi.group5.jeeproject.models.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionDAL extends JpaRepository<Opinion, Long> {
}
