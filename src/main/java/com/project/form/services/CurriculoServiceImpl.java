package com.project.form.services;


import com.project.form.model.Curriculo;
import com.project.form.model.repository.CurriculoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CurriculoServiceImpl {

    private CurriculoRepository curriculoRepository;


    public void save(Curriculo curriculo) {
        curriculoRepository.save(curriculo);
    }
}
