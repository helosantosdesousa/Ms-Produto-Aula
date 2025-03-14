package br.com.fiap.ms_produto.service;

import br.com.fiap.ms_produto.dto.ProdutoRequestDTO;
import br.com.fiap.ms_produto.dto.ProdutoResponseDTO;
import br.com.fiap.ms_produto.entities.Categoria;
import br.com.fiap.ms_produto.entities.Produto;
import br.com.fiap.ms_produto.repositories.CategoriaRepository;
import br.com.fiap.ms_produto.repositories.ProdutoRepository;
import br.com.fiap.ms_produto.service.exceptions.DatabaseException;
import br.com.fiap.ms_produto.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> findAll() {
        List<Produto> list = repository.findAll();
        return list.stream().map(ProdutoResponseDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO findById(Long id) {

        Produto entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. Id: " + id)
        );
        return new ProdutoResponseDTO(entity);
    }

    @Transactional
    public ProdutoResponseDTO insert(ProdutoRequestDTO requestDTO) {

        try {
            Produto entity = new Produto();
            // metodo auxiliar para converter DTO para Entity
            toEntity(requestDTO, entity);
            entity = repository.save(entity);
            return new ProdutoResponseDTO(entity);
        } catch (DataIntegrityViolationException ex) {
            throw new DatabaseException("Violação de integridade referencial - Categoria ID: "
                    + requestDTO.categoria().getId());
        }
    }

    @Transactional
    public ProdutoResponseDTO update(Long id, ProdutoRequestDTO requestDTO){

        try{
            Produto entity = repository.getReferenceById(id);
            toEntity(requestDTO, entity);
            entity = repository.save(entity);
            return new ProdutoResponseDTO(entity);
        } catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }
    }

    @Transactional
    public void delete(Long id){

        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. Id: " + id);
        }
        repository.deleteById(id);
    }

    private void toEntity(ProdutoRequestDTO requestDTO, Produto entity) {
        entity.setNome(requestDTO.nome());
        entity.setDescricao(requestDTO.descricao());
        entity.setValor(requestDTO.valor());

        // Objeto completo gerenciado
        Categoria categoria = categoriaRepository.getReferenceById(requestDTO.categoria().getId());
        entity.setCategoria(categoria);
    }

}
