package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Endereco;

@Repository
public interface AddressRepository extends JpaRepository<Endereco, UUID> {

}
