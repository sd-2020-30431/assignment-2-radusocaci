package com.wasteless.sd.Service;

import com.wasteless.sd.Model.Goal;
import com.wasteless.sd.Repository.GoalRepository;
import org.springframework.stereotype.Service;

@Service
public class GoalService {
    private GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public Goal getGoalByUsername(String username) {
        return goalRepository.findFirstByUsername(username).orElseGet(Goal::new);
    }

    public Goal save(Goal goal, String name) {
        goalRepository.findFirstByUsername(name).ifPresent(g -> goal.setId(g.getId()));
        goal.setUsername(name);
        return goalRepository.save(goal);
    }
}
