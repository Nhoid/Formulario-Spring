package com.project.form.services.impl;


import com.project.form.model.Curriculo;
import com.project.form.model.dto.CurriculoDTOOutput;
import com.project.form.repository.CurriculoRepository;
import com.project.form.services.CurriculoService;
import com.project.form.services.StorageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

// IMPL DE CURRICULO SERVICE
@Service
@AllArgsConstructor
public class CurriculoServiceImpl implements CurriculoService {

    private CurriculoRepository curriculoRepository;

    private StorageService storageService;


    public void save(Curriculo curriculo) {
        curriculoRepository.save(curriculo); // SALVA CURRICULO NO BANCO DE DADOS
    }

    public List<CurriculoDTOOutput> findAll(){
        return curriculoRepository.findAll().stream().map(CurriculoDTOOutput::new).toList(); // RETORNA CURRICULOS PARA SEREM EXIBIDOS
    }

    public void delete(Long id) { // DELETA USUARIO E CURRICULO
        Path path = Paths.get(findCurriculoById(id));

        path.toFile().delete();

        curriculoRepository.deleteById(id);
    }

    @Override
    public String findCurriculoById(Long id) { // RETORNA ENDERECO DO CURRICULO COM BASE NO ID
        return curriculoRepository.findArquivoUrlById(id);
    }

    @Override
    public List<CurriculoDTOOutput> findAllByDate(Instant inicio, Instant fim, Boolean desqualificado) {
        return curriculoRepository.findAllByDate(inicio, fim, desqualificado); // RETORNA CURRICULOS FITLRADOS
    }

    public Curriculo findById(Long id) { // ENCONTRA CURRICULO COM BASE EM ID
        return curriculoRepository.findById(id).get();
    }

}
