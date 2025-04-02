package com.bjtg.shortly.pruebas.user;

public class UserBuilder {
    // Campos obligatorios (final, deben inicializarse en el constructor)
    private final String name;
    private final String email;

    // Campos opcionales (valores por defecto)
    private int age = 0;
    private String phone = "";

    // Constructor con parámetros obligatorios
    public UserBuilder(String name, String email) {
        this.name = name;
        this.email = email;
    }

    // Método para asignar la edad (opcional)
    public UserBuilder age(int age) {
        this.age = age;
        return this; // Retorna el Builder para permitir "method chaining"
    }

    // Método para asignar el teléfono (opcional)
    public UserBuilder phone(String phone) {
        this.phone = phone;
        return this; // Retorna el Builder para permitir "method chaining"
    }

    // Método para construir el objeto User
    public User build() {
        return new User(this); // Crea una instancia de User usando el Builder
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
}