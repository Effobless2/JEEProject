package com.esgi.group5.jeeproject.security.google;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.security.google.contracts.IJWTGoogleService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class JWTGoogleService implements IJWTGoogleService {
    @Value("${googleOAuth.clientId}")
    private String CLIENT_ID;

    public GoogleIdToken tokenVerification(String token) throws InvalidGoogleTokenException {
        HttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = new JacksonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        try {
            GoogleIdToken idToken = verifier.verify(token);
            if (idToken != null)
                return idToken;
            throw new InvalidGoogleTokenException(token);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User convertGoogleTokenToUser(GoogleIdToken googleIdToken){
        User user = new User();
        GoogleIdToken.Payload payload = googleIdToken.getPayload();

        user.setGoogleId(payload.getSubject());
        user.setName((String) googleIdToken.getPayload().get("name"));
        user.setEmail(googleIdToken.getPayload().getEmail());
        user.setAvatarUrl((String) googleIdToken.getPayload().get("picture"));

        return user;
    }
}
