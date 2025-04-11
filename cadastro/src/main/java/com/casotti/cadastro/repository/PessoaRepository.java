package com.casotti.cadastro.repository;

import com.casotti.cadastro.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    @Query("""
    SELECT p FROM Pessoa p
    WHERE (:nome IS NULL OR LOWER(p.nome) LIKE LOWER (CONCAT('%', :nome, '%')))
        AND (:cpf IS NULL OR p.cpf = :cpf)
        AND (:nascimentoInicial IS NULL OR p.nascimento >= :nascimentoInicial)
        AND (:nascimentoFinal IS NULL OR p.nascimento <= :nascimentoFinal)
        AND (:email IS NULL OR LOWER(p.email) LIKE LOWER(CONCAT('%', :email, '%')))
    """)

    List<Pessoa> buscarComFiltos(
            @Param("nome") String nome,
            @Param("cpf") String cpf,
            @Param("nascimentoInicial")LocalDate nascimentoInicial,
            @Param("nascimentoFinal") LocalDate nascimentoFinal,
            @Param("email") String email
    );

}
