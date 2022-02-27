package com.ceiba.biblioteca.dominio.puerto;

import com.ceiba.biblioteca.dominio.dto.RespuestaConsultaPrestamoDto;

public interface ConsultaPrestamoPuerto {
    RespuestaConsultaPrestamoDto consultarPrestamoEnBaseDatosConId(int id);
    boolean consultarUsuarioPorIdentificacionDeUsuario(String identificacionUsuario);
}
