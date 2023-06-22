package com.example;

import java.util.ArrayList;
import java.util.List;

class ModifyCategoriesCommand implements Command {
    private Recipe recipe;
    private List<String> oldCategories;
    private List<String> newCategories;

    public ModifyCategoriesCommand(Recipe recipe, List<String> newCategories) {
        this.recipe = recipe;
        this.newCategories = newCategories;
        this.oldCategories = new ArrayList(recipe.getCategories());

    }

    @Override
    public void execute() {
        recipe.setCategories(newCategories);
    }

    @Override
    public void undo() {
        recipe.setCategories(oldCategories);
    }
}

