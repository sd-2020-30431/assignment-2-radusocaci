package com.wasteless.sd.Controller;

import com.wasteless.sd.Service.GroceryItemService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@CrossOrigin("*")
public class UserController {

    private final GroceryItemService groceryItemService;

    public UserController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        groceryItemService.checkWaste(user.getName());
        return user;
    }
}
