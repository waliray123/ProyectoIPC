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
public class FisionGuy extends Guerreros{
    
    public FisionGuy(int porcentajeMuerte){
        this.factorMuerte = 1.95;
        this.ataqueEspecial = "Rayos Gamma";
        this.valorEspacioEnNave = 4;
        // Se calcula Valor de Muerte
        this.calcularValorMuerte(porcentajeMuerte);        
    }    
    
}
