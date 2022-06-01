package com.pnc.user.registration.controller;

import java.io.IOException;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.pnc.user.registration.model.UserDTO;
import com.pnc.user.registration.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserDTO userDTO;

    @Autowired
    private UserService service;

    @RequestMapping(value = "/user", method = RequestMethod.GET, produces = "application/json")
    public void doGet(HttpServletResponse response, @RequestParam String ipAddress, @RequestParam String userName,
            @RequestParam String password) throws IOException, GeoIp2Exception {
        try {
            logger.info("User Registration Get Mapping API called");
            response.setContentType("text/html");
            response.setCharacterEncoding("UTF-8");
            if (!isValidInput(userName) && !isValidInput(password)) {
                response.getWriter().append("Username or Password cannot be empty or blank");
            } else if (!isValidPassword(password)) {
                logger.info("isValidPassword:" + isValidPassword(password));
                response.getWriter().append("Password did not meet the valid password strength");
            } else {
                userDTO.setIpAddress(ipAddress);
                userDTO.setUserName(userName);
                String cityName = service.getUserCity(ipAddress);

                if (cityName == null)
                    response.getWriter().append("User is not eligible to Register");
                else
                    response.getWriter().append(UUID.randomUUID().toString() + " Hello " + userDTO.getUserName()
                            + " from " + cityName + ".");
            }
        } catch (IOException e) {
            logger.error("IO Exception occured with msg:", e.getMessage());
        } catch (GeoIp2Exception e) {
            logger.error("IP address is not valid:", e.getMessage());
        }

    }

    public boolean isValidInput(String input) {
        return input != null && !input.isEmpty() && !input.equals("null");
    }

    public boolean isValidPassword(String password) {
        logger.info("Password: " + password);
        String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.{1,}[_#$%.]).{9,}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

}
