package org.contact.list.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Map;

@CrossOrigin(origins="*")
@RestController
public class IndexController {

    @RequestMapping("/")
    public String index() {
        return "Contact list api is running..!";
    }

}
