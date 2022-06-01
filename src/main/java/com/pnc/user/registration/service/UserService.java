package com.pnc.user.registration.service;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.pnc.user.registration.model.UserDTO;

import org.springframework.stereotype.Service;

@Service
public class UserService {
    private DatabaseReader dbReader;

    public UserService() throws IOException {
        File database = new File("/Users/mansi.agarwal/Practice/GeoLite2-City_20220527/GeoLite2-City.mmdb");
        dbReader = new DatabaseReader.Builder(database).build();
    }

    // public UserDTO getUserDetails(String ip, String username, String password)
    // throws IOException, GeoIp2Exception {
    // InetAddress inetAddress = InetAddress.getByName(ip);
    // CityResponse response = dbReader.city(inetAddress);
    // UserDTO user = new UserDTO();
    // user.setCityName(response.getCity().getName());
    // user.setCountry(response.getCountry().getName()); ;
    // user.setUserName(username);
    // user.setPassword(password);
    // return user ;
    // }

    public String getUserCity(String ip) throws IOException, GeoIp2Exception {
        InetAddress inetAddress = InetAddress.getByName(ip);
        CityResponse response = dbReader.city(inetAddress);

        // user.setCityName(response.getCity().getName());
        // user.setCountry(response.getCountry().getName()); ;

        return response.getCountry().getName().equals("Canada") ? response.getCity().getName() : null;
    }

}
