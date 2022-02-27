package com.ceiba.biblioteca.infraestructura.controlador;


import com.ceiba.biblioteca.aplicacion.servicio.ConsultaPrestamoServicioAplicacion;
import com.ceiba.biblioteca.aplicacion.servicio.RegistroPrestamoServicioAplicacion;
import com.ceiba.biblioteca.dominio.dto.RespuestaConsultaPrestamoDto;
import com.ceiba.biblioteca.dominio.dto.RespuestaPrestamoDto;
import com.ceiba.biblioteca.infraestructura.dto.RegistroPrestamoDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/prestamo")
public class PrestamoControlador {
    RegistroPrestamoServicioAplicacion registroPrestamoServicioAplicacion;
    ConsultaPrestamoServicioAplicacion consultaPrestamoServicioAplicacion;

    @PostMapping()
    public RespuestaPrestamoDto crearRegistroPrestamo(@RequestBody RegistroPrestamoDto registroPrestamoDto){
        return registroPrestamoServicioAplicacion.recibeControladorParaDominio(registroPrestamoDto);
    }

    @GetMapping("/{id}")
    public RespuestaConsultaPrestamoDto consultarRegistroPrestamo(@PathVariable int id){
        return consultaPrestamoServicioAplicacion.recibeConsultaDeControladorParaElServicioDeDominio(id);
    }

}

