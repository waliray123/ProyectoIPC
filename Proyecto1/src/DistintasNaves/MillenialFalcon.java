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
public class MillenialFalcon extends Naves {
    
     public MillenialFalcon() {
        this.costoProduccion = 70;
        this.espacioTransporte = 58;
        this.velocidad = 1.5;
    }
    
    @Override
    public String tipoNave(){
        return "Millenial Falcon";
    }
    
}
