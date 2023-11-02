package br.com.cg.projectAlpha.controller;

import br.com.cg.projectAlpha.exceptions.PessoaException;
import br.com.cg.projectAlpha.exceptions.ProjetoException;
import br.com.cg.projectAlpha.model.Membros;
import br.com.cg.projectAlpha.model.MembrosId;
import br.com.cg.projectAlpha.model.Projeto;
import br.com.cg.projectAlpha.service.MembrosService;
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
import java.util.List;

@RestController
@RequestMapping("/api/membros")
@CrossOrigin(origins = {"http://localhost:4200"})
public class MembrosController {

    @Autowired
    private MembrosService membrosService;

    private static final Logger LOG = LogManager.getLogger(PessoaController.class);

    @GetMapping
    public Page<Membros> get(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page, size);
        return membrosService.getMembros(paging);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Membros>> findById (@PathVariable("id") BigInteger id) throws ProjetoException {
        return new ResponseEntity<List<Membros>>(membrosService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Membros> add(@RequestBody MembrosId membros) throws PessoaException, ProjetoException {
        Membros membrosCreated = membrosService.addMembros(membros);
        LOG.info("Membros created - ID: {}", membrosCreated.getMembrosId().getIdprojeto());
        return new ResponseEntity<Membros>(membrosCreated, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<HttpStatus> delete(@RequestBody MembrosId membros) throws ProjetoException, PessoaException {
        membrosService.delete(membros);
        LOG.info("Membro {} deleted from project: {}", membros.getPessoa().getIdPessoa(), membros.getIdprojeto());
        return new ResponseEntity<HttpStatus>(HttpStatus.OK);
    }

}
