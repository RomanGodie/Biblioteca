package com.ceiba.biblioteca.aplicacion.servicio;

import com.ceiba.biblioteca.aplicacion.mapper.MapperRegistroPrestamo;
import com.ceiba.biblioteca.dominio.dto.RespuestaPrestamoDto;
import com.ceiba.biblioteca.dominio.servicio.RegistroPrestamoServicioDominio;
import com.ceiba.biblioteca.infraestructura.dto.RegistroPrestamoDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RegistroPrestamoServicioAplicacion {
    MapperRegistroPrestamo mapperRegistroPrestamo;
    RegistroPrestamoServicioDominio registroPrestamoServicioDominio;

    public RespuestaPrestamoDto recibeControladorParaDominio(RegistroPrestamoDto registroPrestamoDto){
        return registroPrestamoServicioDominio.ejecucionLogicaNegocio
                (mapperRegistroPrestamo.convertir(registroPrestamoDto));
    }

}
