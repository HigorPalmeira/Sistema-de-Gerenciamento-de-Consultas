package com.higorpalmeira.github.gerenciadorconsultas.model.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.higorpalmeira.github.gerenciadorconsultas.model.dto.create.CriarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.output.SaidaSimplesConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.update.AtualizarConsultaDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Consulta;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Medico;
import com.higorpalmeira.github.gerenciadorconsultas.model.entity.Paciente;

@Mapper(componentModel = "spring", uses = {MedicoMapper.class, PacienteMapper.class})
public interface ConsultaMapper {
	
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "status", expression = "java(TipoStatusConsulta.AGENDADA)")
	@Mapping(target = "medico", expression = "java(medico != null ? medico : null)")
	@Mapping(target = "paciente", expression = "java(paciente != null ? paciente : null)")
	Consulta criarConsultaDtoParaConsulta(CriarConsultaDto criarConsultaDto, Medico medico, Paciente paciente);
	
	SaidaSimplesConsultaDto consultaParaSaidaSimplesConsultaDto(Consulta consulta);

	@BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "creationTimestamp", ignore = true)
	@Mapping(target = "updateTimestamp", ignore = true)
	@Mapping(target = "medico", expression = "java(medico != null ? medico : null)")
	@Mapping(target = "paciente", expression = "java(paciente != null ? paciente : null)")
	@Mapping(target = "status", expression = "java(consulta.setStatus(atualizarConsultaDto.getStatus()))")
	void atualizarConsultaDeAtualizarConsultaDto(AtualizarConsultaDto atualizarConsultaDto, Medico medico, Paciente paciente, @MappingTarget Consulta consulta);
	
}
