/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.enums;

/**
 *
 * @author higor
 */
public class TipoStatus {
    
    public enum StatusOperacao {
        
        SUCESSO_CRIACAO(201),
        SUCESSO_BUSCA(200),
        SUCESSO_DELECAO_EDICAO(204);
        
        private int tipo;
        
        StatusOperacao(int tipo) {
            this.tipo = tipo;
        }
        
        public int getTipo() {
            return this.tipo;
        }
        
        public static StatusOperacao fromTipo(int tipo) {
            for (StatusOperacao status : StatusOperacao.values()) {
                if (status.tipo == tipo) {
                    return status;
                }
            }
            
            throw new RuntimeException("O tipo não é compatível!");
        }
        
    }
    
    public enum TipoStatusConta {
        
        ATIVO("ATIVO"),
        INATIVO("INATIVO");
        
        private String tipo;
        
        TipoStatusConta(String tipo) {
            this.tipo = tipo;
        }
        
        public String getTipo() {
            return this.tipo;
        }
        
        public static TipoStatusConta fromTipo(String tipo) {
            for (TipoStatusConta status : TipoStatusConta.values()) {
                if (status.tipo.equalsIgnoreCase(tipo)) {
                    return status;
                }
            }
            
            throw new RuntimeException("O tipo não é compatível!");
        }
        
    }
    
}
