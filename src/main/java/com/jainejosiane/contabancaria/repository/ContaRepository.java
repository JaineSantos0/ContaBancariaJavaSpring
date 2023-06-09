package com.jainejosiane.contabancaria.repository;

import com.jainejosiane.contabancaria.model.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
    public List<Conta> findAllByAgencia(@Param("agencia") Integer agencia);

    public Optional<Conta> findByNumero(@Param ("numero") Integer numero);
}