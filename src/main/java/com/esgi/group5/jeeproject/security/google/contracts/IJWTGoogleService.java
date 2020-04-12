package com.esgi.group5.jeeproject.security.google.contracts;

import com.esgi.group5.jeeproject.models.User;
import com.esgi.group5.jeeproject.security.google.InvalidGoogleTokenException;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

public interface IJWTGoogleService {
    GoogleIdToken tokenVerification(String substring) throws InvalidGoogleTokenException;

    User convertGoogleTokenToUser(GoogleIdToken googleIdToken);
}
