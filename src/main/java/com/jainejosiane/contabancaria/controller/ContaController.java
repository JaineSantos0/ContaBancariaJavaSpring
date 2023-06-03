package com.jainejosiane.contabancaria.controller;

import com.jainejosiane.contabancaria.model.Conta;
import com.jainejosiane.contabancaria.service.ContaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/contas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ContaController {

   @Autowired
    private ContaService contaService;

    @GetMapping("/all")
    public ResponseEntity<List<Conta>> getAll() {
        List<Conta> conta = contaService.getAllContas();
        return ResponseEntity.ok(conta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Conta> getById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(contaService.getContaById(id));
    }

    @GetMapping("/agencia/{agencia}")
    public ResponseEntity<List<Conta>> getByAgencia(@PathVariable Integer agencia) {
        List<Conta> conta = contaService.getByAgencias(agencia);
        return ResponseEntity.ok(conta);
    }

    @PostMapping("/post")
    public ResponseEntity<Conta> post(@Valid @RequestBody Conta conta) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contaService.postConta(conta));
    }

    @PutMapping("/put")
    public ResponseEntity<Conta> put(@Valid @RequestBody Conta conta) {
      return ResponseEntity.status(HttpStatus.OK)
              .body(contaService.putConta(conta));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id){
       contaService.deleteConta(id);
      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}