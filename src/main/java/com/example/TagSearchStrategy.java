package com.example;

import java.util.ArrayList;
import java.util.List;

class TagSearchStrategy implements RecipeSearchStrategy {

    @Override
    public List<Recipe> search(List<Recipe> recipes, String keyword) {
        List<Recipe> matchingRecipes = new ArrayList<>();

        for (Recipe recipe : recipes) {
            List<String> tags = recipe.getTags();

            if (containsIgnoreCase(tags, keyword)) {
                matchingRecipes.add(recipe);
            }
        }

        return matchingRecipes;
    }


    private boolean containsIgnoreCase(List<String> tags, String keyword) {
        for (String tag : tags) {
            if (tag.equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }
}
