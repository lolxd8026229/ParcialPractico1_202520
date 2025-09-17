package co.edu.uniandes.dse.ParcialPractico1_202520.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import co.edu.uniandes.dse.ParcialPractico1_202520.entities.SistemaSolarEntity;

@Repository
public interface SistemaSolarRepository extends JpaRepository<SistemaSolarEntity, Long> {
    // Si en el futuro necesitas queries personalizadas, las defines aquí.
}
