package com.auth.test.controller;

import com.auth.test.entity.Flat;
import com.auth.test.entity.Type;
import com.auth.test.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/types")
public class TypeController {

    @Autowired
    TypeRepository typeRepository;

    @GetMapping("")
    public List<Type> getType() {
        return typeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Type> getFlatById(@PathVariable Long id) {
        return typeRepository.findById(id);


    }
}
