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
//    private Naves[] listaNaves;
//    private Constructores[] listaConstructores;
    private int cantidadDinero;
//    private Guerreros[] listaGuerreros;

    public Jugador(int numeroJugador, String nombreJugador, Planetas primerPlaneta,int cantidadDinero) {
        this.nombreJugador = nombreJugador;
        this.numeroJugador = numeroJugador;
        this.listaPlanetas = new Planetas[1];        
        this.listaPlanetas[0] = primerPlaneta;
        this.cantidadDinero = cantidadDinero;
    }
    
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
    
    public void quitarPlaneta(Planetas planetaQuitar){
        Planetas[] listaPlanetas2 = this.listaPlanetas;
        this.listaPlanetas = new Planetas[listaPlanetas2.length - 1];
        for (int i = 0; i < this.listaPlanetas.length; i++) {
            if (planetaQuitar != listaPlanetas2[i]) {
                this.listaPlanetas[i] = listaPlanetas2[i];
            }            
        }         
    }
    
    public void AgregarDinero(int cantidadDinero){
        this.cantidadDinero += cantidadDinero;
    }
    
    public boolean EstaPlanetaEnLista(Planetas planetaBuscar){
        for (Planetas planetaEnLista : this.listaPlanetas) {
            if (planetaEnLista == planetaBuscar) {
                return true;
            }
        }
        return false;
    }

    public String getNombreJugador() {
        return this.nombreJugador;
    }
    
    public int obtenerCantidadPlanetasConquistados(){
        return this.listaPlanetas.length;
    }
    
    public void PruebaListaPlanetas(){
        for (Planetas planetas : this.listaPlanetas) {
            System.out.println("Nombre: " + planetas.obtenerNombrePlaneta());            
        }
    }
    
}
