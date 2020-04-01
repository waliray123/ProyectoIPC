/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistintosGuerreros;

import Konquest.Guerreros;

/**
 *
 * @author user-ubunto
 */
public class Magma extends Guerreros{
    
    public Magma(int porcentajeMuerte){
        this.factorMuerte = 1.75;
        this.ataqueEspecial = "Bola de Lava";
        this.valorEspacioEnNave = 2;
        // Se calcula Valor de Muerte
        this.calcularValorMuerte(porcentajeMuerte);        
    }
    
}
