package com.meghana.blogproject.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    private String resourcename;
    private String fieldname;
    private Long fieldvalue;
    public ResourceNotFoundException(String resourcename,String fieldname,Long fieldvalue){
        super(String.format("%s is not found with %s : '%s",resourcename,fieldname,fieldvalue));
        this.resourcename=resourcename;
        this.fieldname=fieldname;
        this.fieldvalue=fieldvalue;
    }

}
