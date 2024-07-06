package com.project.form.model.dto;

import com.project.form.model.Curriculo;
import lombok.Data;


// OBJETO QUE EXIBE OS DADOS
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

    private Boolean desqualificado;


    public CurriculoDTOOutput(Curriculo curriculo) {
        this.id = curriculo.getId();
        this.nome = curriculo.getNome();
        this.email = curriculo.getEmail();
        this.telefone = curriculo.getTelefone();
        this.cargoDesejado = curriculo.getVaga().getNomeDaVaga();
        this.escolaridade = curriculo.getEscolaridade().getValue();
        this.observacoes = curriculo.getObservacoes();
        this.DataCadastro = curriculo.getDataHoraEnvio();
        this.desqualificado = curriculo.getDesqualificado();
    }

}
