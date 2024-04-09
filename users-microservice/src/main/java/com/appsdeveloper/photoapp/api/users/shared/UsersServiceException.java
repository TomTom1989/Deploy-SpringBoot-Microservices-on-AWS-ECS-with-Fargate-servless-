package com.appsdeveloper.photoapp.api.users.shared;


public class UsersServiceException extends RuntimeException {
    
    public UsersServiceException(){}
    
    public UsersServiceException(String message)
    {
        super(message);
    }
    
}
