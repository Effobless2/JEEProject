package com.esgi.group5.jeeproject.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import com.esgi.group5.jeeproject.persistence.datatbase.daos.TradeDAO;
import com.esgi.group5.jeeproject.persistence.datatbase.parsers.TradeParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface JpaTradeRepository extends JpaRepository<TradeDAO, Long>, TradeRepository {

    default Trade create(Trade trade){
        TradeDAO dao = TradeParser.parse(trade);
        TradeDAO saved = save(dao);
        return TradeParser.parse(saved);
    }
    default Collection<Trade> get(){
        return findAll()
                .stream()
                .map(TradeParser::parse)
                .collect(Collectors.toList());

    }
    default Optional<Trade> get(Long id){
        Optional<TradeDAO> found = findById(id);
        if(found.isEmpty())
            return Optional.empty();
        return Optional.of(TradeParser.parse(found.get()));
    }
    default void delete(Long id){
        deleteById(id);
    }
    default Optional<Trade> update(Trade trade){
        if(trade.getId() == null)
            return Optional.empty();
        update(
            trade.getId(),
            trade.getName(),
            trade.getProfilePict(),
            trade.getType(),
            trade.getLongitude(),
            trade.getLatitude(),
            trade.getAddress(),
            trade.getDescription()
        );
        return get(trade.getId());
    }

    @Transactional
    @Modifying
    @Query(value = "UPDATE TradeDAO trade " +
            "SET trade.name = :name," +
            "trade.profilePict = :profilePict," +
            "trade.type = :beertype," +
            "trade.longitude = :longitude," +
            "trade.latitude = :latitude," +
            "trade.address = :address," +
            "trade.description = :description " +
            "WHERE trade.id = :id")
    void update(Long id,
                String name,
                String profilePict,
                String beertype,
                Double longitude,
                Double latitude,
                String address,
                String description);
}
