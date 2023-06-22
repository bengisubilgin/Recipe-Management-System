package com.example;
import java.util.ArrayList;
import java.util.List;

class CategorySearchStrategy implements RecipeSearchStrategy {

    @Override
    public List<Recipe> search(List<Recipe> recipes, String keyword) {
        List<Recipe> matchingRecipes = new ArrayList();

        for (Recipe recipe : recipes) {
            List<String> categories = recipe.getCategories();

            if(containsIgnoreCase(categories,keyword)){
                matchingRecipes.add(recipe);
            }

        }
        return matchingRecipes;
    }

    private boolean containsIgnoreCase(List<String> categories, String keyword) {
        for(String category : categories){
            if(category.equalsIgnoreCase(keyword)){
                return true;
            }
        }
        return false;
    }
}
