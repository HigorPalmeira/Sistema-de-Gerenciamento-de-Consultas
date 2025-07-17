package com.higorpalmeira.github.gerenciadorconsultas.model.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consultation;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusConsultationType;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, UUID> {
	
	List<Consultation> findAllByStatus(StatusConsultationType status);
	
	List<Consultation> findAllByStatusNot(StatusConsultationType status);

}
