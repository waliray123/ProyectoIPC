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
public class Mole extends Guerreros{

    public Mole(int porcentajeMuerte){
        this.factorMuerte = 1.5;
        this.ataqueEspecial = "Entierro Mortal";
        this.valorEspacioEnNave = 1;
        // Se calcula Valor de Muerte
        this.calcularValorMuerte(porcentajeMuerte);        
    }
    
    
    
    
    
    
}
