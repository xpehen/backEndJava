package com.pedro.ravagnani.contato.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pedro.ravagnani.pessoa.domain.Pessoa;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contatos")
public class Contato {

    @Id
    @Column(name = "id_contato")
    private String id;

    @NotEmpty(message = "O nome do contato não pode ser vazio.")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "telefone", nullable = false)
    private Long telefone;

    @Email
    @NotEmpty(message = "E-mail do contato não pode ser vazio.")
    @Column(name = "email", nullable = false)
    private String email;

    @ManyToOne
    @NotNull
    @JoinColumn(name="id_pessoa")
    private Pessoa pessoa;
}