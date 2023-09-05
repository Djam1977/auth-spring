package com.auth.test.controller;

import com.auth.test.entity.Ingredient;
import com.auth.test.payload.response.MessageResponse;
import com.auth.test.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @PostMapping("")
    public ResponseEntity<?> postIngredients(@RequestBody List<Ingredient> ingredientsBody) {

        for (Ingredient ingredient : ingredientsBody) {
            ingredientRepository.save(ingredient);
        }

        return ResponseEntity.ok(new MessageResponse("ok ingrédients ajoutés"));
    }
}
