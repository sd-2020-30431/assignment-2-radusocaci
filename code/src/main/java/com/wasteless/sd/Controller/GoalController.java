package com.wasteless.sd.Controller;

import com.wasteless.sd.Model.Goal;
import com.wasteless.sd.Service.GoalService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class GoalController {
    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PostMapping("/goal")
    public Goal createGoal(@Valid @RequestBody Goal goal) {
        return goalService.save(goal, "user");
    }
}
