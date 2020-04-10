package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.Goal;
import com.wasteless.sd.Model.GroceryList;
import com.wasteless.sd.Service.GoalService;
import com.wasteless.sd.Service.GroceryListService;
import com.wasteless.sd.Service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class GroceryListController {
    private GroceryListService groceryListService;
    private GoalService goalService;
    private NotificationService notificationService;

    public GroceryListController(GroceryListService groceryListService, GoalService goalSerive, NotificationService notificationService) {
        this.groceryListService = groceryListService;
        this.goalService = goalSerive;
        this.notificationService = notificationService;
    }

    @GetMapping("/grocery-lists")
    public String getAllGroceryLists(Principal principal, Model model) {
        GroceryList groceryList = new GroceryList();
        groceryList.setUsername(principal.getName());
        model.addAttribute("addList", groceryList);
        model.addAttribute("message", notificationService.getMessage());
        model.addAttribute("goal", goalService.getGoalByUsername(principal.getName()));
        model.addAttribute("groceryLists", groceryListService.findAllByUsername(principal.getName()));
        return "home";
    }

    @RequestMapping("/grocery-lists/delete/{id}")
    public String deleteGroceryList(@PathVariable("id") Integer id) {
        groceryListService.deleteGroceryList(id);
        return "redirect:/grocery-lists";
    }

    @PostMapping(path = "/create-list")
    public String createGroceryList(@Valid GroceryList groceryList, Principal principal) {
        groceryListService.save(groceryList);
        return "redirect:/grocery-lists";
    }

    @PostMapping(path = "/goal")
    public String createGoal(@Valid Goal goal, Principal principal) {
        goalService.save(goal, principal.getName());
        return "redirect:/grocery-lists";
    }
}
