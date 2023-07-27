package com.auth.test.controller;


import com.auth.test.entity.Flat;
import com.auth.test.repository.FlatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/flats")
public class FlatController {

    @Autowired
    FlatRepository flatRepository;

    @GetMapping("")

    public List<Flat> getFlat() { return flatRepository. findAll();
    }
//    @PostMapping("")
//    public ResponseEntity<?>postFlat(@RequestBody Flat flatsBody){
//        Flat newFlat = new Flat (flatsBody.getImage();
//               flatsBody.getTitre();
//                flatsBody.getPrice();
//return ResponseEntity.ok(new PlatResponse("Plat bien enregistré!"));}


}
