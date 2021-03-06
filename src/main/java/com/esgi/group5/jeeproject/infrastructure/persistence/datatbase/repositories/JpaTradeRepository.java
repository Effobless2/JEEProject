package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.TradeDAO;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers.TradeParser;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers.TradeWithBeerParser;
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

    @Override
    default Trade createTrade(Trade trade){
        TradeDAO dao = TradeParser.parse(trade);
        TradeDAO saved = save(dao);
        return TradeParser.parse(saved);
    }

    @Override
    default Collection<Trade> getAllTrades(){
        return findAll()
                .stream()
                .map(TradeParser::parse)
                .collect(Collectors.toList());

    }

    @Override
    default Optional<Trade> getTradeById(Long id){
        Optional<TradeDAO> found = findById(id);
        if(found.isEmpty())
            return Optional.empty();
        return Optional.of(TradeParser.parse(found.get()));
    }

    @Override
    default void deleteTradeById(Long id){
        deleteById(id);
    }

    @Override
    default Optional<Trade> updateTrade(Trade trade){
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
        return getTradeById(trade.getId());
    }

    @Override
    default Optional<Trade> getTradeByIdWithBeers(Long tradeId) {
        Optional<TradeDAO> dao = findById(tradeId);
        if(dao.isEmpty())
            return Optional.empty();

        return Optional.of(TradeWithBeerParser.parse(dao.get()));
    }

    @Override
    default Collection<Trade> getAllTradeWithBeers() {
        Collection<TradeDAO> daos = findAll();
        return daos
                .stream()
                .map(TradeWithBeerParser::parse)
                .collect(Collectors.toList());
    }

    @Override
    default void updateTradeItems(Trade trade) {
        TradeDAO dao = TradeWithBeerParser.parse(trade);
        save(dao);
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
