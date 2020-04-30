package com.esgi.group5.jeeproject.services.contracts;

import com.esgi.group5.jeeproject.models.Trade;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ITradeService {
    long add(Trade trade);
    List<Trade> get();
    Trade get(long tradeId);
    boolean updatePict(Trade trade, String url);
}
