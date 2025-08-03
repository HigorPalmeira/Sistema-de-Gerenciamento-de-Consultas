/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.util;

/**
 *
 * @author higor
 */
public class Formatador {
    
    public static String limparFormatacao(String formatado) {
        
        if (formatado == null || formatado.isBlank()) return formatado;
        
        return formatado.replaceAll("[^A-Z0-9]", "");
        
    }
    
}
