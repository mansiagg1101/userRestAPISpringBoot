package com.pnc.user.registration.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.pnc.user.registration.ValidPassword;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class UserDTO {
    @NonNull
    @NotBlank(message = "Username cannot be blank!")
    private  String userName;

    
    @NonNull
    @NotBlank(message = "IP cannot be blank!")
    private String ipAddress;
    
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }


    @ValidPassword
    @JsonProperty(access = Access.WRITE_ONLY)
    @NonNull
    @NotBlank(message = "Password cannot be blank!")
    private  String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }
    

    public void setPassword(String password) {
        this.password = password;
    }

    

    

    
    
}
