package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Builder recipeBuilder = new RecipeBuilder();
        CommandInvoker commandInvoker = new CommandInvoker();
        System.out.println("Welcome to the Online Recipe Management System!");
        Recipe recipe = null;
        Recipe recipe1= new Recipe();
        List<Recipe> recipes = new ArrayList<>();

        while (true) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add recipe");
            System.out.println("2. Modify and Undo recipe");
            System.out.println("3. Search");
            System.out.println("4. Rate");
            System.out.println("5. Display All Recipes");
            System.out.println("6. Quit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    create((RecipeBuilder) recipeBuilder, recipes,scanner);
                    break;

                case 2:
                    if(recipes.size()==0) {
                        System.out.println("You haven't created a recipe yet");
                        break;
                    }
                    commandInvoker.execute(new PromptCommand("Enter the name of the recipe you want to modify"));
                    String name = commandInvoker.getInput();
                    for (Recipe item : recipes) {
                        if (item.getName().equals(name)) {
                            modification((RecipeBuilder) recipeBuilder,item);
                            break;
                        }
                        else System.out.println("No recipe with this name found");
                    }
                    break;

                case 3:
                        search(recipe1,recipes);
                    break;

                case 4:
                        rate(recipes);
                    break;

                case 5:
                    for (Recipe item:
                         recipes){
                            display(item);
                    }
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void create(RecipeBuilder recipeBuilder, List<Recipe> recipes, Scanner scanner) {
        System.out.println("=== Recipe Creation Module ===");

        CommandInvoker commandInvoker= new CommandInvoker();
        commandInvoker.execute(new PromptCommand("Enter the name:"));
        String name = commandInvoker.getInput();
        recipeBuilder.setName(name);

        commandInvoker.execute(new ListInputCommand("Enter the ingredients (one per line, empty line to finish):"));
        List<String> ingredients = commandInvoker.getListInput();
        recipeBuilder.setIngredient(ingredients);

        commandInvoker.execute(new ListInputCommand("Enter the cooking instructions (one per line, empty line to finish):"));
        List<String> instructions = commandInvoker.getListInput();
        recipeBuilder.setInstruction(instructions);

        commandInvoker.execute(new IntegerInputCommand("Enter the serving size:"));
        int servingSize = commandInvoker.getIntegerInput();
        recipeBuilder.setServingSize(servingSize);

        scanner.nextLine();

        List<String> availableCategories = List.of("Breakfast", "Dinner", "Dessert");

        System.out.println("Choose category");

        for (int i = 0; i < availableCategories.size(); i++) {
            System.out.println((i + 1) + ". " + availableCategories.get(i));
        }

        System.out.print("Enter your choice(s) (comma-separated): ");
        String categoryChoice = scanner.nextLine();
        ArrayList categories
                = new ArrayList();

        String[] choices = categoryChoice.split(",", 3);
        for (String myChoice : choices) {
            int index = Integer.parseInt(myChoice.trim()) - 1;
            if (index >= 0 && index < availableCategories.size()) {
                categories.add(availableCategories.get(index));
            }
        }
        recipeBuilder.setCategories(categories);
        scanner.nextLine();

        List<String> availableTags = List.of("Vegan", "Gluten Free", "Spicy");

        System.out.println("Choose up to three tags:");

        for (int i = 0; i < Math.min(3, availableTags.size()); i++) {
            System.out.println((i + 1) + ". " + availableTags.get(i));
        }

        System.out.print("Enter your choice(s) (comma-separated): ");
        String tagChoices = scanner.nextLine();

        String[] newChoices = tagChoices.split(",", 3);
        ArrayList tags
                = new ArrayList();
        for (String myChoice : newChoices) {
            int index = Integer.parseInt(myChoice.trim()) - 1;
            if (index >= 0 && index < availableTags.size()) {
                tags.add(availableTags.get(index));
            }
        }

        recipeBuilder.setTag(tags);

        RecipeRatingModule ratingModule = new RecipeRatingModule();
        Recipe recipe = recipeBuilder.build();
        recipe.setRatingModule(ratingModule);
        ratingModule.addObserver(recipe, recipe);
        recipes.add(recipe);
        display(recipe);

    }

    public static void display(Recipe item){

        System.out.println("\n=== Recipe Details==="  );
        System.out.println("Name:" + item.getName());
        System.out.println("Ingredients: " + item.getIngredients());
        System.out.println("Instructions: " + item.getInstructions());
        System.out.println("Serving Size: " + item.getServingSize());
        System.out.println("Categories: " + item.getCategories());
        System.out.println("Tags: " + item.getTags());
        System.out.println("Total Rate: " + item.getRating());
        System.out.println("Average Rate: " + item.averageRate());
        System.out.println("***************");

    }

    public static void modification(RecipeBuilder recipeBuilder, Recipe recipe) {
        System.out.println("\n=== Recipe Modification Module ===");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Modify Ingredients");
            System.out.println("2. Modify Instructions");
            System.out.println("3. Modify Categories");
            System.out.println("4. Undo Last Modification");
            System.out.println("5. Exit");
            CommandInvoker commandInvoker= new CommandInvoker();
            commandInvoker.execute(new IntegerInputCommand(""));

            int option = commandInvoker.getIntegerInput();

            switch (option) {
                case 1:
                    commandInvoker.execute(new ListInputCommand("Enter the new ingredients (one per line, empty line to finish):"));
                    List<String> newIngredients = commandInvoker.getListInput();
                    Command modifyIngredientsCommand = new ModifyIngredientsCommand(recipe, newIngredients);
                    recipeBuilder.executeCommand(modifyIngredientsCommand);
                    System.out.println("New Recipe:");
                    display(recipe);
                    break;

                case 2:
                    commandInvoker.execute(new ListInputCommand("Enter the new instructions (one per line, empty line to finish):"));
                    List<String> newInstructions = commandInvoker.getListInput();
                    Command modifyInstructionsCommand = new ModifyInstructionsCommand(recipe, newInstructions);
                    recipeBuilder.executeCommand(modifyInstructionsCommand);
                    System.out.println("New Recipe");
                    display(recipe);
                    break;

                case 3:
                    List<String> availableCategories = List.of("Breakfast", "Dinner", "Dessert");
                    commandInvoker.execute(new MultipleChoiceCommand("Choose new categories:\n", availableCategories, 3));
                    List<String> newCategories = commandInvoker.getListInput();
                    Command modifyCategoriesCommand = new ModifyCategoriesCommand(recipe, newCategories);
                    recipeBuilder.executeCommand(modifyCategoriesCommand);
                    System.out.println("New Recipe");
                    display(recipe);
                    break;

                case 4:
                    recipeBuilder.undoLastCommand();
                    System.out.println("New Recipe");
                    display(recipe);
                    break;
                case 5:
                   return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void search(Recipe recipe, List<Recipe> recipes){
        while (true) {
            System.out.println("\n=== Recipe Search Module ===");
            System.out.println("Choose a search option:");
            System.out.println("1. Search by Tags");
            System.out.println("2. Search by Ingredients");
            System.out.println("3. Search by Categories");
            System.out.println("4. Exit");

            CommandInvoker commandInvoker = new CommandInvoker();

            commandInvoker.execute(new IntegerInputCommand(""));
            int option = commandInvoker.getIntegerInput();

            switch (option) {
                case 1:
                    recipe.setSearchStrategy(new TagSearchStrategy());
                    break;
                case 2:
                    recipe.setSearchStrategy(new IngredientSearchStrategy());
                    break;
                case 3:
                    recipe.setSearchStrategy(new CategorySearchStrategy());
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid option");
                    continue;
            }

            commandInvoker.execute(new PromptCommand("Enter the search keyword:"));
            String keyword = commandInvoker.getInput();

            List<Recipe> searchResults = recipe.searchRecipes(recipes, keyword);

            System.out.println("\n=== Search Results ===");
            if (searchResults.isEmpty()) {
                System.out.println("No recipes found");
            } else {
                for (Recipe result : searchResults) {
                    display(result);
                }
            }
        }
    }

    public static void rate(List<Recipe> recipes){
        System.out.println("\n=== Recipe Rating Module ===");

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. Rate Recipe");
            System.out.println("2. Exit");

            CommandInvoker commandInvoker = new CommandInvoker();

            commandInvoker.execute(new IntegerInputCommand(""));
            int option =  commandInvoker.getIntegerInput();


            switch (option) {
                case 1:
                    commandInvoker.execute(new PromptCommand("Enter the recipe name to rate:"));
                    String recipeName = commandInvoker.getInput();

                    Recipe selectedRecipe = findRecipeByName(recipes, recipeName);
                    if (selectedRecipe != null) {
                        commandInvoker.execute(new IntegerInputCommand("Enter the rating (1-5 stars):"));
                        int rating = commandInvoker.getIntegerInput();
                        int rate = selectedRecipe.getRating();
                        selectedRecipe.rate(rate+rating);
                    } else {
                        System.out.println("Recipe not found.");
                    }
                    break;
                case 2:
                    return;
                default:
                    System.out.println("Invalid option");
            }
        }
    }

    private static Recipe findRecipeByName(List<Recipe> recipes, String name) {
        for (Recipe recipe : recipes) {
            if (recipe.getName().equals(name)) {
                return recipe;
            }
        }
        return null;
    }
}