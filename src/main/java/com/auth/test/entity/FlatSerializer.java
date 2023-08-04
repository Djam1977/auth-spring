package com.auth.test.entity;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlatSerializer extends JsonSerializer<List<Flat>> {
    @Override
    public void serialize(List<Flat> flats, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        List<Map<String, Object>> serializedFlats = new ArrayList<>();
        for (Flat flat : flats) {
            Map<String, Object> serializedFlat = new HashMap<>();
            serializedFlat.put("name", flat.getTitre());

            serializedFlats.add(serializedFlat);
        }
        jsonGenerator.writeObject(serializedFlats);
    }
}
