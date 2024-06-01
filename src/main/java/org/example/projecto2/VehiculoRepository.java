package org.example.projecto2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    @Query("SELECT AVG(v.potencia) FROM Vehiculo v WHERE v.tipo = :tipo")
    Double findAveragePotenciaByTipo(@Param("tipo") String tipo);

    @Query("SELECT MAX(v.potencia) FROM Vehiculo v WHERE v.tipo = :tipo")
    Integer findMaxPotenciaByTipo(@Param("tipo") String tipo);

    Double getAveragePotenciaByTipo(String tipo);

    Integer getMaxPotenciaByTipo(String tipo);
}
