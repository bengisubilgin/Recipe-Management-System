package com.example;

import java.util.List;

class Recipe implements RecipeRatingObserver{
    private String name;
    private List<String> ingredients;
    private List<String> instructions;
    private int servingSize;
    private List<String> categories;
    private List<String> tags;
    private int rating;
    private int numberOfRatings;

    private RecipeSearchStrategy searchStrategy;
    private RecipeRatingModule ratingModule;


    public Recipe() {
    }

    public Recipe(String name, List<String> ingredients, List<String> instructions, int servingSize,
                  List<String> categories, List<String> tags) {
        this.name= name;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.servingSize = servingSize;
        this.categories = categories;
        this.tags = tags;
    }

    public double averageRate(){
        if (numberOfRatings > 0) {
            double averageRate = (double) rating / numberOfRatings;
            return averageRate;
        }
        return 0;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public void updateRating(int rating) {
        this.rating = rating;
    }

    public void setSearchStrategy(RecipeSearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Recipe> searchRecipes(List<Recipe> recipes, String keyword) {
        if (searchStrategy == null) {
            throw new IllegalStateException("Search strategy not set");
        }
        return searchStrategy.search(recipes, keyword);
    }

    public void setRatingModule(RecipeRatingModule ratingModule) {
        this.ratingModule = ratingModule;
    }

    public void rate(int rating) {
        numberOfRatings++;
        ratingModule.rateRecipe(this, rating);
        System.out.println(name + " received notification: Recipe rating has been updated to " + rating);

    }
    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public int getServingSize() {
        return servingSize;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return tags;
    }


}