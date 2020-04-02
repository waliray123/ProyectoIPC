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
import java.util.Scanner;



/**
 *
 * @author user-ubunto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
        Scanner scaner = new Scanner(System.in);
        Mapa[] mapasJugables = new Mapa[5];
        
        System.out.println("Bienvenido a Konquest");
        int contadorMapasUsados = 0;
        boolean cerrarJuego = false;
        
        while (cerrarJuego == false){
            System.out.println("Deseas Jugar? S/n");
            String jugar = scaner.nextLine();
            int numeroMapaJugar = 0;
            
            if(jugar.equalsIgnoreCase("s")){
                
                System.out.println("Que deseas hacer? Escribe el numero de la opcion");
                System.out.println("1.Crear un mapa nuevo");
                System.out.println("2.Jugar un mapa usado anteriormente");
                System.out.println("3.Editar un mapa");
                String mapaNuevo = scaner.nextLine();
                    
                if(mapaNuevo.equalsIgnoreCase("1") ){
                    Mapa mapainicial = new Mapa();
                    mapainicial.NombresJugadores();
                    mapainicial.obtenerCantidadFilasYColumnasInicial(); 
                    boolean planetasNeutralesCorrecto = false;
        
                    while(planetasNeutralesCorrecto == false){
                        planetasNeutralesCorrecto = mapainicial.crearCantidadPlanetasNeutrales();
                        Runtime.getRuntime().exec("clear");
                    }
                    
                    mapainicial.crearPlanetasEnMapa();
                    mapasJugables[contadorMapasUsados] = mapainicial;
                    
                    
                    numeroMapaJugar = contadorMapasUsados;
                    contadorMapasUsados +=1;
                }else if(mapaNuevo.equalsIgnoreCase("2") || mapaNuevo.equalsIgnoreCase("3")){
                    boolean mapasUsados = false;
                    for (int i = 0; i < 5; i++) {
                        if(mapasJugables[i] != null){
                            mapasUsados = true;
                        }
                    }
                    if (mapasUsados == false){
                        System.out.println("No hay mapas usados anteriormente");
                    }else if(mapasUsados == true){
                        if (mapaNuevo.equalsIgnoreCase("2")) {
                            System.out.println("Con que mapa deseas jugar?");
                            for (int i = 0; i < 5; i++) {
                                if(mapasJugables[i] != null){
                                    System.out.println("Mapa No. "+ i+1);
                                }
                            }
                            
                            try{
                                int numeroMapa = Integer.parseInt(scaner.nextLine());
                                numeroMapaJugar = numeroMapa;
                            }catch(NumberFormatException e){
                                System.out.println("Debe ingresar un numero");
                            }
                        }else if(mapaNuevo.equalsIgnoreCase("3")){
                            System.out.println("Que mapa deseas editar");
                            try{
                                int numeroMapa = Integer.parseInt(scaner.nextLine());
                                mapasJugables[numeroMapa].crearPlanetasEnMapa();
                                numeroMapaJugar = numeroMapa;
                            }catch(NumberFormatException e){
                                System.out.println("Debe ingresar un numero");
                            }
                            
                        }                        
                    }
                    
                }
                try{
                mapasJugables[numeroMapaJugar].DibujarMapa();
                Juego juegoPrincipal = new Juego();
                juegoPrincipal.Partida(mapasJugables[numeroMapaJugar].getJugador1(), 
                        mapasJugables[numeroMapaJugar].getJugador2(), mapasJugables[numeroMapaJugar].obtenerTablero(),
                        mapasJugables[numeroMapaJugar], mapasJugables[numeroMapaJugar].obtenerCantidadColumnas(), 
                        mapasJugables[numeroMapaJugar].obtenerCantidadFilas());
                }catch(NullPointerException e){
                    
                }
                
            }else if(jugar.equalsIgnoreCase("n")){
                cerrarJuego = true;
            }
            
            
            
            
        
                   
        
            
        
        
            
        
        }
                        
        
    }

}
