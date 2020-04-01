/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistintosPlanetas;

import DistintosGuerreros.Mole;
import Konquest.Guerreros;
import Konquest.Planetas;

/**
 *
 * @author user-ubunto
 */
public class Tierra extends Planetas{

    public Tierra(String NombrePlaneta, int porcentajeMuerte, int cantidadDinero, int cantidadConstructores, int posicionX, int posicionY, int cantidadNaves, int cantidadGuerreros) {
        super(NombrePlaneta, porcentajeMuerte, cantidadDinero, cantidadConstructores, posicionX, posicionY, cantidadNaves, cantidadGuerreros);
        this.guerrerosDelPlaneta = new Guerreros[0];
        this.ProduccionGuerreros(cantidadGuerreros);
    }
    
    @Override
    public String tipoPlaneta(){
        return "Tierra";
    }
    
    @Override
    public void ProduccionGuerreros(int cantidadGuerrerosInicial){
        int cantidadGuerrerosProducir = 0;
        
        if (cantidadGuerrerosInicial != 0){
            cantidadGuerrerosProducir = cantidadGuerrerosInicial;
        }else if (cantidadGuerrerosInicial == 0){
            cantidadGuerrerosProducir = (int) (Math.random() * 11 + 15);
        }
        Guerreros guerrerosDelPlaneta2[] = this.guerrerosDelPlaneta;
        this.guerrerosDelPlaneta = new Guerreros[guerrerosDelPlaneta2.length+cantidadGuerrerosProducir]; 
        for (int i = 0; i < guerrerosDelPlaneta2.length; i++) {
            this.guerrerosDelPlaneta[i] = guerrerosDelPlaneta2[i];            
        }
        for (int i = guerrerosDelPlaneta2.length; i < (guerrerosDelPlaneta2.length+cantidadGuerrerosProducir); i++) {
            this.guerrerosDelPlaneta[i] = new Mole(this.porcentajeMuerte);
        }
        this.cantidadGuerreros = this.guerrerosDelPlaneta.length;        
    }

    @Override
    public void produccionDinero(){
        int cantidadProducidaDinero = (int)(Math.random() * 51 + 50);
        this.cantidadDinero += cantidadProducidaDinero;
    }
}
