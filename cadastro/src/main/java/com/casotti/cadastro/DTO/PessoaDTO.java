package com.casotti.cadastro.DTO;

import java.time.LocalDate;

public record PessoaDTO (
        String nome,
        String cpf,
        LocalDate nascimento,
        String email,
        String telefone
) {
}
