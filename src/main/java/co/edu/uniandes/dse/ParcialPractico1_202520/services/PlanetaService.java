package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.PlanetaEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.PlanetaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Pattern;

@Service
public class PlanetaService {

    private final PlanetaRepository repo;
    private static final Pattern SUFIJO_ROMANO = Pattern.compile(".*\\s(I|II|III)$");

    public PlanetaService(PlanetaRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public PlanetaEntity crearPlaneta(String nombre, Long poblacion, Integer diametro) {
        if (nombre == null || !SUFIJO_ROMANO.matcher(nombre).matches())
            throw new IllegalArgumentException("El nombre debe terminar en I, II o III.");
        if (poblacion == null || poblacion <= 0)
            throw new IllegalArgumentException("La población debe ser > 0.");
        if (diametro == null || diametro <= 0)
            throw new IllegalArgumentException("El diámetro debe ser positivo.");

        PlanetaEntity planeta = new PlanetaEntity();
        planeta.setNombre(nombre);
        planeta.setPoblacion(poblacion);
        planeta.setDiametro(diametro);

        return repo.save(planeta);
    }
}
