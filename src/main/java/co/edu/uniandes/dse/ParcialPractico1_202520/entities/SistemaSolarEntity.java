package co.edu.uniandes.dse.ParcialPractico1_202520.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class SistemaSolarEntity extends BaseEntity {

    @Column(length = 30, nullable = false)    // Nombre del sistema solar (< 31 caracteres)
    private String nombre;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)     // Región de la galaxia (usamos enum)
    private RegionType region;

    @Column(nullable = false)     // Ratio mínimo (decimal entre 0.2 y 0.6)
    private double ratioMinimo;

    @Column(nullable = false)     // Número de stormtroopers asignados (> 1000)
    private long stormtroopersAsignados;

    // Relación 1:N con planetas
    @OneToMany(mappedBy = "sistemaSolar")
    private List<PlanetaEntity> planetas = new ArrayList<>();

    public void agregarPlaneta(PlanetaEntity planeta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'agregarPlaneta'");
    }
}
