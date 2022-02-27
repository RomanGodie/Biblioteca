package com.ceiba.biblioteca.aplicacion.mapper;


import com.ceiba.biblioteca.dominio.modelo.RegistroPrestamo;
import com.ceiba.biblioteca.infraestructura.dto.RegistroPrestamoDto;
import org.springframework.stereotype.Component;

@Component
public class MapperRegistroPrestamo {
    public RegistroPrestamo convertir(RegistroPrestamoDto registroPrestamoDto){
        RegistroPrestamo registroPrestamo = new RegistroPrestamo();
        registroPrestamo.setIsbn(registroPrestamoDto.getIsbn());
        registroPrestamo.setIdentificacionUsuario(registroPrestamoDto.getIdentificacionUsuario());
        registroPrestamo.setTipoUsuario(registroPrestamoDto.getTipoUsuario());
        return registroPrestamo;
    }
}
