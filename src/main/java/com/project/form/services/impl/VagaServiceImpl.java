package com.project.form.services.impl;

import com.project.form.model.Vaga;
import com.project.form.repository.VagaRepository;
import com.project.form.services.VagaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;


// SERVICE VAGA
@Service
@AllArgsConstructor
public class VagaServiceImpl implements VagaService {

    private final VagaRepository vagaRepository;

    @Override
    public void save(Vaga vaga) {
        vagaRepository.save(vaga); // SALVA VAGA
    }

    @Override
    public Collection<Vaga> findAll() {
        return (Collection<Vaga>) vagaRepository.findAll(); // ENCONTRA TODAS AS VAGAS
    }

    @Override
    public Collection<Vaga> findAllAtiva() {
        return vagaRepository.findAllAtiva(); // ENCONTRA VAGAS ATIVAS
    }

    @Override
    public Vaga findById(Long id) {
        return vagaRepository.findById(id).orElse(null); // ENCONTRA VAGA POR ID
    }

    @Override
    public void delete(Long id) {
        vagaRepository.deleteById(id); // DELETA VAGA
    }


}
