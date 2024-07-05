package com.project.form.model.dto;

import com.project.form.model.Curriculo;
import com.project.form.model.enums.Escolaridade;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.Instant;

@Data
public class CurriculoDTOOutput {

    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cargoDesejado;

    private String escolaridade;

    private String observacoes;

    private String DataCadastro;


    public CurriculoDTOOutput(Curriculo curriculo) {
        this.id = curriculo.getId();
        this.nome = curriculo.getNome();
        this.email = curriculo.getEmail();
        this.telefone = curriculo.getTelefone();
        this.cargoDesejado = curriculo.getVaga().getNomeDaVaga();
        this.escolaridade = curriculo.getEscolaridade().getValue();
        this.observacoes = curriculo.getObservacoes();
        this.DataCadastro = curriculo.getDataHoraEnvio();
    }

}
