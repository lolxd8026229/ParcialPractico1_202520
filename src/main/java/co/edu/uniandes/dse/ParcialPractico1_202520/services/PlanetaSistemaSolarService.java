package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolarEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanetaSistemaSolarService {

    private final PlanetaRepository planetaRepo;

    public PlanetaSistemaSolarService(PlanetaRepository planetaRepo) {
        this.planetaRepo = planetaRepo;
    }

    public SistemaSolarEntity obtenerSistemaSolarDePlaneta(Long planetaId) {
        PlanetaEntity planeta = planetaRepo.findById(planetaId)
                .orElseThrow(() -> new IllegalArgumentException("Planeta no existe."));
        return planeta.getSistemaSolar();
    }
}
