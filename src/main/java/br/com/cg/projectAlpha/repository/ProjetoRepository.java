package br.com.cg.projectAlpha.repository;
import br.com.cg.projectAlpha.model.Projeto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjetoRepository extends PagingAndSortingRepository<Projeto, BigInteger> {

    List<Projeto> findAll();

    Projeto save(Projeto projeto);

    Optional<Projeto> findById(BigInteger id);

    Projeto delete(Projeto projeto);
}
