package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ModifyIngredientsCommand implements Command {
    private final Recipe recipe;
    private final List<String> oldIngredients;
    private final List<String> newIngredients;

    public ModifyIngredientsCommand(Recipe recipe, List<String> newIngredients) {
        this.recipe = recipe;
        this.newIngredients = newIngredients;
        this.oldIngredients = new ArrayList(Collections.singleton(recipe.getIngredients()));
    }

    @Override
    public void execute() {
        recipe.setIngredients(newIngredients);
    }

    @Override
    public void undo() {
        recipe.setIngredients(oldIngredients);
    }
}

