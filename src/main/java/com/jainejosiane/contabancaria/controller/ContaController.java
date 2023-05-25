package com.jainejosiane.contabancaria.controller;

import com.jainejosiane.contabancaria.model.Conta;
import com.jainejosiane.contabancaria.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

    @Autowired
    private ContaRepository contaRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Conta>> getAll() {
        return ResponseEntity.ok(contaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Long id) {
        return contaRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/agencia/{agencia}")
    public ResponseEntity<List<Conta>> getByAgencia(@PathVariable Integer agencia) {
        return ResponseEntity.ok(contaRepository.findAllByAgencia(agencia));
    }

    @PostMapping("/post")
    public ResponseEntity<Conta> post(@Valid @RequestBody Conta conta) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contaRepository.save(conta));
    }

    @PutMapping("/put")
    public ResponseEntity<Conta> put(@Valid @RequestBody Conta conta) {
        return contaRepository.findById(conta.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(contaRepository.save(conta)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        Optional<Conta> optional = contaRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        contaRepository.deleteById(id);
    }
}