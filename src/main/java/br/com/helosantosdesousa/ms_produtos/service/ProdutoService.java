package br.com.helosantosdesousa.ms_produtos.service;

import br.com.helosantosdesousa.ms_produtos.dto.ProdutoRequestDTO;
import br.com.helosantosdesousa.ms_produtos.dto.ProdutoResponseDTO;
import br.com.helosantosdesousa.ms_produtos.entities.Produto;
import br.com.helosantosdesousa.ms_produtos.repositories.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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

    @Transactional(readOnly = true)
    public ProdutoResponseDTO findById(Long id) {
        Produto entity = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado. Id: " + id)
        );
        return new ProdutoResponseDTO(entity);
    }

    @Transactional
    public ProdutoResponseDTO insert(@RequestBody ProdutoRequestDTO requestDTO) {
        Produto entity = new Produto();

        //converter DTO para entity
        toEntity(requestDTO, entity);
        entity = repository.save(entity);
        return new ProdutoResponseDTO(entity);
    }

    private void toEntity(ProdutoRequestDTO requestDTO, Produto entity) {
        entity.setNome(requestDTO.nome());
        entity.setDescricao(requestDTO.descricao());
        entity.setValor(requestDTO.preco());
    }

    @Transactional
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO requestDTO) {
        try {
            Produto entity = repository.getReferenceById(id);
            toEntity(requestDTO, entity);
            return new ProdutoResponseDTO(entity);
        } catch (EntityNotFoundException ex) {
            throw new EntityNotFoundException("Recurso não encontrando. Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new EntityNotFoundException("Recurso não encontrad. Id: " + id);
        }
        repository.deleteById(id);
    }

}
