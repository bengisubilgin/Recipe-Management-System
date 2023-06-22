package com.example;

import java.util.List;

public interface Builder {
    RecipeBuilder setInstruction(List<String> instruction);
    RecipeBuilder setIngredient(List<String> ingredient);
    RecipeBuilder setServingSize(int size);
    RecipeBuilder setCategories(List<String>  categories);
    RecipeBuilder setName(String name);
    RecipeBuilder setTag(List<String> tags);
}
