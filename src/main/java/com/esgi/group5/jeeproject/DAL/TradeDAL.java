package com.esgi.group5.jeeproject.DAL;

import com.esgi.group5.jeeproject.models.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeDAL extends JpaRepository<Trade, Long> {
}
