package com.pnc.user.registration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    UserService userService;

    @Test
    public void getUserCityTest() throws IOException, GeoIp2Exception {
        String city1 = userService.getUserCity("75.152.221.251");
        String city2 = userService.getUserCity("136.49.57.29");

        assertEquals(city1, "Medicine Hat");
        assertNotEquals(city2, "Medicine Hat");
        // assertNotEquals(city2, "Medicine Hat");
    }

}
