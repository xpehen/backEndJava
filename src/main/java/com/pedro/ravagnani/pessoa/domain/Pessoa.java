package com.pedro.ravagnani.pessoa.domain;

import lombok.*;
import com.pedro.ravagnani.contato.domain.Contato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pessoas")
public class Pessoa {

    @Id
    @Column(name = "id_pessoa")
    private String id;

    @NotEmpty(message = "O nome da pessoa não pode ser vazio.")
    @NotNull(message = "O nome da pessoa não pode ser nulo.")
    private String nome;

    @NotEmpty(message = "O CPF da pessoa não pode ser vazio.")
    @CPF(message="CPF inválido")
    private String cpf;

    @Past(message = "A data de nascimento não pode ser uma data futura.")
    @Column(name = "data_nasc", nullable = false)
    private Date dataNascimento;


    @JsonIgnore
    @OneToMany(mappedBy ="pessoa", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contato> contatos = new ArrayList<>();

}
