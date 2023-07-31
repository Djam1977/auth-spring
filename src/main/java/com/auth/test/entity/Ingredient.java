package com.auth.test.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 255)
    private String strIngredientFr;
    @NotBlank
    @Size(max = 255)
    private String strIngredientEn;

    @NotBlank
    @Size(max = 1000)
    private String description;


    @ManyToMany(mappedBy = "ingredients")
    private List<Flat> flat = new ArrayList<>();
    public Ingredient() {
    }

    public Ingredient(Long id, String strIngredientFr, String strIngredientEn, String description) {
        this.id = id;
        this.strIngredientFr = strIngredientFr;
        this.strIngredientEn = strIngredientEn;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStrIngredientFr() {
        return strIngredientFr;
    }

    public void setStrIngredientFr(String strIngredientFr) {
        this.strIngredientFr = strIngredientFr;
    }

    public String getStrIngredientEn() {
        return strIngredientEn;
    }

    public void setStrIngredientEn(String strIngredientEn) {
        this.strIngredientEn = strIngredientEn;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Flat> getFlat() {
        return flat;
    }

    public void setFlat(List<Flat> flat) {
        this.flat = flat;
    }
}

