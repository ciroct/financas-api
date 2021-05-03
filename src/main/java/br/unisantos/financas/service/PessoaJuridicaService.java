package br.unisantos.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.financas.model.PessoaJuridica;
import br.unisantos.financas.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService implements ServiceInterface<PessoaJuridica> {

	@Autowired
	private PessoaJuridicaRepository repository;
	
    public PessoaJuridicaService() {}

    @Override
    public PessoaJuridica create(PessoaJuridica obj) {
    	return repository.save(obj);
    }
    
    @Override
    public List<PessoaJuridica> findAll() {
        return repository.findAll();
    }

    @Override
    public PessoaJuridica findById(Long id) {
    	Optional<PessoaJuridica> _obj = repository.findById(id);
        return _obj.orElse(null);
    }
    
    @Override
    public boolean update(PessoaJuridica obj) {
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
