package com.blogapp.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)//status 404 not found
public class ResourceNotFoundException extends RuntimeException{ //remember to use runtimeexception
    public ResourceNotFoundException(long id){ //constructure  
        super("Resource Not Found By Id: "+id);//calling its parent constructure...
    }
}
