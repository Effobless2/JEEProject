package com.esgi.group5.jeeproject.infrastructure.web.security.googleToken;

public class InvalidGoogleTokenException extends Exception {
    public InvalidGoogleTokenException(String token){
        super("Following Google Token is Invalid : " + token);
    }
}
