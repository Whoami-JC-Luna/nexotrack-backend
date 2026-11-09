package com.nexologistics.nexotrack.shared.error;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TestRequest {

    @NotBlank(message = "El nombre no puede estar vacio")
    private String nombre;

    @Size(min = 6, message = "La constraseña debe tener al menos 6 caracteres")
    private String password;

    public String getNombre() {
    return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
