package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import com.esgi.group5.jeeproject.domain.tools.ImageUploadService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class UpdateTradeService {
    private final TradeRepository tradeRepository;
    private final ImageUploadService imageUploadService;

    public UpdateTradeService(TradeRepository tradeRepository, ImageUploadService imageUploadService) {
        this.tradeRepository = tradeRepository;
        this.imageUploadService = imageUploadService;
    }

    public Trade updateTrade(Trade trade, User user) {
        //TODO: Check if user is responsible of the trade
        return updateTrade(trade);
    }

    public Trade updateTrade(Trade trade, User user, MultipartFile image) {
        //TODO: Check if user is responsible of the trade
        Optional<String> imageUrl = imageUploadService.uploadImageTradeImage(image, trade.getId());
        imageUrl.ifPresent(trade::setProfilePict);
        return updateTrade(trade);
    }

    private Trade updateTrade(Trade trade) {
        Optional<Trade> updated = tradeRepository.updateTrade(trade);
        return updated.orElse(null);
    }
}
