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
public class StarDestroyer extends Naves{
    
    public StarDestroyer() {
        this.costoProduccion = 100;
        this.espacioTransporte = 80;
        this.velocidad = 1.75;
    }
    
    @Override
    public String tipoNave(){
        return "Star Destroyer";
    }
}
