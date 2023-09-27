package com.eis.apiusers.exceptions;

public class UserFoundException extends Exception{
    public UserFoundException() {
        super("El usuario con ese username ya existe en la base de datos, vuelva a intentar!");
    }
    public UserFoundException(String message) {
        super(message);
    }

}
