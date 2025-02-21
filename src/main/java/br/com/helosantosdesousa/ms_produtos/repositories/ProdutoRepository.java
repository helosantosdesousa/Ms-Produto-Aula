package br.com.helosantosdesousa.ms_produtos.repositories;

import br.com.helosantosdesousa.ms_produtos.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
