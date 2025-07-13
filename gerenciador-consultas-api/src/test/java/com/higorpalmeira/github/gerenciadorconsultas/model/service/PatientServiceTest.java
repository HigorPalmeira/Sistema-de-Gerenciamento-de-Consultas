package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import java.util.UUID;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientServiceTest {
	
	@Mock
	private PatientRepository patientRepository;
	
	@InjectMocks
	private PatientService patientService;
	
	@Captor
	private ArgumentCaptor<Patient> patientArgumentCaptor;
	
	@Captor
	private ArgumentCaptor<UUID> uuidArgumentCaptor;

}
