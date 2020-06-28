package br.com.codenation.domain.entity;


import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.Objects;


@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Time extends Entity {

    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Long capitao;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        super(id);
        this.nome = nome;
        this.dataCriacao = dataCriacao;
        this.corUniformePrincipal = corUniformePrincipal;
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public boolean temCapitao() {
        if(Objects.isNull(this.getCapitao()))
            throw new CapitaoNaoInformadoException();
        return true;
    }
}
