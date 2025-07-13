package com.higorpalmeira.github.gerenciadorconsultas.model.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

import java.time.Instant;
import java.util.Optional;
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
		
		@Test
		@DisplayName("Should Throw Exception When Error Occurs")
		void shouldThrowExceptionWhenErrorOccurs() {
			
			// Arrange
			doThrow(new RuntimeException())
				.when(specialityRepository)
				.save(any());
			
			var input = new CreateSpecialityDto("description");
			
			// Act & Assert
			assertThrows(RuntimeException.class, () -> specialityService.createSpeciality(input));
			
		}
		
	}

	@Nested
	class getSpecialityById {
		
		@Test
		@DisplayName("Should get speciality by id with success when optional is present")
		void shouldGetSpecialityByIdWithSuccessWhenOptionalIsPresent() {
			
			// Arrange
			var speciality = new Speciality(
					UUID.randomUUID(),
					"description",
					Instant.now(),
					null
					);
			
			doReturn(Optional.of(speciality))
				.when(specialityRepository)
				.findById(uuidArgumentCaptor.capture());
			
			// Act
			var output = specialityService.findSpecialityById(speciality.getId().toString());
			
			// Assert
			assertTrue(output.isPresent());
			assertEquals(speciality.getId(), uuidArgumentCaptor.getValue());
			
		}
		
		@Test
		@DisplayName("Should get speciality by id with success when optional is empty")
		void shouldGetSpecialityByIdWithSuccessWhenOptionalIsEmpty() {
			
			// Arrange
			var specialityId = UUID.randomUUID();
			doReturn(Optional.empty())
				.when(specialityRepository)
				.findById(uuidArgumentCaptor.capture());
			
			// Act
			var output = specialityService.findSpecialityById(specialityId.toString());
			
			// Assert
			assertTrue(output.isEmpty());
			assertEquals(specialityId, uuidArgumentCaptor.getValue());
			
		}
		
	}
	
}
