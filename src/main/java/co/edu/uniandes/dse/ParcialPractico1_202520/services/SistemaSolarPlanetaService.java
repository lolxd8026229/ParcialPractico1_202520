package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolarEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SistemaSolarPlanetaService {

    private final SistemaSolarRepository sistemaRepo;
    private final PlanetaRepository planetaRepo;

    public SistemaSolarPlanetaService(SistemaSolarRepository sistemaRepo, PlanetaRepository planetaRepo) {
        this.sistemaRepo = sistemaRepo;
        this.planetaRepo = planetaRepo;
    }

    @Transactional
    public void asociarPlanetaASistema(Long sistemaId, Long planetaId) {
        SistemaSolarEntity sistema = sistemaRepo.findById(sistemaId)
                .orElseThrow(() -> new IllegalArgumentException("SistemaSolar no existe."));
        PlanetaEntity planeta = planetaRepo.findById(planetaId)
                .orElseThrow(() -> new IllegalArgumentException("Planeta no existe."));

        long poblacionTotal = sistema.getPlanetas().stream().mapToLong(PlanetaEntity::getPoblacion).sum()
                + planeta.getPoblacion();

        double ratioActual = (double) sistema.getStormtroopersAsignados() / poblacionTotal;

        if (ratioActual < sistema.getRatioMinimo()) {
            throw new IllegalArgumentException("La asociación rompe el ratio mínimo del sistema solar.");
        }

        sistema.agregarPlaneta(planeta); // mantiene bidireccionalidad
        sistemaRepo.save(sistema);
        planetaRepo.save(planeta);
    }
}
