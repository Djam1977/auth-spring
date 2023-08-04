package com.auth.test.entity;


import jakarta.persistence.Entity;


public class FlatWithIngredientWrapper {
    private Flat flat;
    private Ingredient ingredient;

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
