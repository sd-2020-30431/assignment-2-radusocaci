package com.wasteless.sd.Service;

import com.wasteless.sd.Model.Goal;
import com.wasteless.sd.Model.GroceryListItem;
import com.wasteless.sd.Model.NotificationEvent;
import com.wasteless.sd.Repository.GroceryItemRepository;
import com.wasteless.sd.Repository.GroceryListRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class GroceryItemService {
    private GroceryItemRepository groceryItemRepository;
    private GroceryListRepository groceryListRepository;
    private GoalService goalService;

    private ApplicationEventPublisher applicationEventPublisher;

    public GroceryItemService(GroceryItemRepository groceryItemRepository,
                              GroceryListRepository groceryListRepository,
                              GoalService goalService,
                              ApplicationEventPublisher applicationEventPublisher) {
        this.groceryItemRepository = groceryItemRepository;
        this.groceryListRepository = groceryListRepository;
        this.goalService = goalService;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public List<GroceryListItem> findByListId(Integer listId) {
        return groceryItemRepository.findByGroceryListId(listId);
    }

    public void save(GroceryListItem groceryListItem, Integer listId, String name) {
        groceryListRepository.findById(listId).map(list -> {
            groceryListItem.setGroceryList(list);
            List<GroceryListItem> listItems = findByListId(listId);
            List<String> listItemNames = listItems
                    .stream()
                    .map(GroceryListItem::getName)
                    .collect(Collectors.toList());
            int i = listItemNames.indexOf(groceryListItem.getName());
            if (i != -1) {
                listItems.stream()
                        .filter(item -> item.getName().equals(listItemNames.get(i)))
                        .findFirst()
                        .ifPresent(item -> groceryListItem.setId(item.getId()));
            }
            return groceryItemRepository.save(groceryListItem);
        });

        checkWaste(name);
    }

    public void deleteGroceryItem(Integer id, String name) {
        groceryItemRepository.deleteById(id);
        checkWaste(name);
    }

    public GroceryListItem findById(Integer id) {
        return groceryItemRepository.findById(id).get();
    }

    private void checkWaste(String name) {
        Date now = Date.from(ZonedDateTime.now().toInstant());
        Goal goal = goalService.getGoalByUsername(name);

        List<GroceryListItem> groceryItems = groceryListRepository.findAllByUsername(name)
                .stream()
                .map(list -> groceryItemRepository.findByGroceryListId(list.getId()))
                .flatMap(List::stream)
                .filter(item -> item.getExpirationDate().after(now) && item.getConsumptionDate() == null)
                .collect(Collectors.toList());

        int dailyConsumption = groceryItems
                .stream()
                .mapToInt(item -> {
                    long diffMillis = Math.abs(now.getTime() - item.getExpirationDate().getTime());
                    return (int) (item.getCalorieValue() / TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS));
                })
                .reduce(0, Integer::sum);

        String newValue = (goal.getCaloriesPerDay() >= dailyConsumption) ? null : "The goal is too tight! Consider donating...";

        applicationEventPublisher.publishEvent(new NotificationEvent(this, newValue));
    }
}
