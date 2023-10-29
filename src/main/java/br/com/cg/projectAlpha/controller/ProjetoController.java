package br.com.cg.projectAlpha.controller;

import br.com.cg.projectAlpha.dto.ProjetoRequestDto;
import br.com.cg.projectAlpha.exceptions.ProjetoException;
import br.com.cg.projectAlpha.model.Projeto;
import br.com.cg.projectAlpha.service.ProjetoService;
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
@RequestMapping("/api/projeto")
@CrossOrigin(origins = {"http://localhost:4200"})
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;

    private static final Logger LOG = LogManager.getLogger(ProjetoController.class);

    @GetMapping
    public Page<Projeto> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return projetoService.getProjetos(paging);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> findById (@PathVariable("id") BigInteger id) throws ProjetoException {
        return new ResponseEntity<Projeto>(projetoService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Projeto> add(@RequestBody ProjetoRequestDto projetoRequestDto) throws ProjetoException {
        Projeto projetoCreated = projetoService.addProjeto(projetoRequestDto);
        LOG.info("Projeto created - ID: {}", projetoCreated.getId());
        return new ResponseEntity<Projeto>(projetoCreated, HttpStatus.OK);
    }

    @PutMapping("alterar/{id}")
    public ResponseEntity<Projeto> update (@PathVariable("id") BigInteger id, @RequestBody ProjetoRequestDto projeto) throws ProjetoException {
        Projeto projetoUpdated = projetoService.update(id, projeto);
        LOG.info("Projeto updated - ID: {}", projetoUpdated.getId());
        return new ResponseEntity<Projeto>(projetoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Projeto> delete(@PathVariable("id") BigInteger id) throws ProjetoException {
        Projeto projetoDeleted = projetoService.delete(id);
        LOG.info("Projeto updated - ID: {}", projetoDeleted.getId());
        return new ResponseEntity<Projeto>(projetoDeleted, HttpStatus.OK);
    }
}
