package com.auth.test.controller;

import com.auth.test.entity.Ingredient;
import com.auth.test.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("")
    public List<Ingredient> getIngredient() {
        return ingredientRepository.findAll();
    }


    @PostMapping("")
    public List<Ingredient> postIngredients(@RequestBody List<Ingredient> ingredientsBody) {
        List<Ingredient> list = new ArrayList<>();
        for (Ingredient ingredient : ingredientsBody) {
            Ingredient saved = ingredientRepository.save(ingredient);
            list.add(saved);
        }
        return list;
    }
//    @PostMapping("")
//    public List<Ingredient> postIngredients(@RequestBody List<Ingredient> ingredientsBody) {
//        List<Ingredient> list = new ArrayList<>();
//        for (Ingredient ingredient : ingredientsBody) {
//            Ingredient ingredientToSave = new Ingredient();
//            ingredientToSave.setName(ingredient.getName());
//            ingredientToSave.setNameEn(ingredient.getNameEn());
//            list.add(ingredientToSave);
//            ingredientRepository.save(ingredient);
//        }
//        return list;
//    }
}
