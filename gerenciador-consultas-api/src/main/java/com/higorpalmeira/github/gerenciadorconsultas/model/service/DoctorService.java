package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import org.springframework.stereotype.Service;

import com.higorpalmeira.github.gerenciadorconsultas.model.repository.DoctorRepository;

@Service
public class DoctorService {
	
	private DoctorRepository doctorRepository;
	
	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

}
