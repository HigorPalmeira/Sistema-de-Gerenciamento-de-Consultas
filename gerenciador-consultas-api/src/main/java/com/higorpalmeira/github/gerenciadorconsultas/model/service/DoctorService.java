package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import org.springframework.stereotype.Service;

import com.higorpalmeira.github.gerenciadorconsultas.model.mappers.DoctorMapper;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.DoctorRepository;

@Service
public class DoctorService {
	
	private DoctorRepository doctorRepository;
	
	private DoctorMapper doctorMapper;
	
	public DoctorService(DoctorRepository doctorRepository, DoctorMapper doctorMapper) {
		this.doctorRepository = doctorRepository;
		this.doctorMapper = doctorMapper;
	}

}
