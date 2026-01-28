package com.BackEndArena.bea.exception;

import javax.naming.AuthenticationException;

public class UnauthorisedException extends AuthenticationException {


    public UnauthorisedException(String message) {
        super(message);
    }
}
