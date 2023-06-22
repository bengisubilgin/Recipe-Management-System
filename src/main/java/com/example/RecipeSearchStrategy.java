package com.example;

import java.util.List;

interface RecipeSearchStrategy {
    List<Recipe> search(List<Recipe> recipes, String keyword);

}
