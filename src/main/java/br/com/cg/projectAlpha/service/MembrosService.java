package br.com.cg.projectAlpha.service;

import br.com.cg.projectAlpha.enums.PessoaErrors;
import br.com.cg.projectAlpha.enums.ProjetoErrors;
import br.com.cg.projectAlpha.exceptions.PessoaException;
import br.com.cg.projectAlpha.exceptions.ProjetoException;
import br.com.cg.projectAlpha.model.Membros;
import br.com.cg.projectAlpha.model.MembrosId;
import br.com.cg.projectAlpha.model.Pessoa;
import br.com.cg.projectAlpha.model.Projeto;
import br.com.cg.projectAlpha.repository.MembrosRepository;
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
public class MembrosService {

    @Autowired
    private MembrosRepository membrosRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    ProjetoRepository projetoRepository;

    public Page<Membros> getMembros(Pageable pageable) {
        List<Membros> membrosList = membrosRepository.findAll();
        List<Membros> pageList = membrosList.stream()
                .skip(pageable.getOffset())
                .limit(pageable.getPageSize())
                .collect(Collectors.toList());
        final Page<Membros> page = new PageImpl<>(pageList, pageable, membrosList.size());

        return page;
    }

    public Membros addMembros(MembrosId membrosId) throws PessoaException, ProjetoException {
        verifyPessoaProjeto(membrosId);

        Membros membros = Membros.builder()
                .membrosId(membrosId)
                .build();

        return membrosRepository.save(membros);
    }

    public Membros delete(MembrosId membrosId) throws ProjetoException, PessoaException {
        verifyPessoaProjeto(membrosId);
        
        Membros membros = Membros.builder()
                .membrosId(membrosId)
                .build();

        return membrosRepository.delete(membros);
    }

    private void verifyPessoaProjeto(MembrosId membros) throws PessoaException, ProjetoException {
        Pessoa pessoa = pessoaRepository.findById(membros.getPessoa().getIdPessoa()).orElseThrow(
                ()-> new PessoaException(PessoaErrors.PESSOA_NOT_FOUND, membros.getPessoa().getIdPessoa().toString()));

        Projeto projeto = projetoRepository.findById(membros.getIdprojeto()).orElseThrow(
                ()-> new ProjetoException(ProjetoErrors.PROJETO_NOT_FOUND, membros.getIdprojeto().toString()));
    }

    public List<Membros> findById(BigInteger id) throws ProjetoException {
        List<Membros> membros;
        try{
            membros = membrosRepository.findAllByMembrosId_Idprojeto(id);
        }catch (Exception e){
            throw new ProjetoException(ProjetoErrors.PROJETO_NOT_FOUND, id.toString());
        }

        return membros;
    }
}
