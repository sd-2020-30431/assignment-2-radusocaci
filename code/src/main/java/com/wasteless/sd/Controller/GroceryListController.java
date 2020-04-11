package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.GroceryList;
import com.wasteless.sd.Service.GroceryListService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
public class GroceryListController {
    private final GroceryListService groceryListService;

    public GroceryListController(GroceryListService groceryListService) {
        this.groceryListService = groceryListService;
    }

    @GetMapping("/grocery-lists")
    public List<GroceryList> getAllGroceryLists() {
//        return groceryListService.findAllByUsername(principal.getName());
        return groceryListService.findAll();
    }

    @DeleteMapping("/grocery-lists/{id}")
    public void deleteGroceryList(@PathVariable("id") Integer id) {
        groceryListService.deleteGroceryList(id);
    }

    @PostMapping("/grocery-lists")
    public GroceryList createGroceryList(@Valid @RequestBody GroceryList groceryList) {
        return groceryListService.save(groceryList);
    }
}
