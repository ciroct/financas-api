package br.unisantos.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.financas.model.Categoria;
import br.unisantos.financas.repository.CategoriaRepository;

@Service
public class CategoriaService implements ServiceInterface<Categoria> {

	@Autowired
	private CategoriaRepository repository;
	
    public CategoriaService() {}

    @Override
    public Categoria create(Categoria categoria) {
    	return repository.save(categoria);
    }
    
    @Override
    public List<Categoria> findAll() {
        return repository.findAll();
    }

    @Override
    public Categoria findById(Long id) {
    	Optional<Categoria> _categoria = repository.findById(id);
        return _categoria.orElse(null);
    }
    
    @Override
    public boolean update(Categoria Categoria) {
    	if (repository.existsById(Categoria.getId())) {
    		repository.save(Categoria);
    		return true;
    	}
        return false;
    }

    @Override
    public boolean delete(Long id) {
    	if (repository.existsById(id)) {
    		repository.deleteById(id);
    		return true;
    	}
    	return false;
    }
}
