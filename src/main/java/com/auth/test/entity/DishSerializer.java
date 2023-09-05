package com.auth.test.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DishSerializer extends JsonSerializer<List<Dish>> {
    @Override
    public void serialize(List<Dish> dishes, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Map<String, Object>> serializedDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            Map<String, Object> serializedDish = new HashMap<>();
            serializedDish.put("titre", dish.getTitre());

            serializedDishes.add(serializedDish);
        }
        jsonGenerator.writeObject(serializedDishes);
    }
}
