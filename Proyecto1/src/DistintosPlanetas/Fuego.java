/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DistintosPlanetas;
import DistintosGuerreros.Magma;
import Konquest.Guerreros;
import Konquest.Planetas;
/**
 *
 * @author user-ubunto
 */
public class Fuego extends Planetas{
    //Constructor
    public Fuego(String NombrePlaneta, int porcentajeMuerte, int cantidadDinero, int cantidadConstructores, int posicionX, int posicionY, int cantidadNaves, int cantidadGuerreros) {
        super(NombrePlaneta, porcentajeMuerte, cantidadDinero, cantidadConstructores, posicionX, posicionY, cantidadNaves, cantidadGuerreros);
        this.guerrerosDelPlaneta = new Guerreros[0];
        this.ProduccionGuerreros(cantidadGuerreros);
    }
    
    @Override
    public String tipoPlaneta(){
        return "Fuego";
    }
    
    @Override
    public void ProduccionGuerreros(int cantidadGuerrerosInicial){
        int cantidadGuerrerosProducir = 0;
        if (cantidadGuerrerosInicial != 0){
            cantidadGuerrerosProducir = cantidadGuerrerosInicial;
        }else if (cantidadGuerrerosInicial == 0){
            cantidadGuerrerosProducir = (int) (Math.random() * 11 + 10);
        }
        Guerreros guerrerosDelPlaneta2[] = this.guerrerosDelPlaneta;
        this.guerrerosDelPlaneta = new Guerreros[guerrerosDelPlaneta2.length+cantidadGuerrerosProducir]; 
        for (int i = 0; i < guerrerosDelPlaneta2.length; i++) {
            this.guerrerosDelPlaneta[i] = guerrerosDelPlaneta2[i];            
        }
        for (int i = guerrerosDelPlaneta2.length; i < (guerrerosDelPlaneta2.length+cantidadGuerrerosProducir); i++) {
            this.guerrerosDelPlaneta[i] = new Magma(this.porcentajeMuerte);
        }
        this.cantidadGuerreros = this.guerrerosDelPlaneta.length;        
    }
   @Override
    public void produccionDinero(){
        int cantidadProducidaDinero = (int)(Math.random() * 71 + 70);
        this.cantidadDinero += cantidadProducidaDinero;
    }
    
}
