package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ReadTradeService {
    private final TradeRepository tradeRepository;

    public ReadTradeService(TradeRepository tradeRepository) {
        this.tradeRepository = tradeRepository;
    }

    public Collection<Trade> get(){
        return tradeRepository.getAllTrades();
    }

    public Trade get(Long id){
        return tradeRepository.getTradeById(id).orElse(null);
    }

    public Collection<Trade> filter(Optional<String> name, Optional<List<String>> types, Optional<Double> lng, Optional<Double> lat) {
        List<Trade> matchesFilters = get()
                .stream()
                .filter(trade -> trade.isMatchingFilters(name, types))
                .collect(Collectors.toList());
        return sortByProximity(matchesFilters, lng, lat);
    }

    private List<Trade> sortByProximity(List<Trade> source, Optional<Double> lng, Optional<Double> lat) {
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
}
