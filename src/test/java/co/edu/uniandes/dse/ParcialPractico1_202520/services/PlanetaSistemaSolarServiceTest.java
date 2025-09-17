package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolarEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.RegionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PlanetaSistemaSolarServiceTest {

    @Autowired
    private SistemaSolarService sistemaSolarService;

    @Autowired
    private PlanetaService planetaService;

    @Autowired
    private SistemaSolarPlanetaService asociacionService;

    @Autowired
    private PlanetaSistemaSolarService planetaSistemaSolarService;

    @Test
    void obtenerSistemaSolarDePlaneta_exitoso() {
        SistemaSolarEntity sistema = sistemaSolarService.crearSistemaSolar("Mustafar I", RegionType.OUTER_RIM, 0.3, 5000);
        PlanetaEntity planeta = planetaService.crearPlaneta("Mustafar II", 10000L, 4000);

        asociacionService.asociarPlanetaASistema(sistema.getId(), planeta.getId());

        SistemaSolarEntity sistemaDelPlaneta = planetaSistemaSolarService.obtenerSistemaSolarDePlaneta(planeta.getId());
        assertEquals(sistema.getId(), sistemaDelPlaneta.getId());
    }

    @Test
    void obtenerSistemaSolarDePlaneta_fallaPorPlanetaNoExiste() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                planetaSistemaSolarService.obtenerSistemaSolarDePlaneta(9999L));
        assertTrue(e.getMessage().contains("Planeta no existe"));
    }
}
