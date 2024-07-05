package com.project.form.repository;

import com.project.form.model.Curriculo;
import com.project.form.model.dto.CurriculoDTOOutput;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.nio.file.Path;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface CurriculoRepository extends CrudRepository<Curriculo, Long> {

    List<Curriculo> findAll();

    @Query("select curriculo.arquivoUrl from Curriculo curriculo where curriculo.id = :id")
    String findArquivoUrlById(@Param("id") Long id);

    @Query("select curriculo from Curriculo curriculo where curriculo.dataHoraEnvio BETWEEN :inicio AND :fim")
    List<CurriculoDTOOutput> findAllByDate(Instant inicio, Instant fim);
}
