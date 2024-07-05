package com.project.form.model;

import com.project.form.model.dto.CurriculoDTOInput;
import com.project.form.model.enums.Escolaridade;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Entity
@Table(name = "curriculos")
@NoArgsConstructor
public class Curriculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nome", nullable = false)
    private String nome;

    @Size(max = 255)
    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @Size(max = 20)
    @NotNull
    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "vaga_id", nullable = false)
    private Vaga vaga;

    @NotNull
    @Lob
    @Column(name = "escolaridade", nullable = false)
    @Enumerated(EnumType.STRING)
    private Escolaridade escolaridade;

    @Lob
    @Column(name = "observacoes")
    private String observacoes;

    @Size(max = 255)
    @NotNull
    @Column(name = "arquivo_url", nullable = false)
    private String arquivoUrl;

    @Size(max = 45)
    @NotNull
    @Column(name = "ip_envio", nullable = false, length = 45)
    private String ipEnvio;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_hora_envio", nullable = false)
    private Instant dataHoraEnvio;

    public Curriculo(CurriculoDTOInput curriculoDTOInput) {
        this.nome = curriculoDTOInput.getNome();
        this.email = curriculoDTOInput.getEmail();
        this.telefone = curriculoDTOInput.getTelefone();
        this.escolaridade = Escolaridade.valueOf( curriculoDTOInput.getEscolaridade() );
        this.observacoes = curriculoDTOInput.getObservacoes();
        this.dataHoraEnvio = Instant.now();
    }

    @Override
    public String toString() {
        return "Curriculo{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", vaga=" + vaga +
                ", escolaridade=" + escolaridade +
                ", observacoes='" + observacoes + '\'' +
                ", arquivoUrl='" + arquivoUrl + '\'' +
                ", ipEnvio='" + ipEnvio + '\'' +
                ", dataHoraEnvio=" + dataHoraEnvio +
                '}';
    }

    public String getDataHoraEnvio() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
                .withZone(ZoneId.systemDefault());

        return  formatter.format(this.dataHoraEnvio);
    }
}