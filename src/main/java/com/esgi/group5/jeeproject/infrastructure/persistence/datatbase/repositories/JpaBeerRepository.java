package com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.repositories;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.daos.BeerDAO;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers.BeerParser;
import com.esgi.group5.jeeproject.infrastructure.persistence.datatbase.parsers.BeerWithSellersParser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public interface JpaBeerRepository extends JpaRepository<BeerDAO, Long>, BeerRepository {

    default Beer createBeer(Beer beer) {
        BeerDAO dao = BeerParser.parse(beer);
        BeerDAO saved = save(dao);
        return BeerParser.parse(saved);
    }

    default Collection<Beer> getAllBeers() {
        Collection<BeerDAO> collection = findAll();
        return collection
                .stream()
                .map(BeerParser::parse)
                .collect(Collectors.toList());
    }
    default Optional<Beer> getBeerById(Long id) {
        Optional<BeerDAO> found = findById(id);
        return found.map(BeerParser::parse);
    }
    default void deleteBeerById(Long id) {
        deleteById(id);
    }
    default Optional<Beer> updateBeer(Beer beer) {
        if(beer.getId() == null)
            return Optional.empty();
        update(
                beer.getId(),
                beer.getName(),
                beer.getAlcoholLevel(),
                beer.getDescription(),
                beer.getType(),
                beer.getProfilePict());
        return getBeerById(beer.getId());
    }

    @Override
    default Optional<Beer> getBeerByIdWithSellers(Long beerId) {
        Optional<BeerDAO> dao = findById(beerId);

        if(dao.isEmpty())
            return Optional.empty();

        Beer result = BeerWithSellersParser.parse(dao.get());

        return Optional.of(result);

    }

    @Override
    default Collection<Beer> getBeerWithSellers() {
        Collection<BeerDAO> daos = findAll();
        return daos
                .stream()
                .map(BeerWithSellersParser::parse)
                .collect(Collectors.toList());

    }

    @Transactional
    @Modifying
    @Query(value = "UPDATE BeerDAO beer " +
            "SET beer.name = :name, " +
            "beer.alcoholLevel = :alcoholLevel," +
            "beer.description = :description," +
            "beer.type = :type," +
            "beer.profilePict = :profilePict " +
            "WHERE beer.id = :id")
    void update(Long id,
                String name,
                Double alcoholLevel,
                String description,
                String type,
                String profilePict
    );
}
