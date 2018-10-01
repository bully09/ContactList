package org.contact.list.controller;

import org.contact.list.entities.ContactUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Base64;

@CrossOrigin(origins = "*")
@RestController
public class UserController {

    @Value("${app.spring.security.user}")
    String username;

    @Value("${app.spring.security.password}")
    String password;

    @PostMapping("/login")
    public boolean login(@RequestBody ContactUser user) {
        return (user != null && username.equalsIgnoreCase(user.getUsername()) && password.equalsIgnoreCase(user.getPassword()));
    }

    @GetMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization")
                .substring("Basic".length()).trim();
        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }

}
