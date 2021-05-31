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

import br.unisantos.financas.model.Movimentacao;
import br.unisantos.financas.service.MovimentacaoService;

@RestController
@RequestMapping("/movimentacoes")
public class MovimentacaoController implements ControllerInterface<Movimentacao>{
    @Autowired
    private MovimentacaoService service;

    @GetMapping
    @Override
    public ResponseEntity<List<Movimentacao>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(value = "/{id}")
    @Override
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        Movimentacao _obj = service.findById(id);
        if (_obj != null)
            return ResponseEntity.ok(_obj);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping
    @Override
    public ResponseEntity<Movimentacao> post(@RequestBody Movimentacao obj) {
        service.create(obj);
        return ResponseEntity.ok(obj);
    }

    @PutMapping
    @Override
    public ResponseEntity<?> put(@RequestBody Movimentacao obj) {
        if (service.update(obj)) {
            return ResponseEntity.ok(obj);
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

}
