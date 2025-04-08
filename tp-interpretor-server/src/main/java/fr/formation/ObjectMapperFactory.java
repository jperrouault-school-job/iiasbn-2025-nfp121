package fr.formation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperFactory {
    private ObjectMapperFactory() { }

    public static ObjectMapper createObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.registerModule(new JavaTimeModule());

        // On ajoute le format "Pretty"
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        return mapper;
    }
}
