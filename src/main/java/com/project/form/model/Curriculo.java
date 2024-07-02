package com.project.form.model;

import com.project.form.model.dto.CurriculoDTO;
import com.project.form.model.enums.Escolaridade;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "curriculos")
public class Curriculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "cargo_desejado", nullable = false)
    private String cargoDesejado;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "ENUM('Fundamental', 'Fundamental incompleto', 'Medio', 'Medio incompleto', 'Superior', 'Superior incompleto')")
    private Escolaridade escolaridade;

    @Column(name = "observacoes")
    private String observacoes;

    @Column(name = "arquivo_url", nullable = false)
    private String arquivoUrl;

    @Column(name = "ip_envio", nullable = false, length = 45)
    private String ipEnvio;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_hora_envio")
    private Instant dataHoraEnvio;


    public Curriculo(CurriculoDTO curriculoDTO) {

        this.nome = curriculoDTO.getNome();
        this.email = curriculoDTO.getEmail();
        this.telefone = curriculoDTO.getTelefone();
        this.cargoDesejado = curriculoDTO.getCargo();
        this.escolaridade = Escolaridade.valueOf(curriculoDTO.getEscolaridade());
        this.observacoes = curriculoDTO.getObservacoes();
        this.dataHoraEnvio = Instant.now();

    }

    public Curriculo() {

    }
}