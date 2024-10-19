package br.com.felipe.books.repository;

import br.com.felipe.books.domain.model.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReaderRepository extends JpaRepository<Reader,Long> {

    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);

}
