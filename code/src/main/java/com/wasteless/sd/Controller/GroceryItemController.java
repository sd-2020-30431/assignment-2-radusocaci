package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.GroceryListItem;
import com.wasteless.sd.Service.GroceryItemService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("*")
public class GroceryItemController {
    private final GroceryItemService groceryItemService;

    public GroceryItemController(GroceryItemService groceryItemService) {
        this.groceryItemService = groceryItemService;
    }

    @GetMapping("/grocery-items/{listId}")
    public List<GroceryListItem> getAllItemsByListId(@PathVariable Integer listId) {
        return groceryItemService.findByListId(listId);
    }

    @PostMapping(path = "/grocery-items/{listId}")
    public GroceryListItem createGroceryList(@Valid @RequestBody GroceryListItem groceryItem,
                                             @PathVariable(value = "listId") Integer listId,
                                             Principal principal) {
        return groceryItemService.save(groceryItem, listId, principal.getName());
    }

    @DeleteMapping("/grocery-items/{id}")
    public void deleteGroceryList(@PathVariable("id") Integer id, Principal principal) {
        groceryItemService.deleteGroceryItem(id, principal.getName());
    }
}
