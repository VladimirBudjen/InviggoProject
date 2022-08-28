package com.inviggoproject.controller;

import com.inviggoproject.dto.AuthenticationRequestDto;
import com.inviggoproject.dto.UpdateAdvertRequestDto;
import com.inviggoproject.security.auth.TokenUtils;
import com.inviggoproject.util.TestConstants;
import com.inviggoproject.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Import(AdvertController.class)
public class AdvertControllerTest {
    private final String URL_PREFIX = "/advert";
    private final String LOGIN_URL_PREFIX = "/auth/login";

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    private String jwt;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void login() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        var authDto = new AuthenticationRequestDto();
        authDto.setPassword(TestConstants.PASSWORD);
        authDto.setUsername(TestConstants.USER_NAME_LEMARA_2);
        String json = TestUtil.json(authDto);
        this.mockMvc.perform(post(LOGIN_URL_PREFIX)
                .contentType(contentType).content(json))
                .andDo(result -> this.jwt =
                        "Bearer " + TestUtil.parseJwtResponse(result.getResponse().getContentAsString()).getJwt());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateAdvertWithRightUser() throws Exception {
        var dto = new UpdateAdvertRequestDto();
        dto.setCategory(TestConstants.CATEGORIE_NAME_TOOL);
        dto.setCity(TestConstants.CITY);
        dto.setDescription(TestConstants.ADVERT_DESCRIPTION);
        dto.setName(TestConstants.ADVERT_NAME);
        dto.setImageUrl(TestConstants.IMAGE_URL);
        dto.setPrice(TestConstants.PRICE);
        dto.setCode(TestConstants.ADVERT_CODE_BY_LEMARA_2);
        String json = TestUtil.json(dto);
        this.mockMvc.perform(put(URL_PREFIX).contentType(contentType)
                .header("Authorization",this.jwt).content(json)).andExpect(status().isOk());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateAdvertWithWrongUser() throws Exception {
        var dto = new UpdateAdvertRequestDto();
        dto.setCategory(TestConstants.CATEGORIE_NAME_TOOL);
        dto.setCity(TestConstants.CITY);
        dto.setDescription(TestConstants.ADVERT_DESCRIPTION);
        dto.setName(TestConstants.ADVERT_NAME);
        dto.setImageUrl(TestConstants.IMAGE_URL);
        dto.setPrice(TestConstants.PRICE);
        dto.setCode(TestConstants.ADVERT_CODE_BY_VLADIMIR);
        String json = TestUtil.json(dto);
        this.mockMvc.perform(put(URL_PREFIX).contentType(contentType)
                .header("Authorization",this.jwt).content(json)).andExpect(status().isUnauthorized());
    }
}
