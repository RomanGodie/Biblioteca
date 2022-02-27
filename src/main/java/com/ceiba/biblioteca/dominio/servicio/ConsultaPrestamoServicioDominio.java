package com.ceiba.biblioteca.dominio.servicio;

import com.ceiba.biblioteca.dominio.dto.RespuestaConsultaPrestamoDto;
import com.ceiba.biblioteca.dominio.excepcion.ExcepcionDeNegocio;
import com.ceiba.biblioteca.dominio.puerto.ConsultaPrestamoPuerto;

public class ConsultaPrestamoServicioDominio {
    private final int ID_VACIO = 0;
    private final String MENSAJE_EXCEPCION_ID_INVALIDO = "El ID digitado no existe en la base de datos o es incorrecto";
    private ConsultaPrestamoPuerto consultaPrestamoPuerto;

    public ConsultaPrestamoServicioDominio(ConsultaPrestamoPuerto consultaPrestamoPuerto) {
        this.consultaPrestamoPuerto = consultaPrestamoPuerto;
    }

    public RespuestaConsultaPrestamoDto ejecucionLogicaDeNegocioEnConsulta(int id){
        if(consultaPrestamoPuerto.consultarPrestamoEnBaseDatosConId(id).getId()==ID_VACIO){
            throw new ExcepcionDeNegocio(MENSAJE_EXCEPCION_ID_INVALIDO);
        }
        return consultaPrestamoPuerto.consultarPrestamoEnBaseDatosConId(id);
    }
}
