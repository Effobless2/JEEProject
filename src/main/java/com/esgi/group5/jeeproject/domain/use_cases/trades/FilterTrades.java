package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.History;
import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.enums.HistorySearchType;
import com.esgi.group5.jeeproject.domain.repositories.HistoryRepository;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FilterTrades {
    private final TradeRepository tradeRepository;
    private final HistoryRepository historyRepository;

    public FilterTrades(TradeRepository tradeRepository, HistoryRepository historyRepository) {
        this.tradeRepository = tradeRepository;
        this.historyRepository = historyRepository;
    }

    public Collection<Trade> execute(
            Optional<String> name,
            Optional<List<String>> types,
            Optional<Double> lng,
            Optional<Double> lat) {
        List<Trade> matchesFilters = tradeRepository.getAllTrades()
                .stream()
                .filter(trade -> trade.isMatchingFilters(name, types))
                .collect(Collectors.toList());

        saveIntoSearchingHistory(name, types, lng, lat, matchesFilters.size());

        return sortByProximity(matchesFilters, lng, lat);
    }

    private List<Trade> sortByProximity(
            List<Trade> source,
            Optional<Double> lng,
            Optional<Double> lat) {
        if(lng.isEmpty() || lat.isEmpty()) {
            return source;
        }
        Comparator<Trade> comparator = (o1, o2) -> {
            Double dist1 = Math.sqrt(Math.pow(lat.get()-o1.getLatitude(), 2) + Math.pow(lng.get()-o1.getLongitude(), 2));
            Double dist2 = Math.sqrt(Math.pow(lat.get()-o2.getLatitude(), 2) + Math.pow(lng.get()-o2.getLongitude(), 2));
            return dist1.compareTo(dist2);
        };
        source.sort(comparator);
        return source;
    }

    private boolean saveIntoSearchingHistory(
            Optional<String> name,
            Optional<List<String>> types,
            Optional<Double> lng,
            Optional<Double> lat,
            int resultCount) {
        History history = new History();
        history.setType(HistorySearchType.Trade);
        history.setFields(getStringFromFields(name, types, lng, lat));
        history.setResultCount(resultCount);

        History saved = historyRepository.saveResearch(history);

        return saved != null;
    }

    private String getStringFromFields(
            Optional<String> name,
            Optional<List<String>> types,
            Optional<Double> lng,
            Optional<Double> lat) {

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
        stringBuilder.append("latitude: ");
        stringBuilder.append(lat.isPresent() ? lat.get().toString() : "none");
        stringBuilder.append("; ");
        stringBuilder.append("longitude: ");
        stringBuilder.append(lng.isPresent() ? lng.get().toString() : "none");
        stringBuilder.append("; ");

        return stringBuilder.toString();
    }
}
