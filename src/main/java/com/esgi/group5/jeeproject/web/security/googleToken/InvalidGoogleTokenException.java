package com.esgi.group5.jeeproject.web.security.googleToken;

public class InvalidGoogleTokenException extends Exception {
    public InvalidGoogleTokenException(String token){
        super("Following Google Token is Invalid : " + token);
    }
}
