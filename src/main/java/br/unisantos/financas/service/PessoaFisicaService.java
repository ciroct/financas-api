package br.unisantos.financas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.unisantos.financas.exception.AuthorizationException;
import br.unisantos.financas.model.PessoaFisica;
import br.unisantos.financas.repository.PessoaFisicaRepository;
import br.unisantos.financas.security.JWTUtil;

@Service
public class PessoaFisicaService implements ServiceInterface<PessoaFisica> {

	@Autowired
	private PessoaFisicaRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JWTUtil jwtUtil;
	
    public PessoaFisicaService() {}

    @Override
    public PessoaFisica create(PessoaFisica obj) {
    	obj.setSenha(passwordEncoder.encode(obj.getSenha()));
    	return repository.save(obj);
    }
    
    @Override
    public List<PessoaFisica> findAll() {
        return repository.findAll();
    }

    @Override
    public PessoaFisica findById(Long id) throws AuthorizationException {
    	if (!jwtUtil.authorized(id)) {
    		throw new AuthorizationException("Acesso negado!");
    	}
    	Optional<PessoaFisica> _obj = repository.findById(id);
        return _obj.orElse(null);
    }
    
    @Override
    public boolean update(PessoaFisica obj) {
    	if (repository.existsById(obj.getId())) {
    		obj.setSenha(passwordEncoder.encode(obj.getSenha()));
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
