package br.com.fiap.ms_produto.repositories;

import br.com.fiap.ms_produto.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

}
