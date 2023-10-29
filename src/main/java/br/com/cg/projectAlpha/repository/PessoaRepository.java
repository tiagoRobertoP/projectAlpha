package br.com.cg.projectAlpha.repository;

import br.com.cg.projectAlpha.model.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaRepository extends PagingAndSortingRepository<Pessoa, BigInteger> {

    List<Pessoa> findAllByOrderByNomeDesc();

    Pessoa save(Pessoa pessoa);

    Optional<Pessoa> findById(BigInteger id);

    Pessoa delete(Pessoa pessoa);

}
