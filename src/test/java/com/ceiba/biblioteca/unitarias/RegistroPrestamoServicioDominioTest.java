package com.ceiba.biblioteca.unitarias;

import com.ceiba.biblioteca.dominio.servicio.RegistroPrestamoServicioDominio;
import com.ceiba.biblioteca.infraestructura.implementacionPuerto.ConsultaPrestamoPuertoImp;
import com.ceiba.biblioteca.infraestructura.implementacionPuerto.RegistroPrestamoPuertoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RegistroPrestamoServicioDominioTest {
    @Spy
    @InjectMocks
    RegistroPrestamoServicioDominio registroPrestamoServicioDominio;

    @Mock
    ConsultaPrestamoPuertoImp consultaPrestamoPuertoImp;

    @Mock
    RegistroPrestamoPuertoImp registroPrestamoPuertoImp;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void probarVerificadorTipoUsuario(){

        LocalDate fechaPrestamo = LocalDate.now();
        fechaPrestamo = addDaysSkippingWeekends(fechaPrestamo, 7);
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        Assert.assertEquals(fechaPrestamo.format(formato),registroPrestamoServicioDominio.verificadorTipoUsuario(3));

    }
    private static LocalDate addDaysSkippingWeekends(LocalDate date, int days) {
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
}
