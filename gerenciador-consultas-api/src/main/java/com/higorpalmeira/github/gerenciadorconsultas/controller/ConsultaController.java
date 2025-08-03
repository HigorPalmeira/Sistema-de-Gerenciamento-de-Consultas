package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.ConsultaService;

@RestController
@RequestMapping("/v1/consulta")
public class ConsultaController {
	
	private ConsultaService consultaService;
	
	public ConsultaController(ConsultaService consultaService) {
		this.consultaService = consultaService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> criarConsulta(@RequestBody CriarConsultaDto criarConsultaDto) {
		
		var consultaId = consultaService.criarConsulta(criarConsultaDto);
		
		return ResponseEntity.created(URI.create("/v1/consulta/" + consultaId.toString())).build();
		
	}
	
	@GetMapping("/{consultaId}")
	public ResponseEntity<SaidaSimplesConsultaDto> buscarSaidaSimplesConsultaPorId(@PathVariable("consultaId") String consultaId) {
		
		var consulta = consultaService.buscarSaidaSimplesConsultaPorId(consultaId);
		
		return ResponseEntity.ok(consulta);
		
	}
	
	@GetMapping("/datahora/{dataHoraConsulta}")
	public ResponseEntity<SaidaSimplesConsultaDto> buscarSaidaSimplesConsultaPorDataHora(@PathVariable("dataHoraConsulta") LocalDateTime dataHora) {
		
		var consulta = consultaService.buscarSaidaSimplesConsultaPorDataHora(dataHora);
		
		return ResponseEntity.ok(consulta);
		
	}
	
	@GetMapping("/valor/{valorConsulta}")
	public ResponseEntity<List<SaidaSimplesConsultaDto>> listarTodasSaidaSimplesConsultaPorValor(@PathVariable("valorConsulta") BigDecimal valorConsulta) {
		
		var consultas = consultaService.listarTodasSaidaSimplesConsultaPorValor(valorConsulta);
		
		return ResponseEntity.ok(consultas);
		
	}
	
	@GetMapping("/valor/maior/{valorConsulta}")
	public ResponseEntity<List<SaidaSimplesConsultaDto>> listarTodasSaidaSimplesConsultaPorValorMaior(@PathVariable("valorConsulta") BigDecimal valorConsulta) {
		
		var consultas = consultaService.listarTodasSaidaSimplesConsultaPorValorMaiorQue(valorConsulta);
		
		return ResponseEntity.ok(consultas);
		
	}
	
	@GetMapping("/valor/menor_igual/{valorConsulta}")
	public ResponseEntity<List<SaidaSimplesConsultaDto>> listarTodasSaidaSimplesConsultaPorValorMenorIgual(@PathVariable("valorConsulta") BigDecimal valorConsulta) {
		
		var consultas = consultaService.listarTodasSaidaSimplesConsultaPorValorMenorIgualQue(valorConsulta);
		
		return ResponseEntity.ok(consultas);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SaidaSimplesConsultaDto>> listarTodasSaidaSimplesConsultaAtiva() {
		
		var consultas = consultaService.listarTodasSaidaSimplesConsultaAtiva();
		
		return ResponseEntity.ok(consultas);
		
	}
	
	@GetMapping("/status")
	public ResponseEntity<List<SaidaSimplesConsultaDto>> listarTodasSaidaSimplesConsulta() {
		
		var consultas = consultaService.listarTodasSaidaSimplesConsulta();
		
		return ResponseEntity.ok(consultas);
		
	}
	
	@GetMapping("/status/{statusConsulta}")
	public ResponseEntity<List<SaidaSimplesConsultaDto>> listarTodasSaidaSimplesConsultaPorStatus(@PathVariable("statusConsulta") String statusConsulta) {
		
		var consultas = consultaService.listarTodasSaidaSimplesConsultaPorStatus(statusConsulta);
		
		return ResponseEntity.ok(consultas);
		
	}
	
	@PutMapping("/{consultaId}")
	public ResponseEntity<Void> atualizarConsultaPorId(@PathVariable("consultaId") String consultaId, 
			@RequestBody AtualizarConsultaDto atualizarConsultaDto) {
		
		consultaService.atualizarConsultaPorId(consultaId, atualizarConsultaDto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@DeleteMapping("/{consultaId}")
	public ResponseEntity<Void> deletarConsultaPorId(@PathVariable("consultaId") String consultaId) {
		
		consultaService.deletarConsultaPorId(consultaId);
		
		return ResponseEntity.noContent().build();
		
	}

}
