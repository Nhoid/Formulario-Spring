package com.project.form.services;

import com.project.form.model.Curriculo;
import com.project.form.model.dto.CurriculoDTOOutput;

import java.time.Instant;
import java.util.List;

public interface CurriculoService {

    void save(Curriculo curriculo);

    public List<CurriculoDTOOutput> findAll();

    void delete(Long id);

    String findCurriculoById(Long id);

    List<CurriculoDTOOutput> findAllByDate(Instant inicio, Instant fim);
}
