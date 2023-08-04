package com.auth.test.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IngredientSerializer extends JsonSerializer<List<Ingredient>> {


    @Override
    public void serialize(List<Ingredient> ingredients, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Map<String, Object>> serializedIngredients = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            Map<String, Object> serializedIngredient = new HashMap<>();
            serializedIngredient.put("name", ingredient.getName());
            serializedIngredient.put("nameEn", ingredient.getNameEn());
            serializedIngredients.add(serializedIngredient);
        }
        jsonGenerator.writeObject(serializedIngredients);
    }
}
