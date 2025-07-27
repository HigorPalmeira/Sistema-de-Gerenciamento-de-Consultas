/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.model.enums;

/**
 *
 * @author higor
 */
public class Genero {

    public enum TipoGenero {
        FEMININO("FEMININO"),
        MASCULINO("MASCULINO"),
        NAO_DEFINIDO("NÃO DEFINIDO");

        private String tipo;

        TipoGenero(String tipo) {
            this.tipo = tipo;
        }

        public String getTipo() {
            return this.tipo;
        }

        public static TipoGenero fromTipo(String tipo) {
            for (TipoGenero genero : TipoGenero.values()) {
                if (genero.tipo.equalsIgnoreCase(tipo)) {
                    return genero;
                }
            }

            throw new UnsupportedOperationException("O tipo não é compatível!");
        }
    }

}
