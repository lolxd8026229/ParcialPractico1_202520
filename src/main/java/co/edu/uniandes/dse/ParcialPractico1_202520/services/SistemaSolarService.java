package co.edu.uniandes.dse.ParcialPractico1_202520.services;

import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolarEntity;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.RegionType;
import co.edu.uniandes.dse.ParcialPractico1_202520.repositories.SistemaSolarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SistemaSolarService {

    private final SistemaSolarRepository repo;

    public SistemaSolarService(SistemaSolarRepository repo) {
        this.repo = repo;
    }

    @Transactional
    public SistemaSolarEntity crearSistemaSolar(String nombre, RegionType region,
                                               double ratioMinimo, long stormtroopersAsignados) {
        if (nombre == null || nombre.length() > 30)
            throw new IllegalArgumentException("El nombre debe tener menos de 31 caracteres.");
        if (ratioMinimo < 0.2 || ratioMinimo > 0.6)
            throw new IllegalArgumentException("El ratio debe estar entre 0.2 y 0.6.");
        if (stormtroopersAsignados <= 1000)
            throw new IllegalArgumentException("Stormtroopers debe ser > 1000.");

        SistemaSolarEntity sistema = new SistemaSolarEntity();
        sistema.setNombre(nombre);
        sistema.setRegion(region);
        sistema.setRatioMinimo(ratioMinimo);
        sistema.setStormtroopersAsignados(stormtroopersAsignados);

        return repo.save(sistema);
    }
}
