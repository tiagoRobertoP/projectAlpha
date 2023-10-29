package br.com.cg.projectAlpha.repository;

import br.com.cg.projectAlpha.model.Membros;
import br.com.cg.projectAlpha.model.Pessoa;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

@Repository
public interface MembrosRepository extends PagingAndSortingRepository<Membros, BigInteger> {
    List<Membros> findAll();

    Membros save(Membros membros);

    Membros delete(Membros membros);

    Optional<Membros> findById(BigInteger idprojeto);

}
