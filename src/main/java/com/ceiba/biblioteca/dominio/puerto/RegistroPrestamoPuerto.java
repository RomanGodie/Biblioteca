package com.ceiba.biblioteca.dominio.puerto;

import com.ceiba.biblioteca.dominio.dto.RespuestaPrestamoDto;
import com.ceiba.biblioteca.dominio.modelo.RegistroPrestamo;

public interface RegistroPrestamoPuerto {
    RespuestaPrestamoDto crearRegistroPrestamoEnBaseDeDatos(RegistroPrestamo registroPrestamo);
}
