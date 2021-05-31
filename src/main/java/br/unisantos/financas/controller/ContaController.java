package br.unisantos.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisantos.financas.model.Conta;
import br.unisantos.financas.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController implements ControllerInterface<Conta> {
	@Autowired
	private ContaService service;

	@GetMapping
	@Override
	public ResponseEntity<List<Conta>> getAll() {
		return ResponseEntity.ok(service.findAll());
	}

	@GetMapping(value = "/{id}")
	@Override
	public ResponseEntity<?> get(@PathVariable("id") Long id) {
		Conta _conta = service.findById(id);
		if (_conta != null)
			return ResponseEntity.ok(_conta);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PostMapping
	@Override
	public ResponseEntity<Conta> post(@RequestBody Conta conta) {
		service.create(conta);
		return ResponseEntity.ok(conta);
	}

	@PutMapping
	@Override	
	public ResponseEntity<?> put(@RequestBody Conta conta) {
		if (service.update(conta)) {
			return ResponseEntity.ok(conta);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping(value = "/{id}")
	@Override
	@PreAuthorize("hasAnyRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		if (service.delete(id)) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@GetMapping(value = "/agencia/{agencia}")
	public ResponseEntity<List<Conta>> getByAgencia(@PathVariable("agencia") Integer agencia) {
		return ResponseEntity.ok(service.listarPorAgencia(agencia));
	}

	@GetMapping(value = "/agencia/{agencia}/{from}/{to}")
	public ResponseEntity<List<Conta>> getByAgenciaESaldo(@PathVariable("agencia") Integer agencia,
			@PathVariable("from") Float from, @PathVariable("to") Float to) {
		return ResponseEntity.ok(service.listarPorAgenciaESaldo(agencia, from, to));
	}

	@GetMapping(value = "/cliente/{nome}")
	public ResponseEntity<List<Conta>> getByNomeCliente(@PathVariable("nome") String nome) {
		return ResponseEntity.ok(service.listarPorNomeCliente(nome));
	}

}
