package com.project.form.services.impl;

import com.project.form.model.Vaga;
import com.project.form.repository.VagaRepository;
import com.project.form.services.VagaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class VagaServiceImpl implements VagaService {

    private final VagaRepository vagaRepository;

    @Override
    public void save(Vaga vaga) {
        vagaRepository.save(vaga);
    }

    @Override
    public Collection<Vaga> findAll() {
        return (Collection<Vaga>) vagaRepository.findAll();
    }

    @Override
    public Vaga findById(Long id) {
        return vagaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        vagaRepository.deleteById(id);
    }


}
