package com.project.form.repository;

import com.project.form.model.Curriculo;
import com.project.form.model.dto.CurriculoDTOOutput;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;


// REPOSITORIO CURRICYLO
@Repository
public interface CurriculoRepository extends CrudRepository<Curriculo, Long> {

    List<Curriculo> findAll();

    @Query("select curriculo.arquivoUrl from Curriculo curriculo where curriculo.id = :id")
    String findArquivoUrlById(@Param("id") Long id);

    @Query("select curriculo from Curriculo curriculo where curriculo.dataHoraEnvio BETWEEN :inicio AND :fim AND curriculo.desqualificado = :desqualificado")
    List<CurriculoDTOOutput> findAllByDate(Instant inicio, Instant fim, Boolean desqualificado);

    Optional<Curriculo> findById(Long id);
}
