package com.example;

import java.util.ArrayList;
import java.util.List;

class IngredientSearchStrategy implements RecipeSearchStrategy {
    @Override
    public List<Recipe> search(List<Recipe> recipes, String keyword) {
        List<Recipe> matchingRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            List<String> ingredients = recipe.getIngredients();

            if (containsIgnoreCase(ingredients, keyword)) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }

    private boolean containsIgnoreCase(List<String> ingredients, String keyword) {
        for (String ingredient : ingredients) {
            if (ingredient.equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
}
