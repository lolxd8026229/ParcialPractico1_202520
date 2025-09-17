package co.edu.uniandes.dse.ParcialPractico1_202520.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Data;

@Data
@Entity
public class PlanetaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;// El nombre debe terminar en I, II o III (regla de negocio)
    private Long poblacion; // Población > 0 (debería ser número, no String)
    private Integer diametro;    // Diámetro en km (entero positivo)

    @ManyToOne
    @JoinColumn(name = "sistema_solar_id")
    private SistemaSolarEntity sistemaSolar;
}
