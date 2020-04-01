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
public class Nemo extends Guerreros{
    
    public Nemo(int porcentajeMuerte){
        this.factorMuerte = 1.6;
        this.ataqueEspecial = "Turbo Chorro Venenoso";
        this.valorEspacioEnNave = 1;        
        // Se calcula Valor de Muerte
        this.calcularValorMuerte(porcentajeMuerte);        
    }
    
}
