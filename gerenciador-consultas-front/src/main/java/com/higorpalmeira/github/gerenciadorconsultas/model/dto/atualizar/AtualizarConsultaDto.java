/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.dto.atualizar;

import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.TipoStatusConsulta;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author higor
 */
public class AtualizarConsultaDto {

    private LocalDateTime dataHora;

    private TipoStatusConsulta status;

    private String observacoes;

    private BigDecimal valor;

    private UUID medicoId;

    private UUID pacienteId;

    public AtualizarConsultaDto() {
    }

    public AtualizarConsultaDto(LocalDateTime dataHora, TipoStatusConsulta status, String observacoes, BigDecimal valor, UUID medicoId, UUID pacienteId) {
        this.dataHora = dataHora;
        this.status = status;
        this.observacoes = observacoes;
        this.valor = valor;
        this.medicoId = medicoId;
        this.pacienteId = pacienteId;
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

    public UUID getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(UUID medicoId) {
        this.medicoId = medicoId;
    }

    public UUID getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(UUID pacienteId) {
        this.pacienteId = pacienteId;
    }
    
}
