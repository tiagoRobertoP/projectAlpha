package br.com.cg.projectAlpha.service;

import br.com.cg.projectAlpha.dto.PessoaRequestDto;
import br.com.cg.projectAlpha.enums.PessoaErrors;
import br.com.cg.projectAlpha.exceptions.PessoaException;
import br.com.cg.projectAlpha.model.Pessoa;
import br.com.cg.projectAlpha.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<Pessoa> getPessoas(Pageable pageable){
        List<Pessoa> pessoaList = pessoaRepository.findAllByOrderByNomeDesc();
        List<Pessoa> pageList = pessoaList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        final Page<Pessoa> page = new PageImpl<>(pageList, pageable, pessoaList.size());

        return page;
    }

    public Pessoa addPessoa(PessoaRequestDto pessoaRequestDto) {
        Pessoa pessoa = Pessoa.builder()
                .cpf(pessoaRequestDto.getCpf())
                .nome(pessoaRequestDto.getNome())
                .datanascimento(pessoaRequestDto.getDatanascimento())
                .build();
        return pessoaRepository.save(pessoa);
    }

    public Pessoa update(BigInteger id, Pessoa pessoa) throws PessoaException {
        Pessoa pessoaData = pessoaRepository.findById(id).orElseThrow(
                () -> new PessoaException(PessoaErrors.PESSOA_NOT_FOUND, id.toString()));

        return pessoaRepository.save(Pessoa.builder()
                        .idPessoa(pessoaData.getIdPessoa())
                        .cpf(pessoa.getCpf())
                        .nome(pessoa.getNome())
                        .datanascimento(pessoa.getDatanascimento())
                .build());
    }


    public Pessoa findById(BigInteger id) throws PessoaException {
        return pessoaRepository.findById(id).orElseThrow(
                ()-> new PessoaException(PessoaErrors.PESSOA_NOT_FOUND, id.toString()));
    }

    public Pessoa delete(BigInteger id) throws PessoaException {
        Pessoa pessoa = findById(id);
        pessoaRepository.delete(pessoa);
        return pessoa;
    }
}
