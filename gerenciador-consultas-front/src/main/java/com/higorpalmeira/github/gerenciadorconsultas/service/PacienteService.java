/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.higorpalmeira.github.gerenciadorconsultas.client.PacienteClient;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.AtualizarEnderecoDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.AtualizarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.CriarPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.dto.SaidaSimplesPacienteDto;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.Genero.TipoGenero;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.TipoStatusConta;
import com.higorpalmeira.github.gerenciadorconsultas.model.enums.TipoStatus.StatusOperacao;
import com.higorpalmeira.github.gerenciadorconsultas.util.Validador;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class PacienteService {
    
    private final String LOCATION = "Location";
    
    private final PacienteClient client;
    
    private final ObjectMapper mapper;
    
    public PacienteService(PacienteClient pacienteClient) {
        this.client = pacienteClient;
        this.mapper = new ObjectMapper();
        this.mapper.registerModule(new JavaTimeModule());
    }
    
    public boolean criarPaciente(CriarPacienteDto criarPacienteDto) {
        
        if (criarPacienteDto == null) {
            return false;
        }
        
        try {
            
            HttpResponse<String> response = client.criarPaciente(criarPacienteDto);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_CRIACAO.getTipo()) {
                
                Optional<String> locationHeader = response.headers().firstValue(LOCATION);
                
                if (locationHeader.isPresent()) {
                    
                    String locationUrl = locationHeader.get();
                    
                    URI uri = new URI(locationUrl);
                    String path = uri.getPath();
                    
                    String idPath = path.substring(path.lastIndexOf("/") + 1);
                    
                    try {
                        UUID.fromString(idPath);
                    } catch(Exception e) {
                        return false;
                    }
                    
                }
                
                return true;
                
            } else {
                
                return false;
            
            }
        } catch (IOException | InterruptedException | URISyntaxException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar criar um novo Paciente!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
    }
    
    public boolean editarPaciente(String idPaciente, String nome, String sobrenome,
            String cpf, String dataNascimento, String genero, String status, 
            String email, String telefone, String cep, String rua, 
            String complemento, String bairro, String localidade, String uf) {
        
        if (idPaciente == null || idPaciente.isBlank()) {
            return false;
        }
        
        if (nome == null || nome.isBlank() || nome.length() < 3) {
            return false;
        }
        
        if (sobrenome == null || sobrenome.isBlank() || sobrenome.length() < 3) {
            return false;
        }
        
        if (!Validador.isCpf(cpf) ||
                !Validador.isEmail(email) ||
                !Validador.isTelefone(telefone) ||
                !Validador.isCep(cep)) {
            return false;
        }
        
        if (rua == null || rua.isBlank()) {
            return false;
        }
        
        if (bairro == null || bairro.isBlank()) {
            return false;
        }
        
        if (localidade == null || bairro.isBlank()) {
            return false;
        }
        
        if (uf == null || uf.isBlank() || uf.length() > 2) {
            return false;
        }
        
        LocalDate ldDataNascimento = LocalDate.parse(dataNascimento, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        TipoGenero eGenero = TipoGenero.fromTipo(genero);
        TipoStatusConta eStatus = TipoStatusConta.fromTipo(status);
        
        AtualizarEnderecoDto endereco = new AtualizarEnderecoDto(
                cep, 
                rua, 
                complemento, 
                bairro, 
                localidade, 
                uf);
        
        AtualizarPacienteDto atualizarPacienteDto = new AtualizarPacienteDto(
                nome, 
                sobrenome, 
                cpf, 
                ldDataNascimento, 
                eGenero, 
                eStatus, 
                email, 
                telefone, 
                endereco);
        
        try {
            
            HttpResponse<String> response = client.editarPaciente(UUID.fromString(idPaciente), atualizarPacienteDto);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_DELECAO_EDICAO.getTipo()) {
                
                return true;
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar atualizar o paciente.\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
        
    }
    
    public boolean deletarPaciente(String idPaciente) {
        
        if (idPaciente == null || idPaciente.isBlank()) {
            return false;
        }
        
        try {
            
            HttpResponse<String> response = client.deletarPaciente(UUID.fromString(idPaciente));
            
            if (response.statusCode() == StatusOperacao.SUCESSO_DELECAO_EDICAO.getTipo()) {
                
                return true;
            
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar deletar o paciente.\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return false;
        
    }
    
    public SaidaSimplesPacienteDto buscarSaidaSimplesPacienteDto(UUID idPaciente) {
        
        if (idPaciente == null) {
            return null;
        }
        
        SaidaSimplesPacienteDto pacienteDto = new SaidaSimplesPacienteDto();
        
        try {
            
            HttpResponse<String> response = client.buscarSaidaSimplesPacienteDtoPorId(idPaciente);
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {
                
                pacienteDto = mapper.readValue(response.body(), SaidaSimplesPacienteDto.class);
                
            } else {
                
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição do Paciente! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar buscar o paciente!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return pacienteDto;
        
    }
    
    public List<SaidaSimplesPacienteDto> listarSaidasSimplesPacienteDto() {
        
        List<SaidaSimplesPacienteDto> listaPacientes = new ArrayList<>();
        
        try {
            
            HttpResponse<String> response = client.listarSaidaSimplesPacienteDto();
            
            if (response.statusCode() == StatusOperacao.SUCESSO_BUSCA.getTipo()) {

                listaPacientes = mapper.readValue(response.body(), new TypeReference<List<SaidaSimplesPacienteDto>>() { });

            } else {
                
                JOptionPane.showMessageDialog(null, "Ocorreu um erro na requisição dos Pacientes! Status da requisição: " + response.statusCode() + "\nSe o erro persistir contate o administrador do sistema!", "Erro de requisição", JOptionPane.ERROR_MESSAGE);
                
            }
            
        } catch (IOException | InterruptedException ex) {
            
            JOptionPane.showMessageDialog(null, "Erro ao tentar listar todos os pacientes!\nErro: " + ex.toString(), "Ocorreu um erro", JOptionPane.ERROR_MESSAGE);
            
        }
        
        return listaPacientes;
        
    }
    
}
