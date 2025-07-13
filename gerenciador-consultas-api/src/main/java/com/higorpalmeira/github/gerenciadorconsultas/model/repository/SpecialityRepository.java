package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;

@Repository
public interface SpecialityRepository extends JpaRepository<Speciality, UUID> {

}
