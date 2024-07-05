package com.project.form.model;

import com.project.form.model.dto.VagaDTOInput;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "vagas")
@NoArgsConstructor
public class Vaga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "nome_da_vaga", nullable = false)
    private String nomeDaVaga;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "data_de_criacao", nullable = false)
    private Instant dataDeCriacao;

    @OneToMany(mappedBy = "vaga")
    private Set<Curriculo> curriculos = new LinkedHashSet<>();

    @Transient
    private int count;

    public Vaga(VagaDTOInput vaga) {
        this.nomeDaVaga = vaga.getNome();
        this.dataDeCriacao = Instant.now();
    }
}