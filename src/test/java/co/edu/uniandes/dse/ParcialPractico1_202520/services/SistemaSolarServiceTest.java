package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolarEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.RegionType;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SistemaSolarServiceTest {

    @Autowired
    private SistemaSolarService sistemaSolarService;

    @Autowired
    private SistemaSolarRepository repo;

    @Test
    void crearSistemaSolar_exitoso() {
        SistemaSolarEntity s = sistemaSolarService.crearSistemaSolar(
                "Corellia I", RegionType.CORE, 0.3, 2000);

        assertNotNull(s.getId());
        assertEquals("Corellia I", s.getNombre());
        assertEquals(0.3, s.getRatioMinimo());
    }

    @Test
    void crearSistemaSolar_fallaPorNombreLargo() {
        String nombreLargo = "X".repeat(35);
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                sistemaSolarService.crearSistemaSolar(nombreLargo, RegionType.MID_RIM, 0.3, 2000));
        assertTrue(e.getMessage().contains("menos de 31"));
    }

    @Test
    void crearSistemaSolar_fallaPorRatio() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                sistemaSolarService.crearSistemaSolar("Kashyyyk I", RegionType.OUTER_RIM, 0.7, 2000));
        assertTrue(e.getMessage().contains("0.2 y 0.6"));
    }

    @Test
    void crearSistemaSolar_fallaPorStormtroopers() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                sistemaSolarService.crearSistemaSolar("Manaan I", RegionType.COLONIES, 0.3, 500));
        assertTrue(e.getMessage().contains("> 1000"));
    }
}
