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
public class Naves {
    protected int espacioTransporte;    
    protected int costoProduccion;
    protected double velocidad;
    
    public int obtetenerEspacioTransporte(){
        return this.espacioTransporte;
    }
    public int obtetenerCostoProduccion(){
        return this.costoProduccion;
    }
    public double obtetenerVelocidad(){
        return this.velocidad;
    }
    
    public String tipoNave(){
        return "";
    }
    
}
