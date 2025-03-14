package br.com.fiap.ms_produto.service;

import br.com.fiap.ms_produto.dto.CategoriaDTO;
import br.com.fiap.ms_produto.entities.Categoria;
import br.com.fiap.ms_produto.repositories.CategoriaRepository;
import br.com.fiap.ms_produto.service.exceptions.DatabaseException;
import br.com.fiap.ms_produto.service.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    @Transactional(readOnly = true)
    public List<CategoriaDTO> findAll() {
        return repository.findAll()
                .stream().map(CategoriaDTO::new).toList();
    }

    @Transactional(readOnly = true)
    public CategoriaDTO findById(Long id) {
        Categoria entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );
        return new CategoriaDTO(entity);
    }

    @Transactional
    public CategoriaDTO create(CategoriaDTO dto) {
        Categoria entity = new Categoria();
        copyDtoToEntity(dto, entity);
        entity = repository.save(entity);
        return new CategoriaDTO(entity);
    }

    @Transactional
    public CategoriaDTO update(Long id, CategoriaDTO dto){
        try{
            Categoria entity = repository.getReferenceById(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new CategoriaDTO(entity);
        } catch (EntityNotFoundException ex){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!repository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Não é possível deletar a categoria. " +
                    "Ela está sendo referenciado por um ou mais produto.");
        }
    }
    private void copyDtoToEntity(CategoriaDTO dto, Categoria entity) {
        entity.setNome(dto.getNome());
    }
}
