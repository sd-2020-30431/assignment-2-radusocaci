package com.wasteless.sd.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller()
@RequestMapping(value = "/auth")
public class SecurityController {
    @GetMapping(value = "/login")
    private String login() {
        return "login";
    }
}
