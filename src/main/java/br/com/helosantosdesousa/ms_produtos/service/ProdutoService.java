package br.com.helosantosdesousa.ms_produtos.service;

import br.com.helosantosdesousa.ms_produtos.dto.ProdutoResponseDTO;
import br.com.helosantosdesousa.ms_produtos.entities.Produto;
import br.com.helosantosdesousa.ms_produtos.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //Define pro Spring que a classe é um Servico (que acessa o DB)
public class ProdutoService {
    //A classe de service depende desse repositório -> Injetar dependência


    @Autowired
    private ProdutoRepository repository;

    //TEM QUE POR DO JAVA SPRING E NAO DO JAKARTA
    @Transactional(readOnly = true)
    //ReadOnly pq vc só vai dar select, é mais seguro não permitir alterações

    //Iterar a lista e criar uma lista com os produtos

    public List<ProdutoResponseDTO> findAll() {
        List<Produto> list = repository.findAll();
        return list.stream().map(ProdutoResponseDTO::new).toList();
    }

}
