package com.inviggoproject.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inviggoproject.dto.AuthenticationResponseDto;

import java.io.IOException;

public class TestUtil {
    public static String json(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        return mapper.writeValueAsString(object);
    }

    public static AuthenticationResponseDto parseJwtResponse(String response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response, AuthenticationResponseDto.class);
    }
}
