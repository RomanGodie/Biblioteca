package com.ceiba.biblioteca.infraestructura.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistroPrestamoDto {
    private String isbn;
    private String identificacionUsuario;
    private int tipoUsuario;
}
