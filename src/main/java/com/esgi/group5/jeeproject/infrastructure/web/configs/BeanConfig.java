package com.esgi.group5.jeeproject.infrastructure.web.configs;

import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;
import com.esgi.group5.jeeproject.domain.tools.ImageUploadService;
import com.esgi.group5.jeeproject.domain.use_cases.beers.*;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.domain.use_cases.trades.*;
import com.esgi.group5.jeeproject.domain.use_cases.users.ReadUser;
import com.esgi.group5.jeeproject.domain.use_cases.users.RegisterUser;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;

@Configuration
public class BeanConfig {

    @Autowired
    private Environment environment;

    @Bean
    public CloudBlobClient cloudBlobClient() throws URISyntaxException, StorageException, InvalidKeyException {
        CloudStorageAccount storageAccount = CloudStorageAccount.parse(environment.getProperty("azure.storage.ConnectionString"));
        return storageAccount.createCloudBlobClient();
    }

    @Bean
    public CloudBlobContainer testBlobContainer() throws URISyntaxException, StorageException, InvalidKeyException {
        return cloudBlobClient().getContainerReference(environment.getProperty("azure.storage.container.name"));
    }

    @Bean
    public CreateBeer createBeerService(BeerRepository beerRepository){
        return new CreateBeer(beerRepository);
    }

    @Bean
    public ReadBeer readBeerService(BeerRepository beerRepository){
        return new ReadBeer(beerRepository);
    }

    @Bean
    public UpdateBeer updateBeerService(BeerRepository beerRepository, ImageUploadService imageUploadService){
        return new UpdateBeer(imageUploadService, beerRepository);
    }

    @Bean
    public DeleteBeer deleteBeerService(BeerRepository beerRepository){
        return new DeleteBeer(beerRepository);
    }

    @Bean
    public CreateTrade createTradeService(TradeRepository tradeRepository) {
        return new CreateTrade(tradeRepository);
    }

    @Bean
    public ReadTrade readTradeService(TradeRepository tradeRepository) {
        return new ReadTrade(tradeRepository);
    }

    @Bean
    public UpdateTrade updateTradeService(TradeRepository tradeRepository, ImageUploadService imageUploadService) {
        return new UpdateTrade(tradeRepository, imageUploadService);
    }

    @Bean
    public DeleteTrade deleteTradeService(TradeRepository tradeRepository) {
        return new DeleteTrade(tradeRepository);
    }

    @Bean
    public RegisterUser registerUserService(UserRepository userRepository) {
        return new RegisterUser(userRepository);
    }

    @Bean
    public ReadUser readUserService(UserRepository userRepository) {
        return new ReadUser(userRepository);
    }

    @Bean
    public MakeBeerSoldByTrade makeBeerSoldByTrade(TradeRepository tradeRepository, BeerRepository beerRepository) {
        return new MakeBeerSoldByTrade(beerRepository, tradeRepository);
    }

    @Bean
    public RemoveBeerFromTradeItems removeBeerFromTradeItems(TradeRepository tradeRepository, BeerRepository beerRepository) {
        return new RemoveBeerFromTradeItems(beerRepository, tradeRepository);
    }

    @Bean
    public GetTradeByIdWithTheirBeers getTradeByIdWithTheirBeers(TradeRepository tradeRepository) {
        return new GetTradeByIdWithTheirBeers(tradeRepository);
    }

    @Bean
    public GetBeerByIdWithTheirSellers getBeerByIdWithTheirSellers(BeerRepository beerRepository) {
        return new GetBeerByIdWithTheirSellers(beerRepository);
    }
}