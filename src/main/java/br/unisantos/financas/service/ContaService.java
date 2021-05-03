package br.unisantos.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unisantos.financas.model.Conta;
import br.unisantos.financas.repository.ContaRepository;

@Service
public class ContaService implements ServiceInterface<Conta> {

	@Autowired
	private ContaRepository repository;
	
    public ContaService() {}

    @Override
    public Conta create(Conta conta) {
    	return repository.save(conta);
    }
    
    @Override
    public List<Conta> findAll() {
        return repository.findAll();
    }

    @Override
    public Conta findById(Long id) {
    	Optional<Conta> _conta = repository.findById(id);
        return _conta.orElse(null);
    }
    
    @Override
    public boolean update(Conta conta) {
    	if (repository.existsById(conta.getId())) {
    		repository.save(conta);
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
