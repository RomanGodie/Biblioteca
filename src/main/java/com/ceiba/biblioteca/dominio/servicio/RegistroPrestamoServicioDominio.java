package com.ceiba.biblioteca.dominio.servicio;

import com.ceiba.biblioteca.dominio.dto.RespuestaPrestamoDto;
import com.ceiba.biblioteca.dominio.excepcion.ExcepcionDeNegocio;
import com.ceiba.biblioteca.dominio.modelo.RegistroPrestamo;
import com.ceiba.biblioteca.dominio.puerto.ConsultaPrestamoPuerto;
import com.ceiba.biblioteca.dominio.puerto.RegistroPrestamoPuerto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroPrestamoServicioDominio {
    private final  int USUARIO_AFILIADO = 1;
    private final  int USUARIO_EMPLEADO_BIBLIOTECA = 2;
    private final  int USUARIO_INVITADO = 3;
    private final int DIAS_USUARIO_AFILIADO = 10;
    private final int DIAS_USUARIO_EMPLEADO_BIBLIOTECA = 8;
    private final int DIAS_USUARIO_INVITADO = 7;
    private final String MENSAJE_EXCEPCION_USUARIO_INVALIDO = "Tipo de usuario no permitido en la biblioteca";
    private final int LONGITUD_MAXIMA_CAMPOS = 10;
    private final String MENSAJE_EXCEPCION_LONGITUD_CAMPOS_INVALIDA = " El campo digitado contiene mas de 10 caracteres";
    private final String MENSAJE_ISBN = "Isbn: ";
    private final String MENSAJE_IDENTIFICACION_USUARIO = "Identificacion de Usuario: ";
    RegistroPrestamoPuerto registroPrestamoPuerto;
    ConsultaPrestamoPuerto consultaPrestamoPuerto;

    public RegistroPrestamoServicioDominio(RegistroPrestamoPuerto registroPrestamoPuerto, ConsultaPrestamoPuerto consultaPrestamoPuerto) {
        this.registroPrestamoPuerto = registroPrestamoPuerto;
        this.consultaPrestamoPuerto = consultaPrestamoPuerto;
    }

    public RespuestaPrestamoDto ejecucionLogicaNegocio(RegistroPrestamo registroPrestamo){
        validarLongitudMaximaCaracteresAlfanumericos(registroPrestamo.getIsbn(), MENSAJE_ISBN);
        validarLongitudMaximaCaracteresAlfanumericos(registroPrestamo.getIdentificacionUsuario(), MENSAJE_IDENTIFICACION_USUARIO);
        registroPrestamo.setFechaPrestamo(verificadorTipoUsuario(registroPrestamo.getTipoUsuario()));
        validadorPrestamoAInvitadoYaExistente(registroPrestamo);
        return registroPrestamoPuerto.crearRegistroPrestamoEnBaseDeDatos(registroPrestamo);
    }

    private void validadorPrestamoAInvitadoYaExistente(RegistroPrestamo registroPrestamo) {
        if(registroPrestamo.getTipoUsuario()==USUARIO_INVITADO){
            boolean usuarioConPrestamo =consultaPrestamoPuerto.consultarUsuarioPorIdentificacionDeUsuario(registroPrestamo.getIdentificacionUsuario());
            if(usuarioConPrestamo){
                String mensaje = "El usuario con identificación "+
                        registroPrestamo.getIdentificacionUsuario()+
                        " ya tiene un libro prestado por lo cual no se le puede realizar otro préstamo";
                throw new ExcepcionDeNegocio(mensaje);
            }
        }
    }

    public String verificadorTipoUsuario(int tipoUsuario){
        LocalDate fechaPrestamo = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        switch(tipoUsuario){
            case USUARIO_AFILIADO:
                fechaPrestamo = addDaysSkippingWeekends(fechaPrestamo, DIAS_USUARIO_AFILIADO);
                break;
            case USUARIO_EMPLEADO_BIBLIOTECA:
                fechaPrestamo = addDaysSkippingWeekends(fechaPrestamo, DIAS_USUARIO_EMPLEADO_BIBLIOTECA);
                break;
            case USUARIO_INVITADO:
                fechaPrestamo = addDaysSkippingWeekends(fechaPrestamo, DIAS_USUARIO_INVITADO);
                break;
            default:
                throw new ExcepcionDeNegocio(MENSAJE_EXCEPCION_USUARIO_INVALIDO);
        }
        return fechaPrestamo.format(formatter);
    }
    public static LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
        LocalDate result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }

    public void validarLongitudMaximaCaracteresAlfanumericos(String cadenaAlfanumerica, String tipoCampo){
        if(cadenaAlfanumerica.length()>LONGITUD_MAXIMA_CAMPOS){
            throw new ExcepcionDeNegocio(tipoCampo+MENSAJE_EXCEPCION_LONGITUD_CAMPOS_INVALIDA);
        }
    }
}
