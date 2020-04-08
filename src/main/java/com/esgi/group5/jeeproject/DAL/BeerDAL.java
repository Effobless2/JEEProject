package com.esgi.group5.jeeproject.DAL;

import com.esgi.group5.jeeproject.models.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerDAL extends JpaRepository<Beer, Long> {
}
