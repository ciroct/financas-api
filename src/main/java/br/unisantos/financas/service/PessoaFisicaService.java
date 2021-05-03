package br.unisantos.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.financas.model.PessoaFisica;
import br.unisantos.financas.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService implements ServiceInterface<PessoaFisica> {

	@Autowired
	private PessoaFisicaRepository repository;
	
    public PessoaFisicaService() {}

    @Override
    public PessoaFisica create(PessoaFisica obj) {
    	return repository.save(obj);
    }
    
    @Override
    public List<PessoaFisica> findAll() {
        return repository.findAll();
    }

    @Override
    public PessoaFisica findById(Long id) {
    	Optional<PessoaFisica> _obj = repository.findById(id);
        return _obj.orElse(null);
    }
    
    @Override
    public boolean update(PessoaFisica obj) {
    	if (repository.existsById(obj.getId())) {
    		repository.save(obj);
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
