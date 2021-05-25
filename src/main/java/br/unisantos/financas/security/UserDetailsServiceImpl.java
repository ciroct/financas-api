package br.unisantos.financas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.unisantos.financas.model.Cliente;
import br.unisantos.financas.repository.ClienteRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente cliente = repository.findByLogin(username);
		if (cliente == null) {
			throw new UsernameNotFoundException(username);
		}		
		return new UserDetailsImpl(cliente.getId(), 
				cliente.getLogin(), cliente.getSenha(), cliente.getPerfis());
	}

}
