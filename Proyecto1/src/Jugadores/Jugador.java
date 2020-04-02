/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jugadores;

import Konquest.Constructores;
import Konquest.Guerreros;
import Konquest.Naves;
import Konquest.Planetas;

/**
 *
 * @author user-ubunto
 */
public class Jugador {
    public int numeroJugador;
    private String nombreJugador;
    private Planetas[] listaPlanetas;
    private int cantidadDinero;


    // Constructor
    public Jugador(int numeroJugador, String nombreJugador, Planetas primerPlaneta,int cantidadDinero) {
        this.nombreJugador = nombreJugador;
        this.numeroJugador = numeroJugador;
        this.listaPlanetas = new Planetas[1];        
        this.listaPlanetas[0] = primerPlaneta;
        this.cantidadDinero = cantidadDinero;
    }
    
    /**
    * El metodo sirve para asignar un planeta al jugador
    * @param planetaAsignar
    */
    public void AsignarPlaneta(Planetas planetaAsignar){
        Planetas[] listaPlanetas2 = new Planetas[this.listaPlanetas.length];        
        for (int i = 0; i < this.listaPlanetas.length; i++) {
            listaPlanetas2[i] = this.listaPlanetas[i];  
        }
        this.listaPlanetas = new Planetas[listaPlanetas2.length + 1];        
        for (int i = 0; i < listaPlanetas2.length; i++) {
            this.listaPlanetas[i] = listaPlanetas2[i];
        }
        this.listaPlanetas[listaPlanetas2.length] = planetaAsignar;
    }
    
    /**
    * El metodo sirve para desasignar un planeta al jugador
    * @param planetaQuitar
    */
    public void quitarPlaneta(Planetas planetaQuitar){
        Planetas[] listaPlanetas2 = this.listaPlanetas;
        this.listaPlanetas = new Planetas[listaPlanetas2.length - 1];
        for (int i = 0; i < this.listaPlanetas.length; i++) {
            if (planetaQuitar != listaPlanetas2[i]) {
                this.listaPlanetas[i] = listaPlanetas2[i];
            }            
        }         
    }
    
    /**
    * El metodo sirve para agregar una cantidad de dinero al jugador
    * @param cantidadDinero
    */
    public void AgregarDinero(int cantidadDinero){
        this.cantidadDinero += cantidadDinero;
    }
    
    /**
    * El metodo sirve para buscar un planeta en especifico en el listado del jugador
    * @param planetaBuscar
    */
    public boolean EstaPlanetaEnLista(Planetas planetaBuscar){
        for (Planetas planetaEnLista : this.listaPlanetas) {
            if (planetaEnLista == planetaBuscar) {
                return true;
            }
        }
        return false;
    }
    
    /**
    * El metodo sirve para obtener el nombre del jugador
    * 
    */
    public String getNombreJugador() {
        return this.nombreJugador;
    }
    
    /**
    * El metodo sirve para obtener la cantidad Planetas que tiene en su poder el jugador
    * 
    */
    public int obtenerCantidadPlanetasConquistados(){
        return this.listaPlanetas.length;
    }
    
    
//    public void PruebaListaPlanetas(){
//        for (Planetas planetas : this.listaPlanetas) {
//            System.out.println("Nombre: " + planetas.obtenerNombrePlaneta());            
//        }
//    }
    
}
