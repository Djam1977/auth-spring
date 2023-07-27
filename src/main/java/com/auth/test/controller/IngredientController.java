package com.auth.test.controller;

import com.auth.test.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientController {

    @Autowired
    IngredientRepository ingredientRepository;
}
