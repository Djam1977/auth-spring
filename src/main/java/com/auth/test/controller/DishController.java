package com.auth.test.controller;


import com.auth.test.entity.Dish;
import com.auth.test.payload.response.MessageResponse;
import com.auth.test.repository.DishRepository;
import com.auth.test.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dishes")
public class DishController {

    @Autowired
    DishRepository dishRepository;
    @Autowired
    IngredientRepository ingredientRepository;


    @GetMapping("")
    public List<Dish> getDish() {
        return dishRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Dish> getDishById(@PathVariable Long id) {
        return dishRepository.findById(id);


    }

    @PostMapping("")
    public ResponseEntity<?> postDish(@RequestBody Dish dishBody) {


        dishRepository.save(dishBody);
        return ResponseEntity.ok(new MessageResponse("Plat bien ajouté!"));
    }

//    @PostMapping("")
//    public ResponseEntity<?> postDishWithIngredient(@RequestBody DishWithIngredientWrapper wrapper) {
//        Ingredient ingredient = ingredientRepository.findById(wrapper.getIngredient().getId()).orElse(null);
//        Dish dish = dishRepository.findById(wrapper.getDish().getId()).orElse(null);
//        if (ingredient != null && dish != null) {
//
//            dish.getIngredients().add(ingredient);
//            ingredient.getDish().add(dish);
//
//            dishRepository.save(dish);
//            ingredientRepository.save(ingredient);
//
//        }
//        return ResponseEntity.ok(new MessageResponse("VIVE LA VIE"));
//    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateDish(@PathVariable Long id, @RequestBody Dish dishBody) {
        Dish dishToUpdate = dishRepository.findById(id).get();
        dishToUpdate.setImage(dishBody.getImage());
        dishToUpdate.setTitre(dishBody.getTitre());
        dishToUpdate.setPrice(dishBody.getPrice());
        dishRepository.save(dishToUpdate);
        return ResponseEntity.ok(new MessageResponse("Plat bien modifié!"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        Dish dishToDelete = dishRepository.findById(id).get();
        dishRepository.deleteById(dishToDelete.getId());
        return ResponseEntity.ok(new MessageResponse("Plat bien supprimé!"));
    }
}
