package com.esgi.group5.jeeproject.security;

public class InvalidGoogleTokenException extends Exception {
    private String token;

    public InvalidGoogleTokenException(String token){
        super("Following Google Token is Invalid : " + token);
    }
}
