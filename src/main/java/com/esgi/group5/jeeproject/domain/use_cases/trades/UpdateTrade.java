package com.esgi.group5.jeeproject.domain.use_cases.trades;

import com.esgi.group5.jeeproject.domain.exceptions.TradeDoesntExistException;
import com.esgi.group5.jeeproject.domain.exceptions.UserNotAllowedToUpdateTradeException;
import com.esgi.group5.jeeproject.domain.models.Trade;
import com.esgi.group5.jeeproject.domain.models.User;
import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import com.esgi.group5.jeeproject.domain.tools.ImageUploadService;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class UpdateTrade {
    private final TradeRepository tradeRepository;
    private final ImageUploadService imageUploadService;

    public UpdateTrade(TradeRepository tradeRepository, ImageUploadService imageUploadService) {
        this.tradeRepository = tradeRepository;
        this.imageUploadService = imageUploadService;
    }

    public Trade updateTrade(Trade trade, User user) throws UserNotAllowedToUpdateTradeException, TradeDoesntExistException {
        Optional<Trade> t = tradeRepository.getTradeById(trade.getId());
        if (t.isEmpty())
            throw new TradeDoesntExistException();
        if (user == null || user.getId() != t.get().getResponsible().getId())
            throw new UserNotAllowedToUpdateTradeException();
        return updateTrade(trade);
    }

    public Trade updateTrade(Trade trade, User user, MultipartFile image) throws UserNotAllowedToUpdateTradeException, TradeDoesntExistException {
        Optional<Trade> t = tradeRepository.getTradeById(trade.getId());
        if (t.isEmpty())
            throw new TradeDoesntExistException();
        if (user == null || user.getId() != t.get().getResponsible().getId())
            throw new UserNotAllowedToUpdateTradeException();
        Optional<String> imageUrl = imageUploadService.uploadImageTradeImage(image, trade.getId());
        imageUrl.ifPresent(trade::setProfilePict);
        return updateTrade(trade);
    }

    private Trade updateTrade(Trade trade) {
        Optional<Trade> updated = tradeRepository.updateTrade(trade);
        return updated.orElse(null);
    }
}
