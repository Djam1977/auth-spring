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
public class
DishController {

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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> postDish(@RequestBody Dish dishBody) {
        dishRepository.save(dishBody);
        return ResponseEntity.ok(new MessageResponse("Plat bien ajouté!"));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteDish(@PathVariable Long id) {
        dishRepository.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Plat bien supprimé!"));
    }
}
