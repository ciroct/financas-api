package br.unisantos.financas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.unisantos.financas.model.PessoaFisica;
import br.unisantos.financas.service.PessoaFisicaService;

@RestController
@RequestMapping("/pessoas_fisicas")
public class PessoaFisicaController implements ControllerInterface<PessoaFisica>{
    @Autowired
    private PessoaFisicaService service;

    @GetMapping
    @Override
    public ResponseEntity<List<PessoaFisica>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        PessoaFisica _obj = service.findById(id);
        if (_obj != null)
            return ResponseEntity.ok(_obj);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Override
    public ResponseEntity<PessoaFisica> post(@RequestBody PessoaFisica obj) {
        service.create(obj);
        return ResponseEntity.ok(obj);
    }

    @PutMapping
    @Override
    public ResponseEntity<?> put(@RequestBody PessoaFisica obj) {
        if (service.update(obj)) {
            return ResponseEntity.ok(obj);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> delete(@PathVariable("id") Long id) {
        if (service.delete(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
