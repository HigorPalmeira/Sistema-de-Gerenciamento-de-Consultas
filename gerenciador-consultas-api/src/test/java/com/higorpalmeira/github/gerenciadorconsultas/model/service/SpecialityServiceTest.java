package com.higorpalmeira.github.gerenciadorconsultas.model.service;
/*
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.List;
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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.OldUpdateSpecialityDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Speciality;
import com.higorpalmeira.github.gerenciadorconsultas.model.repository.SpecialityRepository;*/

/*@ExtendWith(MockitoExtension.class)*/
public class SpecialityServiceTest {
	
	/*
	 * @Mock private SpecialityRepository specialityRepository;
	 * 
	 * @InjectMocks private SpecialityService specialityService;
	 * 
	 * @Captor private ArgumentCaptor<Speciality> specialityArgumentCaptor;
	 * 
	 * @Captor private ArgumentCaptor<UUID> uuidArgumentCaptor;
	 * 
	 * @Nested class createSpeciality {
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should create a speciality with success") void
	 * shouldCreateSpeciality() {
	 * 
	 * // Arrange var speciality = new Speciality( UUID.randomUUID(), "description",
	 * Instant.now(), null );
	 * 
	 * doReturn(speciality) .when(specialityRepository)
	 * .save(specialityArgumentCaptor.capture());
	 * 
	 * var input = new OldCreateSpecialityDto("description");
	 * 
	 * // Act var output = specialityService.createSpeciality(input);
	 * 
	 * // Assert assertNotNull(output);
	 * 
	 * var specialityCaptured = specialityArgumentCaptor.getValue();
	 * assertEquals(input.description(), specialityCaptured.getDescription());
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should Throw Exception When Error Occurs") void
	 * shouldThrowExceptionWhenErrorOccurs() {
	 * 
	 * // Arrange doThrow(new RuntimeException()) .when(specialityRepository)
	 * .save(any());
	 * 
	 * var input = new OldCreateSpecialityDto("description");
	 * 
	 * // Act & Assert assertThrows(RuntimeException.class, () ->
	 * specialityService.createSpeciality(input));
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @Nested class getSpecialityById {
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should get speciality by id with success when optional is present"
	 * ) void shouldGetSpecialityByIdWithSuccessWhenOptionalIsPresent() {
	 * 
	 * // Arrange var speciality = new Speciality( UUID.randomUUID(), "description",
	 * Instant.now(), null );
	 * 
	 * doReturn(Optional.of(speciality)) .when(specialityRepository)
	 * .findById(uuidArgumentCaptor.capture());
	 * 
	 * // Act var output =
	 * specialityService.findSimpleSpecialityById(speciality.getId().toString());
	 * 
	 * // Assert //assertTrue(output); assertNotNull(output);
	 * assertEquals(speciality.getId(), uuidArgumentCaptor.getValue());
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should get speciality by id with success when optional is empty"
	 * ) void shouldGetSpecialityByIdWithSuccessWhenOptionalIsEmpty() {
	 * 
	 * // Arrange var specialityId = UUID.randomUUID(); doReturn(Optional.empty())
	 * .when(specialityRepository) .findById(uuidArgumentCaptor.capture());
	 * 
	 * // Act var output =
	 * specialityService.findSimpleSpecialityById(specialityId.toString());
	 * 
	 * // Assert //assertTrue(output); assertNotNull(output);
	 * assertEquals(specialityId, uuidArgumentCaptor.getValue());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @Nested class listSpecialities {
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should all specialities with success") void
	 * shouldAllSpecialitiesWithSuccess() {
	 * 
	 * // Arrange var speciality = new Speciality( UUID.randomUUID(), "description",
	 * Instant.now(), null );
	 * 
	 * var specialityList = List.of(speciality);
	 * 
	 * doReturn(specialityList) .when(specialityRepository) .findAll();
	 * 
	 * // Act var output = specialityService.listSpecialities();
	 * 
	 * // Assert assertNotNull(output); assertEquals(specialityList.size(),
	 * output.size());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @Nested class deleteById {
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should delete speciality with success when speciality exists")
	 * void shouldDeleteSpecialityWithSuccessWhenSpecialityExists() {
	 * 
	 * // Arrange doReturn(true) .when(specialityRepository)
	 * .existsById(uuidArgumentCaptor.capture());
	 * 
	 * doNothing() .when(specialityRepository)
	 * .deleteById(uuidArgumentCaptor.capture());
	 * 
	 * var specialityId = UUID.randomUUID();
	 * 
	 * // Act specialityService.deleteSpecialityById(specialityId.toString());
	 * 
	 * // Assert var idList = uuidArgumentCaptor.getAllValues();
	 * assertEquals(specialityId, idList.get(0)); assertEquals(specialityId,
	 * idList.get(1));
	 * 
	 * verify(specialityRepository, times(1)).existsById(idList.get(0));
	 * verify(specialityRepository, times(1)).existsById(idList.get(1));
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should not delete speciality with success when speciality not exists"
	 * ) void shouldNotDeleteSpecialityWithSuccessWhenSpecialityNotExists() {
	 * 
	 * // Arrange doReturn(false) .when(specialityRepository)
	 * .existsById(uuidArgumentCaptor.capture());
	 * 
	 * var specialityId = UUID.randomUUID();
	 * 
	 * // Act specialityService.deleteSpecialityById(specialityId.toString());;
	 * 
	 * // Assert assertEquals(specialityId, uuidArgumentCaptor.getValue());
	 * 
	 * verify(specialityRepository,
	 * times(1)).existsById(uuidArgumentCaptor.getValue());
	 * 
	 * verify(specialityRepository, times(0)).deleteById(any());
	 * 
	 * }
	 * 
	 * }
	 * 
	 * @Nested class updateSpecialityById {
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should update speciality by id when speciality exists and description is filled"
	 * ) void shouldUpdateSpecialityByIdWhenSpecialityExistsAndDescriptionIsFilled()
	 * {
	 * 
	 * // Arrange var updateSpecialityDto = new OldUpdateSpecialityDto(
	 * "description" );
	 * 
	 * var speciality = new Speciality( UUID.randomUUID(), "description",
	 * Instant.now(), null );
	 * 
	 * doReturn(Optional.of(speciality)) .when(specialityRepository)
	 * .findById(uuidArgumentCaptor.capture());
	 * 
	 * doReturn(speciality) .when(specialityRepository)
	 * .save(specialityArgumentCaptor.capture());
	 * 
	 * // Act specialityService.updateSpecialityById(speciality.getId().toString(),
	 * updateSpecialityDto);
	 * 
	 * // Assert assertEquals(speciality.getId(), uuidArgumentCaptor.getValue());
	 * 
	 * var specialityCaptured = specialityArgumentCaptor.getValue();
	 * 
	 * assertEquals(updateSpecialityDto.description(),
	 * specialityCaptured.getDescription());
	 * 
	 * verify(specialityRepository, times(1))
	 * .findById(uuidArgumentCaptor.getValue());
	 * 
	 * verify(specialityRepository, times(1)) .save(speciality);
	 * 
	 * }
	 * 
	 * @Test
	 * 
	 * @DisplayName("Should not update speciality when speciality not exists") void
	 * shouldNotUpdateSpecialityWhenSpecialityNotExists() {
	 * 
	 * // Arrange var updateSpecialityDto = new OldUpdateSpecialityDto(
	 * "description" );
	 * 
	 * var specialityId = UUID.randomUUID();
	 * 
	 * doReturn(Optional.empty()) .when(specialityRepository)
	 * .findById(uuidArgumentCaptor.capture());
	 * 
	 * // Act specialityService.updateSpecialityById(specialityId.toString(),
	 * updateSpecialityDto);
	 * 
	 * // Assert assertEquals(specialityId, uuidArgumentCaptor.getValue());
	 * 
	 * verify(specialityRepository, times(1))
	 * .findById(uuidArgumentCaptor.getValue());
	 * 
	 * verify(specialityRepository, times(0)) .save(any());
	 * 
	 * }
	 * 
	 * }
	 */
	
}
