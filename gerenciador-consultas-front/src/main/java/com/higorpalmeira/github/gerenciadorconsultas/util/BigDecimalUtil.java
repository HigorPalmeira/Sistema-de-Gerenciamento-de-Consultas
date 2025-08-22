/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.higorpalmeira.github.gerenciadorconsultas.util;

import java.math.BigDecimal;

/**
 *
 * @author higor
 */
public class BigDecimalUtil {
    
    public static BigDecimal ofString(String value) {
        
        if (value == null || value.isBlank()) {
            throw new RuntimeException("Valor inválido para transformação!");
        }
        
        return new BigDecimal(
                value.contains(",") 
                ? value.replace(",", ".")
                : value.concat(".00")
        );
        
    }
    
}
