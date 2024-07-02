package com.project.form.model.repository;

import com.project.form.model.Curriculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurriculoRepository extends CrudRepository<Curriculo, Long> {

}
