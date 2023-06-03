package com.jainejosiane.contabancaria.service;

import com.jainejosiane.contabancaria.model.Conta;
import com.jainejosiane.contabancaria.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;
@Service
public class ContaService {

    @Autowired
    private ContaRepository contaRepository;

    public List<Conta> getAllContas() {
        List<Conta> contas = contaRepository.findAll();
        return contas;
    }

    public Conta getContaById(Long id) {
        Optional<Conta> contaById = contaRepository.findById(id);

        if (contaById.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return contaById.get();
    }

    public List<Conta> getByAgencias(Integer agencia) {
        List<Conta> agencias = contaRepository.findAllByAgencia(agencia);
        return agencias;
    }

    public Conta postConta(Conta conta) {
        Optional<Conta> contaByNumero = contaRepository.findByNumero(conta.getNumero());
        if (contaByNumero.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        return contaRepository.save(conta);
    }

    public Conta putConta(Conta conta) {
        getContaById(conta.getId());
        return contaRepository.save(conta);
    }

    public void deleteConta(Long id) {
        getContaById(id);
        contaRepository.deleteById(id);
    }
}