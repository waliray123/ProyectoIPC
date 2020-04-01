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
public class Groot extends Guerreros{
    
    public Groot(int porcentajeMuerte){
        this.factorMuerte = 1.85;
        this.ataqueEspecial = "Trampa Ramal";
        this.valorEspacioEnNave = 3;
        // Se calcula Valor de Muerte
        this.calcularValorMuerte(porcentajeMuerte);        
    }
    
}
