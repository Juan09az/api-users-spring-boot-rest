package com.eis.apiusers.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException() {
        super("El usuario con ese username no existe en la base de datos, vuelva a intentar!");
    }
    public UserNotFoundException(String message) {
        super(message);
    }
}
