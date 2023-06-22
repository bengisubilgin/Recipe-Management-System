package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class RecipeRatingModule  {
    private Map<Recipe, List<RecipeRatingObserver>> observersMap;

    public RecipeRatingModule() {
        this.observersMap = new HashMap();
    }

    public void rateRecipe(Recipe recipe, int rating) {
        recipe.updateRating(rating);
        notifyObservers(recipe, rating);
    }

    public void addObserver(Recipe recipe, RecipeRatingObserver observer) {
        List<RecipeRatingObserver> observers = observersMap.getOrDefault(recipe, new ArrayList());
        observers.add(observer);
        observersMap.put(recipe, observers);
    }


    private void notifyObservers(Recipe recipe, int rating) {
        List<RecipeRatingObserver> observers = observersMap.get(recipe);
        if (observers != null) {
            for (RecipeRatingObserver observer : observers) {
                observer.updateRating(rating);
            }
        }
    }
}
