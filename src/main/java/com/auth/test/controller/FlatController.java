package com.auth.test.controller;


import com.auth.test.entity.Flat;
import com.auth.test.payload.response.MessageResponse;
import com.auth.test.repository.FlatRepository;
import com.auth.test.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/flats")
public class FlatController {

    @Autowired
    FlatRepository flatRepository;
    @Autowired
    IngredientRepository ingredientRepository;


    @GetMapping("")
    public List<Flat> getFlat() {
        return flatRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Flat> getFlatById(@PathVariable Long id) {
        return flatRepository.findById(id);


    }

    @PostMapping("")
    public Flat postFlat(@RequestBody Flat flatBody) {


        return flatRepository.save(flatBody);
    }

//    @PostMapping("")
//    public ResponseEntity<?> postFlatWithIngredient(@RequestBody FlatWithIngredientWrapper wrapper) {
//        Ingredient ingredient = ingredientRepository.findById(wrapper.getIngredient().getId()).orElse(null);
//        Flat flat = flatRepository.findById(wrapper.getFlat().getId()).orElse(null);
//        if (ingredient != null && flat != null) {
//
//            flat.getIngredients().add(ingredient);
//            ingredient.getFlat().add(flat);
//
//            flatRepository.save(flat);
//            ingredientRepository.save(ingredient);
//
//        }
//        return ResponseEntity.ok(new MessageResponse("VIVE LA VIE"));
//    }


    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateFlat(@PathVariable Long id, @RequestBody Flat flatBody) {
        Flat flatToUpdate = flatRepository.findById(id).get();
        flatToUpdate.setImage(flatBody.getImage());
        flatToUpdate.setTitre(flatBody.getTitre());
        flatToUpdate.setPrice(flatBody.getPrice());
        flatRepository.save(flatToUpdate);
        return ResponseEntity.ok(new MessageResponse("Plat bien modifié!"));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteFlat(@PathVariable Long id) {
        Flat flatToDelete = flatRepository.findById(id).get();
        flatRepository.deleteById(flatToDelete.getId());
        return ResponseEntity.ok(new MessageResponse("Plat bien supprimé!"));
    }
}
