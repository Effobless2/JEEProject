package com.esgi.group5.jeeproject.services;

import com.esgi.group5.jeeproject.models.Trade;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
import com.esgi.group5.jeeproject.services.contracts.ITradeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TradeService implements ITradeService {
    private final ITradeRepository repository;

    @Override
    public long add(Trade trade) {
        return repository.add(trade);
    }

    @Override
    public List<Trade> get() {
        return repository.get();
    }

    @Override
    public Trade get(long tradeId) {
        return repository.get(tradeId);
    }

    @Override
    public boolean update(Trade trade) {
        return repository.update(trade);
    }

    @Override
    public List<Trade> filter(Optional<String> name, Optional<List<String>> types, Optional<Double> lng, Optional<Double> lat) {
        List<Trade> res = get().stream().filter((Trade trade) -> {
            Pattern pattern = Pattern.compile(".*\\Q" + trade.getType().toLowerCase() + "\\E.*");
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
                            trade.getName().toLowerCase().contains(name.get().toLowerCase())
            ) &&
                    (
                            matches == null || //No types requested
                                    matches.size() > 0 //Types requested but not matches
                    );
        }).collect(Collectors.toList());

        if(lng.isEmpty() || lat.isEmpty()) {
            return res;
        }

        Comparator<Trade> comparator = (o1, o2) -> {
            Double dist1 = Math.sqrt(Math.pow(lat.get()-o1.getLattitude(), 2) + Math.pow(lng.get()-o1.getLongitude(), 2));
            Double dist2 = Math.sqrt(Math.pow(lat.get()-o2.getLattitude(), 2) + Math.pow(lng.get()-o2.getLongitude(), 2));

            return dist1.compareTo(dist2);
        };

        res.sort(comparator);

        return res;
    }
}
