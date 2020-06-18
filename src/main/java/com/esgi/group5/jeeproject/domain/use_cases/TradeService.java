package com.esgi.group5.jeeproject.domain.use_cases;
/*
import com.esgi.group5.jeeproject.persistence.datatbase.daos.TradeDAO;
import com.esgi.group5.jeeproject.repositories.contracts.ITradeRepository;
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
    public long add(TradeDAO trade) {
        return repository.add(trade);
    }

    @Override
    public List<TradeDAO> get() {
        return repository.get();
    }

    @Override
    public TradeDAO get(long tradeId) {
        return repository.get(tradeId);
    }

    @Override
    public boolean update(TradeDAO trade) {
        return repository.update(trade);
    }

    @Override
    public List<TradeDAO> filter(Optional<String> name, Optional<List<String>> types, Optional<Double> lng, Optional<Double> lat) {
        List<TradeDAO> res = get().stream().filter((TradeDAO trade) -> {
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

        Comparator<TradeDAO> comparator = (o1, o2) -> {
            Double dist1 = Math.sqrt(Math.pow(lat.get()-o1.getLatitude(), 2) + Math.pow(lng.get()-o1.getLongitude(), 2));
            Double dist2 = Math.sqrt(Math.pow(lat.get()-o2.getLatitude(), 2) + Math.pow(lng.get()-o2.getLongitude(), 2));

            return dist1.compareTo(dist2);
        };

        res.sort(comparator);

        return res;
    }
}
*/