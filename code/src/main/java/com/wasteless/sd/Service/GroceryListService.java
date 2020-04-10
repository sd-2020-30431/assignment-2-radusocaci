package com.wasteless.sd.Service;

import com.wasteless.sd.Model.GroceryList;
import com.wasteless.sd.Repository.GroceryListRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroceryListService {
    private GroceryListRepository groceryListRepository;

    public GroceryListService(GroceryListRepository groceryListRepository) {
        this.groceryListRepository = groceryListRepository;
    }

    public void save(GroceryList groceryList) {
        groceryListRepository.save(groceryList);
    }

    public List<GroceryList> findAllByUsername(String userId) {
        return groceryListRepository.findAllByUsername(userId);
    }

    public Optional<GroceryList> findById(Integer listId) {
        return groceryListRepository.findById(listId);
    }

    public void deleteGroceryList(Integer id) {
        groceryListRepository.deleteById(id);
    }
}
