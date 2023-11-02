package br.com.cg.projectAlpha.service;

import br.com.cg.projectAlpha.dto.ProjetoRequestDto;
import br.com.cg.projectAlpha.enums.ProjetoErrors;
import br.com.cg.projectAlpha.exceptions.ProjetoException;
import br.com.cg.projectAlpha.model.Pessoa;
import br.com.cg.projectAlpha.model.Projeto;
import br.com.cg.projectAlpha.repository.PessoaRepository;
import br.com.cg.projectAlpha.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Page<Projeto> getProjetos(Pageable pageable) {
        List<Projeto> projetoList = projetoRepository.findAll();
        List<Projeto> pageList = projetoList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        final Page<Projeto> page = new PageImpl<>(pageList, pageable, projetoList.size());

        return page;
    }

    public Projeto addProjeto(ProjetoRequestDto projetoRequestDto) throws ProjetoException {
        Pessoa gerente = pessoaRepository.findById(projetoRequestDto.getGerente()).orElseThrow(
                ()-> new ProjetoException(ProjetoErrors.PROJETO_NOT_FOUND, projetoRequestDto.getIdgerente().toString()));

        Projeto projeto = Projeto.builder()
                .nome(projetoRequestDto.getNome())
                .descricao(projetoRequestDto.getDescricao())
                .risco(projetoRequestDto.getRisco())
                .status(projetoRequestDto.getStatus())
                .orcamento(projetoRequestDto.getOrcamento())
                .dataFim(projetoRequestDto.getDataFim())
                .dataInicio(projetoRequestDto.getDataInicio())
                .dataPrevisaoFim(projetoRequestDto.getDataPrevisaoFim())
                .gerente(gerente)
                .build();
        return projetoRepository.save(projeto);
    }

    public Projeto findById(BigInteger id) throws ProjetoException {
        Projeto projeto;
        try{
            projeto = projetoRepository.findById(id).get();
        }catch (Exception e){
            throw new ProjetoException(ProjetoErrors.PROJETO_NOT_FOUND, id.toString());
        }

        return projeto;
    }

    public Projeto update(BigInteger id, ProjetoRequestDto projeto) throws ProjetoException {
        Projeto projetoData;
        try{
            projetoData = projetoRepository.findById(id).get();
        }catch (Exception e){
            throw new ProjetoException(ProjetoErrors.PROJETO_NOT_FOUND, id.toString());
        }

        return projetoRepository.save(Projeto.builder()
                .id(projetoData.getId())
                .nome(projeto.getNome())
                .descricao(projeto.getDescricao())
                .risco(projeto.getRisco())
                .status(projeto.getStatus())
                .orcamento(projeto.getOrcamento())
                .dataFim(projeto.getDataFim())
                .dataInicio(projeto.getDataInicio())
                .dataPrevisaoFim(projeto.getDataPrevisaoFim())
                .gerente(Pessoa.builder()
                        .idPessoa(projeto.getGerente())
                        .build())
                .build());
    }

    public Projeto delete(BigInteger id) throws ProjetoException {
        Projeto projeto = findById(id);
        projetoRepository.delete(projeto);
        return projeto;
    }
}
