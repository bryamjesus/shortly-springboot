package com.bjtg.shortly.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.bjtg.shortly.url.service.impl.ShortCodeGeneratorImpl;

import static org.junit.jupiter.api.Assertions.*;

public class UrlShortServiceImplTest {
    private final ShortCodeGeneratorImpl urlShortService = new ShortCodeGeneratorImpl();

    @Test
    @DisplayName("Generar código")
    public void testShortUrl_Generates8CharacterCode() {
        String code = urlShortService.shortUrl();
        System.out.println("code = " + code);

        // Verificar que no sea nulo
        assertNotNull(code, "El código generado no debe ser nulo");

        // Verificar que tenga exactamente 8 caracteres
        assertEquals(8, code.length(), "El código debe tener 8 caracteres");

        // Verificar que solo contenga letras (mayúsculas o minúsculas) y números
        assertTrue(code.matches("[a-zA-Z0-9]{8}"), "El código debe contener solo letras y números");
    }

    @Test
    @DisplayName("Generar diferentes códigos")
    public void testShortUrl_GeneratesDifferentCodes() {
        String code1 = urlShortService.shortUrl();
        String code2 = urlShortService.shortUrl();
        System.out.println("code2+ code1 = " + code2 + code1);

        // Aunque la probabilidad de colisión es muy baja, se espera que en dos llamadas consecutivas sean diferentes.
        assertNotEquals(code1, code2, "Dos códigos generados deberían ser diferentes");
    }
}
