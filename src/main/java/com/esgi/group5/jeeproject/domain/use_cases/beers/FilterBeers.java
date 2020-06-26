package com.esgi.group5.jeeproject.domain.use_cases.beers;

import com.esgi.group5.jeeproject.domain.models.Beer;
import com.esgi.group5.jeeproject.domain.models.History;
import com.esgi.group5.jeeproject.domain.models.enums.HistorySearchType;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.domain.repositories.HistoryRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilterBeers {
    private final BeerRepository beerRepository;
    private final HistoryRepository historyRepository;

    public FilterBeers(BeerRepository beerRepository, HistoryRepository historyRepository) {
        this.beerRepository = beerRepository;
        this.historyRepository = historyRepository;
    }

    public Collection<Beer> execute(
            Optional<String> name,
            Optional<List<String>> types,
            Optional<Double> alcoholLevel) {
        Collection<Beer> result = beerRepository.getBeerWithSellers()
                .stream()
                .filter(beer -> beer.isMatchingFilters(name, types, alcoholLevel))
                .collect(Collectors.toList());
        saveIntoSearchingHistory(name, types, alcoholLevel, result.size());
        return result;
    }

    private boolean saveIntoSearchingHistory(
            Optional<String> name,
            Optional<List<String>> types,
            Optional<Double> alcoholLevel,
            int resultCount) {
        History history = new History();
        history.setType(HistorySearchType.Beer);
        history.setFields(getStringFromFields(name, types, alcoholLevel));
        history.setResultCount(resultCount);

        History saved = historyRepository.saveResearch(history);

        return saved != null;
    }

    private String getStringFromFields(Optional<String> name,
                                Optional<List<String>> types,
                                Optional<Double> alcoholLevel) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("name: ");
        stringBuilder.append(name.orElse("none"));
        stringBuilder.append("; ");
        stringBuilder.append("types: [");
        if (types.isPresent()) {
            List<String> typesList = types.get();
            for (int i = 0; i < typesList.size(); i++) {
                stringBuilder.append(typesList.get(i));
                if(i < typesList.size() - 1)
                    stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]; ");
        stringBuilder.append("alcohol Level: ");
        stringBuilder.append(alcoholLevel.isPresent() ? alcoholLevel.get().toString() : "none");
        stringBuilder.append("; ");

        return stringBuilder.toString();
    }
}
