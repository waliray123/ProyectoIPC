/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistintasNaves;

import Konquest.Naves;

/**
 *
 * @author user-ubunto
 */
public class Naboo extends Naves{

    public Naboo() {
        this.costoProduccion = 40;
        this.espacioTransporte = 25;
        this.velocidad = 1;
    }        
    
    @Override
    public String tipoNave(){
        return "Naboo N-1";
    }
    
    
}
