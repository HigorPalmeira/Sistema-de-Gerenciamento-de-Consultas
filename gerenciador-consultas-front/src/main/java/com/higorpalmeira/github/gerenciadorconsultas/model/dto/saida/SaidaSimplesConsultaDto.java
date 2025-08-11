/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.dto.saida;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.TipoStatusConsulta;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author higor
 */
public class SaidaSimplesConsultaDto {
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHora;
    
    private TipoStatusConsulta status;
    
    private String observacoes;
    
    private BigDecimal valor;
    
    private SaidaSimplesMedicoDto medico;
    
    private SaidaSimplesPacienteDto paciente;

    public SaidaSimplesConsultaDto() {
    }

    public SaidaSimplesConsultaDto(LocalDateTime dataHora, TipoStatusConsulta status, String observacoes, BigDecimal valor, SaidaSimplesMedicoDto medico, SaidaSimplesPacienteDto paciente) {
        this.dataHora = dataHora;
        this.status = status;
        this.observacoes = observacoes;
        this.valor = valor;
        this.medico = medico;
        this.paciente = paciente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public TipoStatusConsulta getStatus() {
        return status;
    }

    public void setStatus(TipoStatusConsulta status) {
        this.status = status;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public SaidaSimplesMedicoDto getMedico() {
        return medico;
    }

    public void setMedico(SaidaSimplesMedicoDto medico) {
        this.medico = medico;
    }

    public SaidaSimplesPacienteDto getPaciente() {
        return paciente;
    }

    public void setPaciente(SaidaSimplesPacienteDto paciente) {
        this.paciente = paciente;
    }
    
}
