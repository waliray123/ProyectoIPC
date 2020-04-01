/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konquest;

//import java.util.Scanner;

import DistintosGuerreros.Mole;
import Jugadores.Jugador;
import java.io.IOException;



/**
 *
 * @author user-ubunto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        // TODO code application logic here
//        Scanner scaner = new Scanner(System.in);
        
        
        System.out.println("Bienvenido a Konquest");
//        System.out.println("\u001B[32m" + "Texto de color rojo");        
        
        
        Mapa mapainicial = new Mapa();
        mapainicial.NombresJugadores();
        
        mapainicial.obtenerCantidadFilasYColumnasInicial();        
        
        boolean planetasNeutralesCorrecto = false;
        
        while(planetasNeutralesCorrecto == false){
           planetasNeutralesCorrecto = mapainicial.crearCantidadPlanetasNeutrales();
//           System.out.print("\033[H\033[2J");  
//           System.out.flush(); 
           Runtime.getRuntime().exec("clear");
        }
        
        
        mapainicial.crearPlanetasEnMapa();
        
//      mapainicial.PruebaPosiciones();
        

        mapainicial.DibujarMapa();
        
//        mapainicial.jugador1.PruebaListaPlanetas();
//        mapainicial.jugador2.PruebaListaPlanetas();
        
        Juego juegoPrincipal = new Juego();
        juegoPrincipal.Partida(mapainicial.jugador1, mapainicial.jugador2, mapainicial.obtenerTablero(),
                mapainicial, mapainicial.obtenerCantidadColumnas(), mapainicial.obtenerCantidadFilas());
        
    }

}
