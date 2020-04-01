/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konquest;

/**
 *
 * @author user-ubunto
 */
public class Guerreros {
    protected double factorMuerte;
    protected int valorEspacioEnNave;
    protected String ataqueEspecial;
    protected double valorMuerte;   

    
    public void calcularValorMuerte(int porcentajeMuerte){
        
        // Se obtiene el valor de Muerte segun el porcentaje de muerte del planeta
        // y el valor de muerte del guerrero en especifico
        
        this.valorMuerte = ((porcentajeMuerte / 1000) * this.factorMuerte);
    }
}
