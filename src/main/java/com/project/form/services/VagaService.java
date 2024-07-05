package com.project.form.services;

import com.project.form.model.Curriculo;
import com.project.form.model.Vaga;

import java.util.Collection;
import java.util.List;

public interface VagaService {

    public void save(Vaga vaga);

    public Collection<Vaga> findAll();

    public Vaga findById(Long id);

    void delete(Long id);

}
