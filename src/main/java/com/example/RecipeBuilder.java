package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class RecipeBuilder implements Builder {
    private final Stack<Command> executedCommands;
    private List<String> ingredients;
    private List<String> instructions;
    private int servingSize;
    private List<String> categories;
    private List<String> tags;
    private String name;

    public RecipeBuilder() {
        ingredients = new ArrayList<>();
        instructions= new ArrayList<>();
        categories = new ArrayList();
        tags = new ArrayList();
        executedCommands = new Stack();

    }
    @Override
    public RecipeBuilder setInstruction(List<String> instruction) {
        this.instructions = instruction;
        return this;
    }

    @Override
    public RecipeBuilder setIngredient(List<String> ingredient) {
        this.ingredients = ingredient;
        return this;
    }

    @Override
    public RecipeBuilder setServingSize(int size) {
        this.servingSize = size;
        return this;
    }
    @Override
    public RecipeBuilder setCategories(List<String>  categories) {
        this.categories = categories;
        return this;
    }
    @Override
    public RecipeBuilder setName(String name) {
        this.name = name;
        return this;
    }
    @Override
    public RecipeBuilder setTag(List<String> tags) {
        this.tags = tags;
        return this;
    }

    public Recipe build() {
        return new Recipe(name,ingredients, instructions, servingSize, categories, tags);
    }

    public void executeCommand(Command command) {
        command.execute();
        executedCommands.push(command);
    }

    public void undoLastCommand() {
        if (!executedCommands.isEmpty()) {
            Command lastCommand = executedCommands.pop();
            lastCommand.undo();
        }
    }
}