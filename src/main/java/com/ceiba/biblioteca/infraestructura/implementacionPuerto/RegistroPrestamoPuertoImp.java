package com.ceiba.biblioteca.infraestructura.implementacionPuerto;

import com.ceiba.biblioteca.dominio.dto.RespuestaPrestamoDto;
import com.ceiba.biblioteca.dominio.modelo.RegistroPrestamo;
import com.ceiba.biblioteca.dominio.puerto.RegistroPrestamoPuerto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Repository
@AllArgsConstructor
public class RegistroPrestamoPuertoImp implements RegistroPrestamoPuerto {
    private JdbcTemplate jdbcTemplate;

    @Override
    public RespuestaPrestamoDto crearRegistroPrestamoEnBaseDeDatos(RegistroPrestamo registroPrestamo) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(registroPrestamo.getFechaPrestamo(), formatter);
        return new RespuestaPrestamoDto(guardePrestamoYRetorneId(registroPrestamo), formatter.format(fecha).toString());
    }
    private int guardePrestamoYRetorneId(RegistroPrestamo registroPrestamo){
        String sql = "insert into prestamo (isbn, identificacionUsuario, tipoUsuario, fechaPrestamo)"+
                " values (?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement stmt = connection.prepareStatement(sql, new String[]{"id"});
                stmt.setString(1,registroPrestamo.getIsbn());
                stmt.setString(2,registroPrestamo.getIdentificacionUsuario());
                stmt.setInt(3,registroPrestamo.getTipoUsuario());
                stmt.setString(4,registroPrestamo.getFechaPrestamo());
                return stmt;},keyHolder);
        return keyHolder.getKey().intValue();
    }
}
