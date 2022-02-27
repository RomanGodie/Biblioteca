package com.ceiba.biblioteca.infraestructura.implementacionPuerto;

import com.ceiba.biblioteca.dominio.dto.RespuestaConsultaPrestamoDto;
import com.ceiba.biblioteca.dominio.puerto.ConsultaPrestamoPuerto;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
@AllArgsConstructor
public class ConsultaPrestamoPuertoImp implements ConsultaPrestamoPuerto {
    private JdbcTemplate jdbcTemplate;

    @Override
    public RespuestaConsultaPrestamoDto consultarPrestamoEnBaseDatosConId(int id) {
        String sql = "SELECT * FROM prestamo WHERE id = ?";
        return jdbcTemplate.queryForObject(
                sql,
                new Object[]{id},
                (rs, rowNum) ->
                new RespuestaConsultaPrestamoDto(
                        rs.getInt("id"),
                        rs.getString("isbn"),
                        rs.getString("identificacionUsuario"),
                        rs.getInt("tipoUsuario"),
                        rs.getString("fechaPrestamo")
                ));
    }
    @Override
    public boolean consultarUsuarioPorIdentificacionDeUsuario(String identificacionUsuario){
        String sql = "SELECT count(identificacionUsuario) FROM prestamo WHERE identificacionUsuario = ?";
        int existe = jdbcTemplate.queryForObject(sql,
                new Object[]{identificacionUsuario},
                Integer.class);
        return (existe >= 1);
    }
}
