package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreatePatientDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Patient;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Gender.GenderType;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Status.StatusType;
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
	
	@Nested
	class createPatient {
		
		@Test
		@DisplayName("Should create a patient with success")
		void shouldCreatePatient() {
			
			// Arrange
			var patient = new Patient(
					UUID.randomUUID(),
					"Benedito",
					"Danilo Nogueira",
					"689.254.310-33",
					LocalDate.parse("1988-06-23", DateTimeFormatter.ISO_LOCAL_DATE),
					GenderType.MALE,
					StatusType.ACTIVE,
					"(96) 99980-7549",
					"benedito-nogueira77@dpi.ig.br",
					Instant.now(),
					null
					);
			
			doReturn(patient)
				.when(patientRepository)
				.save(patientArgumentCaptor.capture());
			
			var input = new CreatePatientDto(
					"Benedito",
					"Danilo Nogueira",
					"689.254.310-33",
					"1988-06-23",
					"MALE",
					"(96) 99980-7549",
					"benedito-nogueira77@dpi.ig.br"
					);
			
			// Act
			var output = patientService.createPatient(input);
			
			// Assert
			assertNotNull(output);
			
			var patientCaptured = patientArgumentCaptor.getValue();
			
			assertEquals(input.firstName(), patientCaptured.getFirstName());
			assertEquals(input.lastName(), patientCaptured.getLastName());
			assertEquals(input.cpf(), patientCaptured.getCpf());
			assertEquals(LocalDate.parse(input.birthdate(), DateTimeFormatter.ISO_LOCAL_DATE), patientCaptured.getBirthdate());
			assertEquals(GenderType.fromType(input.gender()), patientCaptured.getGender());
			assertEquals(input.telephone(), patientCaptured.getTelephone());
			assertEquals(input.email(), patientCaptured.getEmail());
			
		}
		
	}

}
