package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

import java.time.Instant;
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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CreateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.SpecialityRepository;

@ExtendWith(MockitoExtension.class)
public class SpecialityServiceTest {
	
	@Mock
	private SpecialityRepository specialityRepository;
	
	@InjectMocks
	private SpecialityService specialityService;
	
	@Captor
	private ArgumentCaptor<Speciality> specialityArgumentCaptor;
	
	@Captor
	private ArgumentCaptor<UUID> uuidArgumentCaptor;
	
	@Nested
	class createSpeciality {
		
		@Test
		@DisplayName("Should create a speciality with success")
		void shouldCreateSpeciality() {
			
			// Arrange
			var speciality = new Speciality(
					UUID.randomUUID(),
					"description",
					Instant.now(),
					null
					);
			
			doReturn(speciality)
					.when(specialityRepository)
					.save(specialityArgumentCaptor.capture());
			
			var input = new CreateSpecialityDto("description");
			
			// Act
			var output = specialityService.createSpeciality(input);
			
			// Assert
			assertNotNull(output);
			
			var specialityCaptured = specialityArgumentCaptor.getValue();
			assertEquals(input.description(), specialityCaptured.getDescription());
			
		}
		
	}

}
