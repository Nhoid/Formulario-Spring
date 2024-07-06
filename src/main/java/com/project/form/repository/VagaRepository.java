package com.project.form.repository;

import com.project.form.model.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

// REPOSITORIO VAGA
@Repository
public interface VagaRepository extends CrudRepository<Vaga, Long> {


    @Query("SELECT v FROM Vaga v WHERE v.ativa = true")
    Collection<Vaga> findAllAtiva();
}
