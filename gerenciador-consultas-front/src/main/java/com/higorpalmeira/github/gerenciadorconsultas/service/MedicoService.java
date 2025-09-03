/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.higorpalmeira.github.gerenciadorconsultas.client.MedicoClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.atualizar.AtualizarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.criar.CriarMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.saida.SaidaDetalhadaMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.saida.SaidaSimplesMedicoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.StatusOperacao;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.TipoStatusConta;
import com.higorpalmeira.github.gerenciadorconsultas.util.Formatador;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validador;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author higor
 */
public class MedicoService {
    
    private final String LOCATION = "Location";
    
    private final MedicoClient client;
    
    private final ObjectMapper mapper;
    
    public MedicoService(MedicoClient medicoClient) {
        
        client = medicoClient;
        mapper = new ObjectMapper();
        
    }
    
    private void erroRequisicao(int statusCode) {
        
        JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição do Médico! Status da requisição: " + statusCode + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
        
    }
    
    private void erroException(String erro, String mensagem) {
        
        JOptionPane.showMessageDialog(null, "Erro ao tentar " + erro + " o médico!\nErro: " + mensagem, "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
        
    }
    
    public boolean criarMedico(String nome, String sobrenome, String crm, String telefone, String email, UUID especialidadeId) {
        
        if ( (nome == null || nome.isBlank()) || (sobrenome == null || sobrenome.isBlank()) ) {
            return false;
        }
        
        if ( !Validador.isCrm(crm) ) {
            return false;
        }
        
        if ( !Validador.isEmail(email) ) {
            return false;
        }
        
        if ( !Validador.isTelefone(telefone) ) {
            return false;
        }
        
        if (especialidadeId == null) {
            return false;
        }
        
        CriarMedicoDto criarMedicoDto = new CriarMedicoDto(nome, sobrenome, crm, telefone, email, especialidadeId);
        
        try {
            
            HttpResponse response = client.criarMedico(criarMedicoDto);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_CRIACAO.getTipo()) {
                
                Optional<String> locationHeader = response.headers().firstValue(LOCATION);
                
                if (locationHeader.isPresent()) {
                    
                    String locationUrl = locationHeader.get();
                    
                    URI uri = new URI(locationUrl);
                    String path = uri.getPath();
                    
                    String idPath = path.substring(path.lastIndexOf("/") + 1);
                    
                    try {
                        UUID.fromString(idPath);
                    }catch(Exception e) {
                        return false;
                    }
                    
                }
                
                return true;
                
            } else {
                
                return false;
                
            }
            
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar criar um novo Médico!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
        
    }
    
    public boolean editarMedico(UUID idMedico, String nome, String sobrenome, String crm, String telefone, String email, String status, UUID especialidadeId) {
        
        if (idMedico == null) {
            return false;
        }
        
        AtualizarMedicoDto atualizarMedicoDto = new AtualizarMedicoDto(nome, sobrenome, crm, telefone, email, TipoStatusConta.fromTipo(status), especialidadeId);
        
        try {
            
            HttpResponse<String> response = client.editarMedico(idMedico, atualizarMedicoDto);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_DELECAO_EDICAO.getTipo()) {
                return true;
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar o médico.\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
        
    }
    
    public boolean deletarMedico(UUID idMedico) {
        
        if (idMedico == null) {
            return false;
        }
        
        try {
            
            HttpResponse<String> response = client.deletarMedico(idMedico);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_DELECAO_EDICAO.getTipo()) {
                
                return true;
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar deletar o médico.\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
        
    }
    
    public SaidaSimplesMedicoDto buscarSaidaSimplesMedicoDtoPorId(UUID idMedico) {
        
        if (idMedico == null) {
            return null;
        }
        
        SaidaSimplesMedicoDto medicoDto = new SaidaSimplesMedicoDto();
        
        try {
            
            HttpResponse<String> response = client.buscarSaidaSimplesMedicoDtoPorId(idMedico);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                medicoDto = mapper.readValue(response.body(), SaidaSimplesMedicoDto.class);
                
            } else {
            
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição do Médico! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar o médico!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return medicoDto;
        
    }
    
    public SaidaSimplesMedicoDto buscarSaidaSimplesMedicoDtoPorEmail(String email) {
        
        if (!Validador.isEmail(email)) {
            return null;
        }
        
        SaidaSimplesMedicoDto medicoDto = new SaidaSimplesMedicoDto();
        
        try {
            
            HttpResponse<String> response = client.buscarSaidaSimplesMedicoDtoPorEmail(email);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                medicoDto = mapper.readValue(response.body(), SaidaSimplesMedicoDto.class);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição do Médico! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar o médico!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return medicoDto;
        
    }
    
    public SaidaSimplesMedicoDto buscarSaidaSimplesMedicoDtoPorCrm(String crm) {
        
        if (!Validador.isCrm(crm)) {
            return null;
        }
        
        SaidaSimplesMedicoDto medicoDto = new SaidaSimplesMedicoDto();
        
        try {
            
            HttpResponse<String> response = client.buscarSaidaSimplesMedicoDtoPorCrm(Formatador.limparFormatacao(crm));
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                medicoDto = mapper.readValue(response.body(), SaidaSimplesMedicoDto.class);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição do Médico! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar o médico!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return medicoDto;
        
    }
    
    public SaidaSimplesMedicoDto buscarSaidaSimplesMedicoDtoPorTelefone(String telefone) {
        
        if (!Validador.isTelefone(telefone)) {
            return null;
        }
        
        SaidaSimplesMedicoDto medicoDto = new SaidaSimplesMedicoDto();
        
        try {
            
            HttpResponse<String> response = client.buscarSaidaSimplesMedicoDtoPorTelefone(Formatador.limparFormatacao(telefone));
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                medicoDto = mapper.readValue(response.body(), SaidaSimplesMedicoDto.class);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição do Médico! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar o médico!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return medicoDto;
        
    }
    
    public List<SaidaSimplesMedicoDto> listarSaidasSimplesMedicoDto() {
        
        List<SaidaSimplesMedicoDto> listaMedicos = new ArrayList<>();
        
        try {
            
            HttpResponse<String> response = client.listarSaidaSimplesMedicoDto();
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                listaMedicos = mapper.readValue(response.body(), new TypeReference<List<SaidaSimplesMedicoDto>>() { });
                
            } else {
            
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição dos Médicos! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar listar todos os médicos!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return listaMedicos;
        
    }
    
    public List<SaidaSimplesMedicoDto> listarSaidasSimplesMedicoDtoPorNome(String nome) {
        
        List<SaidaSimplesMedicoDto> listaMedicos = new ArrayList<>();
        
        if (nome == null || nome.isBlank()) {
            return listaMedicos;
        }
        
        try {
            
            HttpResponse<String> response = client.listarSaidaSimplesMedicoDtoPorNome(nome);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                listaMedicos = mapper.readValue(response.body(), new TypeReference<List<SaidaSimplesMedicoDto>>() { });
                
            } else {
            
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição dos Médicos! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar listar todos os médicos!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return listaMedicos;
        
    }
    
    public SaidaDetalhadaMedicoDto buscarSaidaDetalhadaMedicoDtoPorId(UUID idMedico) {
        
        if (idMedico == null) {
            return null;
        }
        
        SaidaDetalhadaMedicoDto medicoDto = new SaidaDetalhadaMedicoDto();
        
        try {
            
            HttpResponse<String> response = client.buscarMedicoDetalhadoPorId(idMedico);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                medicoDto = mapper.readValue(response.body(), SaidaDetalhadaMedicoDto.class);
                
            } else {
                
                this.erroRequisicao(response.statusCode());
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            this.erroException("buscar", ex.toString());
            
        }
        
        return medicoDto;
        
    }
    
}
