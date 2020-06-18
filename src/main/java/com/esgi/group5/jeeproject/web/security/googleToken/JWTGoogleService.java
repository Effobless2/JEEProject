package com.esgi.group5.jeeproject.web.security.googleToken;

import com.esgi.group5.jeeproject.web.dtos.users.GoogleAccountDTO;
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
import java.util.Optional;

@Service
public class JWTGoogleService {
    @Value("${googleOAuth.clientId}")
    private String CLIENT_ID;

    public Optional<GoogleAccountDTO> getGoogleAccountFromToken(String token) {
        GoogleIdTokenVerifier verifier = initVerifier();
        try {
            GoogleIdToken idToken = verifyToken(verifier, token);
            return Optional.of(convertGoogleTokenToUser(idToken));
        } catch (GeneralSecurityException | IOException | InvalidGoogleTokenException e) {
            e.printStackTrace();
            return Optional.empty();
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    private GoogleIdToken verifyToken(GoogleIdTokenVerifier verifier, String token) throws InvalidGoogleTokenException, GeneralSecurityException, IOException {
        GoogleIdToken idToken = verifier.verify(token);
        if (idToken != null)
            return idToken;
        throw new InvalidGoogleTokenException(token);
    }

    private GoogleIdTokenVerifier initVerifier() {
        HttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = new JacksonFactory();
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .build();
        return verifier;
    }

    public GoogleAccountDTO convertGoogleTokenToUser(GoogleIdToken googleIdToken) {
        GoogleAccountDTO user = new GoogleAccountDTO();
        GoogleIdToken.Payload payload = googleIdToken.getPayload();

        user.setGoogleId(payload.getSubject());
        user.setName((String) googleIdToken.getPayload().get("name"));
        user.setEmail(googleIdToken.getPayload().getEmail());
        user.setAvatarUrl((String) googleIdToken.getPayload().get("picture"));

        return user;
    }
}
