package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolarEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.RegionType;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class SistemaSolarPlanetaTest {

    @Autowired
    private SistemaSolarService sistemaSolarService;

    @Autowired
    private PlanetaService planetaService;

    @Autowired
    private SistemaSolarPlanetaService asociacionService;

    @Autowired
    private PlanetaRepository planetaRepo;

    @Test
    void asociacion_exitoso() {
        SistemaSolarEntity sistema = sistemaSolarService.crearSistemaSolar("Korriban I", RegionType.OUTER_RIM, 0.2, 1000000);
        PlanetaEntity planeta = planetaService.crearPlaneta("Korriban II", 2000000L, 12500);

        asociacionService.asociarPlanetaASistema(sistema.getId(), planeta.getId());

        PlanetaEntity planetaGuardado = planetaRepo.findById(planeta.getId()).orElseThrow();
        assertEquals(sistema.getId(), planetaGuardado.getSistemaSolar().getId());
    }

    @Test
    void asociacion_fallaPorSistemaNoExiste() {
        PlanetaEntity planeta = planetaService.crearPlaneta("Zakuul III", 1000L, 9000);
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                asociacionService.asociarPlanetaASistema(9999L, planeta.getId()));
        assertTrue(e.getMessage().contains("SistemaSolar no existe"));
    }

    @Test
    void asociacion_fallaPorPlanetaNoExiste() {
        SistemaSolarEntity sistema = sistemaSolarService.crearSistemaSolar("Rodia I", RegionType.COLONIES, 0.3, 2000000);
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                asociacionService.asociarPlanetaASistema(sistema.getId(), 9999L));
        assertTrue(e.getMessage().contains("Planeta no existe"));
    }

    @Test
    void asociacion_fallaPorRatio() {
        SistemaSolarEntity sistema = sistemaSolarService.crearSistemaSolar("Tatoo I", RegionType.OUTER_RIM, 0.5, 10000);
        PlanetaEntity planeta = planetaService.crearPlaneta("Tatooine II", 100000L, 10465);

        Exception e = assertThrows(IllegalArgumentException.class, () ->
                asociacionService.asociarPlanetaASistema(sistema.getId(), planeta.getId()));
        assertTrue(e.getMessage().contains("ratio"));
    }
}
