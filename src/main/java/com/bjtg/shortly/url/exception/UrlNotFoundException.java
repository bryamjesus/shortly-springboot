package com.bjtg.shortly.url.exception;

public class UrlNotFoundException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "URL not found";

    /**
     * Constructor sin parámetros con mensaje por defecto
     */
    public UrlNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructor con mensaje personalizado
     * 
     * @param message mensaje descriptivo del error
     */
    public UrlNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor con mensaje y causa
     * 
     * @param message mensaje descriptivo del error
     * @param cause   excepción original que causó este error
     */
    public UrlNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor de conveniencia para cuando no se encuentra una URL por código
     * 
     * @param codeUrl código de la URL que no se encontró
     * @return nueva excepción con mensaje formateado
     */
    public static UrlNotFoundException byCode(String codeUrl) {
        return new UrlNotFoundException("URL not found with code: " + codeUrl);
    }

    /**
     * Constructor de conveniencia para cuando no se encuentra una URL por ID
     * 
     * @param id identificador de la URL que no se encontró
     * @return nueva excepción con mensaje formateado
     */
    public static UrlNotFoundException byId(Long id) {
        return new UrlNotFoundException("URL not found with id: " + id);
    }
}
