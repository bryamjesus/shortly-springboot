package com.bjtg.shortly.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bjtg.shortly.common.factory.ApiResponseFactory;
import com.bjtg.shortly.url.controller.UrlController;
import com.bjtg.shortly.url.dto.UrlResponse;
import com.bjtg.shortly.url.service.UrlService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = UrlController.class)
public class ShortlyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlService urlManagerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetUrlByCodeUrl() throws Exception {
        String codeUrl = "abc12345";
        UrlResponse mockResponse = UrlResponse.builder().build();

        when(urlManagerService.getUrlByCode(codeUrl)).thenReturn(mockResponse);

        mockMvc.perform(get("/shortly/{codeUrl}", codeUrl))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        ApiResponseFactory.succes("Url retrieved successfully", mockResponse))));
    }
}
