package br.com.cg.projectAlpha.controller;

import br.com.cg.projectAlpha.dto.PessoaRequestDto;
import br.com.cg.projectAlpha.exceptions.PessoaException;
import br.com.cg.projectAlpha.model.Pessoa;
import br.com.cg.projectAlpha.service.PessoaService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger LOG = LogManager.getLogger(PessoaController.class);

    @GetMapping
    public Page<Pessoa> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return pessoaService.getPessoas(paging);
    }

    @PostMapping
    public ResponseEntity<Pessoa> add(@RequestBody PessoaRequestDto pessoaRequestDto) {
        Pessoa pessoa = pessoaService.addPessoa(pessoaRequestDto);
        LOG.info("Pessoa created - ID: {}", pessoa.getIdPessoa());
        return new ResponseEntity<Pessoa>(pessoa, HttpStatus.OK);
    }

    @PutMapping("alterar/{id}")
    public ResponseEntity<Pessoa> update (@PathVariable("id") BigInteger id, @RequestBody Pessoa pessoa) throws PessoaException {
        Pessoa pessoaUpdated = pessoaService.update(id, pessoa);
        LOG.info("Pessoa updated - ID: {}", pessoaUpdated.getIdPessoa());
        return new ResponseEntity<Pessoa>(pessoaUpdated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById (@PathVariable("id") BigInteger id) throws PessoaException {
        return new ResponseEntity<Pessoa>(pessoaService.findById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pessoa> delete(@PathVariable("id") BigInteger id) throws PessoaException {
        Pessoa pessoaDeleted = pessoaService.delete(id);
        LOG.info("Pessoa created - ID: {}", id);
        return new ResponseEntity<Pessoa>(pessoaDeleted, HttpStatus.OK);
    }
}