package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.PacienteService;

@RestController
@RequestMapping("/v1/paciente")
public class PacienteController {
	
	private PacienteService pacienteService;
	
	public PacienteController(PacienteService pacienteService) {
		this.pacienteService = pacienteService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> criarPaciente(@RequestBody CriarPacienteDto criarPacienteDto) {
		
		var pacienteId = pacienteService.criarPaciente(criarPacienteDto);
		
		return ResponseEntity.created(URI.create("/v1/paciente/" + pacienteId.toString())).build();
		
	}
	
	@GetMapping ("/{pacienteId}")
	public ResponseEntity<SaidaSimplesPacienteDto> buscarSaidaSimplesPacienteDto(@PathVariable("pacienteId") String pacienteId) {
		
		var paciente = pacienteService.buscarSaidaSimplesPacientePorId(pacienteId);
		
		return ResponseEntity.ok(paciente);
		
	}
	
	@GetMapping("/email/{emailPaciente}")
	public ResponseEntity<SaidaSimplesPacienteDto> buscarSaidaSimplesPacienteDtoPorEmail(@PathVariable("emailPaciente") String emailPaciente) {
		
		var paciente = pacienteService.buscarSaidaSimplesPacientePorEmail(emailPaciente);
		
		return ResponseEntity.ok(paciente);
		
	}
	
	@GetMapping("/cpf/{cpfPaciente}")
	public ResponseEntity<SaidaSimplesPacienteDto> buscarSaidaSimplesPacienteDtoPorCpf(@PathVariable("cpfPaciente") String cpfPaciente) {
		
		var paciente = pacienteService.buscarSaidaSimplesPacientePorCpf(cpfPaciente);
		
		return ResponseEntity.ok(paciente);
		
	}
	
	@GetMapping("/telefone/{telefonePaciente}")
	public ResponseEntity<List<SaidaSimplesPacienteDto>> listarTodosSaidaSimplesPacientePorTelefone(@PathVariable("telefonePaciente") String telefonePaciente) {
		
		return null;
		
	}
	
	@GetMapping("/nome/{nomePaciente}")
	public ResponseEntity<List<SaidaSimplesPacienteDto>> listarTodosSaidaSimplesPacientePorNome(@PathVariable("nomePaciente") String nomePaciente) {
		
		var paciente = pacienteService.listarTodosSaidaSimplesPacientePorNome(nomePaciente);
		
		return ResponseEntity.ok(paciente);
		
	}
	
	@GetMapping("/sobrenome/{sobrenomePaciente}")
	public ResponseEntity<List<SaidaSimplesPacienteDto>> listarTodosSaidaSimplesPacientePorSobrenome(@PathVariable("sobrenomePaciente") String sobrenomePaciente) {
		
		var paciente = pacienteService.listarTodosSaidaSimplesPacientePorSobrenome(sobrenomePaciente);
		
		return ResponseEntity.ok(paciente);
		
	}
	
	@GetMapping("/status/{statusPaciente}")
	public ResponseEntity<List<SaidaSimplesPacienteDto>> listarTodosSaidaSimplesPacientePorStatus(@PathVariable("statusPaciente") String statusPaciente) {
		
		var pacientes = pacienteService.listarTodosSaidaSimplesPacientePorStatus(statusPaciente);
		
		return ResponseEntity.ok(pacientes);
		
	}
	
	@GetMapping("/genero/{generoPaciente}")
	public ResponseEntity<List<SaidaSimplesPacienteDto>> listarTodosSaidaSimplesPacientePorGenero(@PathVariable("generoPaciente") String generoPaciente) {
		
		var paciente = pacienteService.listarTodosSaidaSimplesPacienteGenero(generoPaciente);
		
		return ResponseEntity.ok(paciente);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SaidaSimplesPacienteDto>> listarTodosSaidaSimplesPaciente() {
		
		var pacientes = pacienteService.listarTodosSaidaSimplesPaciente();
		
		return ResponseEntity.ok(pacientes);
		
	}
	
	@GetMapping("/datanascimento/{dataNascimento}")
	public ResponseEntity<List<SaidaSimplesPacienteDto>> listarTodosSaidaSimplesPacientePorDataNascimento(@PathVariable("dataNascimento") 
		String dataNascimento) {
		
		var pacientes = pacienteService
				.listarTodosSaidaSimplesPacientePorDataNascimento(
						LocalDate.parse(dataNascimento, 
								DateTimeFormatter.ISO_LOCAL_DATE));
		
		return ResponseEntity.ok(pacientes);
		
	}
	
	@GetMapping("/detalhes")
	public ResponseEntity<List<SaidaDetalhadaPacienteDto>> listarTodosSaidaDetalhadaPaciente() {
		
		var pacientes = pacienteService.listarTodosSaidaDetalhadaPaciente();
		
		return ResponseEntity.ok(pacientes);
		
	}
	
	@GetMapping("/detalhes/status/ativo")
	public ResponseEntity<List<SaidaDetalhadaPacienteDto>> listarTodosSaidaDetalhadaPacienteAtivos() {
		
		var pacientes = pacienteService.listarTodosSaidaDetalhadaPacienteAtivos();
		
		return ResponseEntity.ok(pacientes);
		
	}
	
	@GetMapping("/detalhes/status/inativo")
	public ResponseEntity<List<SaidaDetalhadaPacienteDto>> listarTodosSaidaDetalhadaPacienteInativo() {
	
		var pacientes = pacienteService.listarTodosSaidaDetalhadaPacienteInativos();
		
		return ResponseEntity.ok(pacientes);
		
	}
	
	@GetMapping("/detalhes/datanascimento/{dataNascimento}")
	public ResponseEntity<List<SaidaDetalhadaPacienteDto>> listarTodosSaidaDetalhadaPacientePorDataNascimento(@PathVariable("dataNascimento") 
		String dataNascimento) {
		
		var pacientes = pacienteService
				.listarTodosSaidaDetalhadaPacientePorDataNascimento(
						LocalDate.parse(dataNascimento, 
								DateTimeFormatter.ISO_LOCAL_DATE));

		return ResponseEntity.ok(pacientes);
		
	}
	
	@PutMapping("/{pacienteId}")
	public ResponseEntity<Void> atualizarPacientePorId(@PathVariable("pacienteId") String pacienteId, 
			@RequestBody AtualizarPacienteDto atualizarPacienteDto) {
		
		pacienteService.atualizarPacientePorId(pacienteId, atualizarPacienteDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{pacienteId}")
	public ResponseEntity<Void> deleteById(@PathVariable("pacienteId") String pacienteId) {
		
		pacienteService.deletarPacientePorId(pacienteId);
		
		return ResponseEntity.noContent().build();
		
	}

}
