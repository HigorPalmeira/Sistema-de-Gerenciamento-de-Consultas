/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author higor
 */
public class Formatador {
    
    public static String limparFormatacao(String formatado) {
        
        if (formatado == null || formatado.isBlank()) return formatado;
        
        return formatado.replaceAll("[^A-Z0-9]", "");
        
    }
    
    public static String ofLocalDateTime(LocalDateTime dataHora) {
        
        if (dataHora == null) throw new RuntimeException("Data e Hora inválidas para formatação!");
        
        return dataHora.format(DateTimeFormatter.ofPattern("dd-MM-yyyy_HH-mm-ss"));
        
    }
    
}
