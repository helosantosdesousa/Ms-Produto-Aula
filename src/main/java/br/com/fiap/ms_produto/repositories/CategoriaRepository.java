package br.com.fiap.ms_produto.repositories;

import br.com.fiap.ms_produto.entities.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
