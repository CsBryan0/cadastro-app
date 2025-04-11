package com.casotti.cadastro.DTO;

import java.time.LocalDate;

public record PessoaFiltoDTO (
        String nome,
        String cpf,
        LocalDate nascimentoInicial,
        LocalDate nascimentoFinal,
        String email
){
}
