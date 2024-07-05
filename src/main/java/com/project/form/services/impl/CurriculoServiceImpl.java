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
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CurriculoServiceImpl implements CurriculoService {

    private CurriculoRepository curriculoRepository;

    private StorageService storageService;


    public void save(Curriculo curriculo) {
        curriculoRepository.save(curriculo);
    }

    public List<CurriculoDTOOutput> findAll(){
        return curriculoRepository.findAll().stream().map(CurriculoDTOOutput::new).toList();
    }

    public void delete(Long id) {
        Path path = Paths.get(findCurriculoById(id));

        path.toFile().delete();

        curriculoRepository.deleteById(id);
    }

    @Override
    public String findCurriculoById(Long id) {
        return curriculoRepository.findArquivoUrlById(id);
    }

    @Override
    public List<CurriculoDTOOutput> findAllByDate(Instant inicio, Instant fim) {
        return curriculoRepository.findAllByDate(inicio, fim);
    }


}
