package com.auth.test.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Flat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 255)
    private String image;
    @NotBlank
    @Size(max = 255)
    private String titre;

    @NotBlank
    @Size(max = 255)
    private Float price;


//    @ManyToMany
//    @JoinTable(name = "ingredient_flat",
//            joinColumns = @JoinColumn(name = "ingredient_id"),
//            inverseJoinColumns = @JoinColumn(name = "flat_id"))


//    private List<Ingredient> ingredients = new ArrayList<>();



    public Flat() {
    }

    public Flat(Long id, String image, String titre, Float price) {
        this.id = id;
        this.image = image;
        this.titre = titre;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
