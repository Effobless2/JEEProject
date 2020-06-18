package com.esgi.group5.jeeproject.web.configs;

import com.esgi.group5.jeeproject.domain.repositories.TradeRepository;
import com.esgi.group5.jeeproject.domain.repositories.UserRepository;
import com.esgi.group5.jeeproject.domain.tools.ImageUploadService;
import com.esgi.group5.jeeproject.domain.use_cases.beers.CreateBeerService;
import com.esgi.group5.jeeproject.domain.repositories.BeerRepository;
import com.esgi.group5.jeeproject.domain.use_cases.beers.DeleteBeerService;
import com.esgi.group5.jeeproject.domain.use_cases.beers.ReadBeerService;
import com.esgi.group5.jeeproject.domain.use_cases.beers.UpdateBeerService;
import com.esgi.group5.jeeproject.domain.use_cases.trades.CreateTradeService;
import com.esgi.group5.jeeproject.domain.use_cases.trades.DeleteTradeService;
import com.esgi.group5.jeeproject.domain.use_cases.trades.ReadTradeService;
import com.esgi.group5.jeeproject.domain.use_cases.trades.UpdateTradeService;
import com.esgi.group5.jeeproject.domain.use_cases.users.ReadUserService;
import com.esgi.group5.jeeproject.domain.use_cases.users.RegisterUserService;
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
    public CreateBeerService createBeerService(BeerRepository beerRepository){
        return new CreateBeerService(beerRepository);
    }

    @Bean
    public ReadBeerService readBeerService(BeerRepository beerRepository){
        return new ReadBeerService(beerRepository);
    }

    @Bean
    public UpdateBeerService updateBeerService(BeerRepository beerRepository, ImageUploadService imageUploadService){
        return new UpdateBeerService(imageUploadService, beerRepository);
    }

    @Bean
    public DeleteBeerService deleteBeerService(BeerRepository beerRepository){
        return new DeleteBeerService(beerRepository);
    }

    @Bean
    public CreateTradeService createTradeService(TradeRepository tradeRepository) {
        return new CreateTradeService(tradeRepository);
    }

    @Bean
    public ReadTradeService readTradeService(TradeRepository tradeRepository) {
        return new ReadTradeService(tradeRepository);
    }

    @Bean
    public UpdateTradeService updateTradeService(TradeRepository tradeRepository, ImageUploadService imageUploadService) {
        return new UpdateTradeService(tradeRepository, imageUploadService);
    }

    @Bean
    public DeleteTradeService deleteTradeService(TradeRepository tradeRepository) {
        return new DeleteTradeService(tradeRepository);
    }

    @Bean
    public RegisterUserService registerUserService(UserRepository userRepository) {
        return new RegisterUserService(userRepository);
    }

    @Bean
    public ReadUserService readUserService(UserRepository userRepository) {
        return new ReadUserService(userRepository);
    }

}