package br.com.cg.projectAlpha.service;

import br.com.cg.projectAlpha.dto.PessoaRequestDto;
import br.com.cg.projectAlpha.model.Pessoa;
import br.com.cg.projectAlpha.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
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

    public Pessoa update(BigInteger id, Pessoa pessoa) {
        Pessoa pessoaData = pessoaRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Erro ao encontrar pessoa"));

        return pessoaRepository.save(Pessoa.builder()
                        .id(pessoaData.getId())
                        .cpf(pessoa.getCpf())
                        .nome(pessoa.getNome())
                        .datanascimento(pessoa.getDatanascimento())
                .build());
    }


    public Pessoa findById(BigInteger id) {
        return pessoaRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Pessoa não encontrada"));
    }

    public void delete(BigInteger id) {
        Pessoa pessoa = findById(id);
        pessoaRepository.delete(pessoa);
    }
}
