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
        SUCESSO_BUSCA_EDICAO(200),
        SUCESSO_DELECAO(204);
        
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
    
}
