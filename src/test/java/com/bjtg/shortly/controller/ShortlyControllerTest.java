package com.bjtg.shortly.controller;

import com.bjtg.shortly.dto.url.UrlResponse;
import com.bjtg.shortly.service.UrlManagerService;
import com.bjtg.shortly.url.controller.ShortlyController;
import com.bjtg.shortly.util.ResponseUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShortlyController.class)
public class ShortlyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UrlManagerService urlManagerService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetUrlByCodeUrl() throws Exception {
        String codeUrl = "abc12345";
        UrlResponse mockResponse = new UrlResponse();

        when(urlManagerService.getUrlByCode(codeUrl)).thenReturn(mockResponse);

        mockMvc.perform(get("/shortly/{codeUrl}", codeUrl))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(
                        ResponseUtil.succes("Url retrieved successfully", mockResponse)
                )));
    }
}
