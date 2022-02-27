package com.ceiba.biblioteca.infraestructura.configuracion;

import com.ceiba.biblioteca.dominio.puerto.ConsultaPrestamoPuerto;
import com.ceiba.biblioteca.dominio.puerto.RegistroPrestamoPuerto;
import com.ceiba.biblioteca.dominio.servicio.ConsultaPrestamoServicioDominio;
import com.ceiba.biblioteca.dominio.servicio.RegistroPrestamoServicioDominio;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguracion {

    @Bean
    public ConsultaPrestamoServicioDominio inyeccionServicioDominioConsulta(ConsultaPrestamoPuerto consultaPrestamoPuerto){
        return new ConsultaPrestamoServicioDominio(consultaPrestamoPuerto);
    }
    @Bean
    public RegistroPrestamoServicioDominio inyeccionServicioDominio(RegistroPrestamoPuerto registroPrestamoPuerto, ConsultaPrestamoPuerto consultaPrestamoPuerto){
        return new RegistroPrestamoServicioDominio(registroPrestamoPuerto, consultaPrestamoPuerto);
    }
}
