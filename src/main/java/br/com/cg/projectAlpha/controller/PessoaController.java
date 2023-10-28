package br.com.cg.projectAlpha.controller;

import br.com.cg.projectAlpha.dto.PessoaRequestDto;
import br.com.cg.projectAlpha.model.Pessoa;
import br.com.cg.projectAlpha.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@RestController
@RequestMapping("/api/pessoa")
@CrossOrigin(origins = {"http://localhost:4200"})
public class PessoaController {

    @Autowired
    PessoaService pessoaService;

    @GetMapping
    public Page<Pessoa> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return pessoaService.getPessoas(paging);
    }

    @PostMapping
    public ResponseEntity<Pessoa> add(@RequestBody PessoaRequestDto pessoaRequestDto) {
        return new ResponseEntity<Pessoa>(pessoaService.addPessoa(pessoaRequestDto), HttpStatus.OK);
    }

    @PutMapping("alterar/{id}")
    public ResponseEntity<Pessoa> update (@PathVariable("id") BigInteger id, @RequestBody Pessoa pessoa){
        return new ResponseEntity<Pessoa>(pessoaService.update(id, pessoa), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById (@PathVariable("id") BigInteger id){
        return new ResponseEntity<Pessoa>(pessoaService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") BigInteger id){
        pessoaService.delete(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }
}