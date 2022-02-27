package com.ceiba.biblioteca.aplicacion.servicio;

import com.ceiba.biblioteca.dominio.dto.RespuestaConsultaPrestamoDto;
import com.ceiba.biblioteca.dominio.servicio.ConsultaPrestamoServicioDominio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ConsultaPrestamoServicioAplicacion {
    ConsultaPrestamoServicioDominio consultaPrestamoServicioDominio;

    public RespuestaConsultaPrestamoDto recibeConsultaDeControladorParaElServicioDeDominio(int id){
        return consultaPrestamoServicioDominio.ejecucionLogicaDeNegocioEnConsulta(id);
    }
}
