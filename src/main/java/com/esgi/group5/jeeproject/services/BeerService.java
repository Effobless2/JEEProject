package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Beer;
import com.esgi.group5.jeeproject.repositories.contracts.IBeerRepository;
import com.esgi.group5.jeeproject.services.contracts.IBeerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BeerService implements IBeerService {
    private final IBeerRepository repository;

    @Override
    public long add(Beer beer) {
        return repository.add(beer);
    }

    @Override
    public List<Beer> get() {
        return repository.get();
    }

    @Override
    public Beer get(long id) {
        return repository.get(id);
    }

    @Override
    public boolean update(Beer beer) {
        return repository.update(beer);
    }

    @Override
    public boolean delete(long beerId) {
        return repository.delete(beerId);
    }

    @Override
    public List<Beer> filter(Optional<String> name, Optional<List<String>> types, Optional<Double> alcoholLevel) {
        return get().stream().filter((Beer beer) -> {
            Pattern pattern = Pattern.compile(".*\\Q" + beer.getType().toLowerCase() + "\\E.*");
            List<String> matches = null;
            if(types.isPresent() && types.get().size() > 0) {
                matches = new ArrayList<>(); //List of types is not empty, so we are looking for specific types
                for (String type: types.get()){
                    if(pattern.matcher(type.toLowerCase()).matches()) {
                        matches.add(type);
                        break;
                    }
                }
            }
            return  (
                        name.isEmpty() || //No name requested
                        beer.getName().toLowerCase().contains(name.get().toLowerCase())
                    ) &&
                    (
                        matches == null || //No types requested
                        matches.size() > 0 //Types requested but not matches
                    ) &&
                    (
                        alcoholLevel.isEmpty() || //No alcoholLevel requested
                        (
                            beer.getAlcoholLevel() >= alcoholLevel.get() - 1.0d &&
                            beer.getAlcoholLevel() <= alcoholLevel.get() + 1.0d
                        ) //Approximation
                    );
        }).collect(Collectors.toList());
    }
}
