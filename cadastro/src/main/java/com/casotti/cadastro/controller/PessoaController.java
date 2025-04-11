package com.casotti.cadastro.controller;

import com.casotti.cadastro.DTO.PessoaDTO;
import com.casotti.cadastro.DTO.PessoaFiltoDTO;
import com.casotti.cadastro.entity.Pessoa;
import com.casotti.cadastro.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> createPessoa(@RequestBody PessoaDTO dto){
        Pessoa novaPessoa = pessoaService.createPessoa(dto);

        return ResponseEntity.ok(novaPessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodos(){
        List<Pessoa> pessoas = pessoaService.listarTodos();

        return ResponseEntity.ok(pessoas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) throws Exception {
        Pessoa pessoa = pessoaService.buscarPorId(id);
        return ResponseEntity.ok(pessoa);
    }


    @GetMapping("/filtro")
    public ResponseEntity<List<Pessoa>> buscarComFiltros(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate nascimentoInicial,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate nascimentoFinal
    ){
        PessoaFiltoDTO filtro = new PessoaFiltoDTO(nome, cpf, nascimentoInicial, nascimentoFinal, email);
        List<Pessoa> resultado = pessoaService.buscarComFiltros(filtro);
        return ResponseEntity.ok(resultado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaDTO dto) throws Exception {
        Pessoa pessoaAtualizada = pessoaService.atualizarPessoa(id, dto);
        return ResponseEntity.ok(pessoaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id){
        pessoaService.deletarPessoa(id);

        return ResponseEntity.noContent().build();
    }

}
