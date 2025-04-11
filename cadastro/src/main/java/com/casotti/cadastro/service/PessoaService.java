package com.casotti.cadastro.service;

import com.casotti.cadastro.DTO.PessoaDTO;
import com.casotti.cadastro.DTO.PessoaFiltoDTO;
import com.casotti.cadastro.entity.Pessoa;
import com.casotti.cadastro.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa createPessoa(PessoaDTO dto) {
        Pessoa novaPessoa = new Pessoa(
                null,
                dto.nome(),
                dto.cpf(),
                dto.nascimento(),
                dto.email(),
                dto.telefone()
        );


        return pessoaRepository.save(novaPessoa);
    }

    public List<Pessoa> listarTodos(){
        return pessoaRepository.findAll();
    }

    public Pessoa buscarPorId(Long id) throws Exception {
        return pessoaRepository.findById(id).orElseThrow(() -> new Exception("Usuário não encontrado!"));
    }

    public List<Pessoa> buscarComFiltros(PessoaFiltoDTO filtoDTO){
        return pessoaRepository.buscarComFiltos(
                filtoDTO.nome(),
                filtoDTO.cpf(),
                filtoDTO.nascimentoInicial(),
                filtoDTO.nascimentoFinal(),
                filtoDTO.email()
                );
    }

    public Pessoa atualizarPessoa(Long id, PessoaDTO dto) throws Exception {
        Pessoa pessoaExistente = pessoaRepository.findById(id)
                .orElseThrow(() -> new Exception("Usuário não encontrado"));

        pessoaExistente.setNome(dto.nome());
        pessoaExistente.setCpf(dto.cpf());
        pessoaExistente.setNascimento(dto.nascimento());
        pessoaExistente.setEmail(dto.email());
        pessoaExistente.setTelefone(dto.telefone());

        return pessoaRepository.save(pessoaExistente);

    }

    public void deletarPessoa(Long id){
        pessoaRepository.deleteById(id);
    }
}
