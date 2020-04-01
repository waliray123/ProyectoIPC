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
public class XWing extends Naves{
    
    public XWing () {
        this.costoProduccion = 50;
        this.espacioTransporte = 42;
        this.velocidad = 1.25;
    }
    
    @Override
    public String tipoNave(){
        return "X-Wing";
    }
}
