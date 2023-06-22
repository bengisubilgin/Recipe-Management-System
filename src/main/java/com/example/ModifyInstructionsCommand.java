package com.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ModifyInstructionsCommand implements Command {
    private final Recipe recipe;
    private final List<String> oldInstructions;
    private final List<String> newInstructions;

    public ModifyInstructionsCommand(Recipe recipe, List<String> newInstructions) {
        this.recipe = recipe;
        this.newInstructions = newInstructions;
        this.oldInstructions = new ArrayList(Collections.singleton(recipe.getInstructions()));
    }

    @Override
    public void execute() {
        recipe.setInstructions(newInstructions);
    }

    @Override
    public void undo() {
        recipe.setInstructions(oldInstructions);
    }
}
