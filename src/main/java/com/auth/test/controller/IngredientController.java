package com.auth.test.controller;

import com.auth.test.entity.Ingredient;
import com.auth.test.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;

    @GetMapping("")
    public List<Ingredient> getIngredients() {
        return ingredientRepository.findAll();
    }


//    @PostMapping("")
//    public List<Ingredient> postIngredients(@RequestBody List<Ingredient> ingredientsBody) {
//        List<Ingredient> list = new ArrayList<>();
//        for (Ingredient ingredient : ingredientsBody) {
//            Ingredient saved = ingredientRepository.save(ingredient);
//            list.add(saved);
//        }
//        return list;
//    }
}
