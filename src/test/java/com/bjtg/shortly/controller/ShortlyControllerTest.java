package com.bjtg.shortly.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.bjtg.shortly.url.controller.UrlController;
import com.bjtg.shortly.url.dto.UrlRequest;
import com.bjtg.shortly.url.dto.UrlResponse;
import com.bjtg.shortly.url.exception.UrlNotFoundException;
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
    void testGetUrlByCodeUrl_Success() throws Exception {
        // Arrange
        String codeUrl = "aB3xK9Zw"; // 8 caracteres alfanuméricos válidos
        UrlResponse mockResponse = UrlResponse.builder()
                .shortCode(codeUrl)
                .originalUrl("https://www.google.com")
                .build();

        when(urlManagerService.getUrlByCode(codeUrl)).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(get("/shortly/{codeUrl}", codeUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.statusCode").value(200))
                .andExpect(jsonPath("$.message").value("URL retrieved successfully"))
                .andExpect(jsonPath("$.data.codeUrl").value(codeUrl))
                .andExpect(jsonPath("$.data.originalUrl").value("https://www.google.com"))
                .andExpect(jsonPath("$.path").value("/shortly/" + codeUrl))
                .andExpect(jsonPath("$.timestamp").exists()); // Verifica que existe
    }

    @Test
    void testGetUrlByCodeUrl_InvalidCode() throws Exception {
        // Arrange
        String invalidCode = "abc";

        // Act & Assert
        mockMvc.perform(get("/shortly/{codeUrl}", invalidCode)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.message").value("El código debe tener 8 caracteres alfanuméricos"));
    }

    @Test
    void testGetUrlByCodeUrl_NotFound() throws Exception {
        // Arrange
        String codeUrl = "notFound";

        when(urlManagerService.getUrlByCode(codeUrl))
                .thenThrow(new UrlNotFoundException("URL not found with code: " + codeUrl));

        // Act & Assert
        mockMvc.perform(get("/shortly/{codeUrl}", codeUrl)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.statusCode").value(404))
                .andExpect(jsonPath("$.message").value("URL not found with code: " + codeUrl));
    }

    @Test
    void testShortenUrl_Success() throws Exception {
        // Arrange
        String originalUrl = "https://www.google.com";
        String codeUrl = "aB3xK9Zw";

        UrlRequest request = new UrlRequest(originalUrl);
        UrlResponse mockResponse = UrlResponse.builder()
                .shortCode(codeUrl)
                .originalUrl(originalUrl)
                .build();

        when(urlManagerService.shortUrl(originalUrl)).thenReturn(mockResponse);

        // Act & Assert
        mockMvc.perform(post("/shortly")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/shortly/" + codeUrl))
                .andExpect(jsonPath("$.status").value("success"))
                .andExpect(jsonPath("$.statusCode").value(201))
                .andExpect(jsonPath("$.message").value("Short URL generated successfully"))
                .andExpect(jsonPath("$.data.codeUrl").value(codeUrl))
                .andExpect(jsonPath("$.data.originalUrl").value(originalUrl))
                .andExpect(jsonPath("$.path").value("/shortly"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void testShortenUrl_InvalidUrl() throws Exception {
        // Arrange
        UrlRequest request = new UrlRequest(""); // URL vacía

        // Act & Assert
        mockMvc.perform(post("/shortly")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value("error"))
                .andExpect(jsonPath("$.statusCode").value(400));
    }
}
