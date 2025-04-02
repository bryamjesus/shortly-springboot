package com.bjtg.shortly.pruebas.user;

public class User {
    // Campos finales (inmutables)
    private final String name;
    private final String email;
    private final int age;
    private final String phone;

    // Constructor privado (solo accesible desde el Builder)
    User(UserBuilder builder) {
        this.name = builder.getName();
        this.email = builder.getEmail();
        this.age = builder.getAge();
        this.phone = builder.getPhone();
    }

    // Getters (no hay setters para mantener la inmutabilidad)
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }

    // Método estático para obtener el Builder
    public static UserBuilder builder(String name, String email) {
        return new UserBuilder(name, email);
    }
}