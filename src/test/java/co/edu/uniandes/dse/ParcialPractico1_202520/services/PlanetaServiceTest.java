package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class PlanetaServiceTest {

    @Autowired
    private PlanetaService planetaService;

    @Autowired
    private PlanetaRepository repo;

    @Test
    void crearPlaneta_exitoso() {
        PlanetaEntity p = planetaService.crearPlaneta("Dromund Kaas II", 2000L, 11000);
        assertNotNull(p.getId());
        assertEquals(2000L, p.getPoblacion());
    }

    @Test
    void crearPlaneta_fallaPorNombre() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                planetaService.crearPlaneta("Dromund Kaas", 2000L, 11000));
        assertTrue(e.getMessage().contains("I, II o III"));
    }

    @Test
    void crearPlaneta_fallaPorPoblacion() {
        Exception e = assertThrows(IllegalArgumentException.class, () ->
                planetaService.crearPlaneta("Onderon I", 0L, 8000));
        assertTrue(e.getMessage().contains("población"));
    }
}
