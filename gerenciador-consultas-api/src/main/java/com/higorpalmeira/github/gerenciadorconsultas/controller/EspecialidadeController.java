package com.higorpalmeira.github.gerenciadorconsultas.controller;

import java.net.URI;
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

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaDetalhadaEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarEspecialidadeDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.service.EspecialidadeService;

@RestController
@RequestMapping ("/v1/especialidade")
public class EspecialidadeController {
	
	private EspecialidadeService especialidadeService;
	
	public EspecialidadeController(EspecialidadeService especialidadeService) {
		this.especialidadeService = especialidadeService;
	}
	
	@PostMapping
	public ResponseEntity<UUID> criarEspecialidade(@RequestBody CriarEspecialidadeDto criarEspecialidadeDto) {
		
		var especialidadeId = especialidadeService.criarEspecialidade(criarEspecialidadeDto);
		
		return ResponseEntity.created(URI.create("/v1/especialidade/" + especialidadeId.toString())).build();
		
	}
	
	@GetMapping("/id/{especialidadeId}")
	public ResponseEntity<SaidaSimplesEspecialidadeDto> buscarSaidaSimplesEspecialidadePorId(@PathVariable("especialidadeId") String especialidadeId) {
		
		var especialidade = especialidadeService.buscarSaidaSimplesEspecialidadePorId(especialidadeId);
		
		return ResponseEntity.ok(especialidade);
		
	}
	
	
	@GetMapping("/detalhes/{especialidadeId}")
	public ResponseEntity<SaidaDetalhadaEspecialidadeDto> buscarSaidaDetalhadaEspecialidadePorId(@PathVariable("especialidadeId") String especialidadeId) {
		
		var saida = especialidadeService.buscarSaidaDetalhadaEspecialidadePorId(especialidadeId);
		
		return ResponseEntity.ok(saida);
		
	}
	
	@GetMapping("/descricao/{especialidadeDescricao}")
	public ResponseEntity<SaidaSimplesEspecialidadeDto> buscarSaidaSimplesEspecialidadePorDescricao(@PathVariable("especialidadeDescricao") String especialidadeDescricao) {
		
		var especialidade = especialidadeService.buscarSaidaSimplesEspecialidadePorDescricao(especialidadeDescricao);
		
		return ResponseEntity.ok(especialidade);
		
	}
	
	@GetMapping
	public ResponseEntity<List<SaidaSimplesEspecialidadeDto>> listarTodasSaidaSimplesEspecialidade() {
		
		var especialidades = especialidadeService.listarTodasSaidaSimplesEspecialidade();
		
		return ResponseEntity.ok(especialidades);
		
	}
	
	@GetMapping("/detalhes")
	public ResponseEntity<List<SaidaDetalhadaEspecialidadeDto>> listarTodasSaidaDetalhadaEspecialidade() {
		
		var especialidade = especialidadeService.listarTodasSaidaDetalhadaEspecialidade();
		
		return ResponseEntity.ok(especialidade);
		
	}
	
	@PutMapping("/{especialidadeId}")
	public ResponseEntity<Void> atualizarEspecialidadePorId(@PathVariable("especialidadeId") String especialidadeId, @RequestBody AtualizarEspecialidadeDto atualizarEspecialidadeDto) {
		
		especialidadeService.atualizarEspecialidadePorId(especialidadeId, atualizarEspecialidadeDto);
		
		return ResponseEntity.noContent().build();
		
	}

	@DeleteMapping("/{especialidadeId}")
	public ResponseEntity<Void> deletarEspecialidadePorId(@PathVariable("specialityId") String especialidadeId) {

		especialidadeService.deletarEspecialidadePorId(especialidadeId);
		
		return ResponseEntity.noContent().build();

	}

}
