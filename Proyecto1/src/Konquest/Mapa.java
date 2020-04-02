/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konquest;
import DistintosPlanetas.Agua;
import DistintosPlanetas.Fuego;
import DistintosPlanetas.Organico;
import DistintosPlanetas.Radioactivo;
import DistintosPlanetas.Tierra;
import java.util.Scanner;
import Jugadores.Jugador;
/**
 *
 * @author user-ubunto
 */
public class Mapa {
    
    Scanner scaner = new Scanner(System.in);
    private int cantidadFilas;
    private int cantidadColumnas;
    private int cantidadPlanetasNeutrales;
//    boolean tablero[][];
    public Planetas[] planetasEnMapa;
    
    private Planetas[][] tablero;
    private String nombreJ1;
    private String nombreJ2;   
    
    private Jugador jugador1;
    private Jugador jugador2;
    
    
    public void obtenerCantidadFilasYColumnasInicial(){
        System.out.println("Ingrese la cantidad de filas a dibujar en el mapa");
        this.cantidadFilas = Integer.parseInt(scaner.nextLine());
        System.out.println("Ingrese la cantidad de Columnas a dibujar en el mapa");
        this.cantidadColumnas = Integer.parseInt(scaner.nextLine());                
        
    }
    
    public boolean crearCantidadPlanetasNeutrales(){
        System.out.println("Ingrese la cantidad de Planetas Neutrales en el mapa");
        System.out.println("Recuerde que los planetas de cada jugador no son neutrales");
        int cantidadPlanetas = Integer.parseInt(scaner.nextLine());
        
        int cantidadColumnas1 = this.cantidadColumnas;
        int cantidadFilas1 = this.cantidadFilas;
        
        if (((cantidadColumnas1 * cantidadFilas1)- 2) >= cantidadPlanetas){
            this.cantidadPlanetasNeutrales = cantidadPlanetas;
            this.planetasEnMapa = new Planetas[this.cantidadPlanetasNeutrales + 2];
            return true;            
        }else{
            return false;
        }
        
        
    }
    
    public void crearPlanetasEnMapa(){
        //Variables para creacion de Planeta
        String nombrePlaneta = null;
        int porcentajeMuerte = 0;
        int cantidadDinero = 0;
        int cantidadConstructores = 0;            
        int posicionX = 0;
        int posicionY = 0;
        int cantidadNaves = 0;
        int cantidadGuerreros = 0;
        int tipoPlaneta = 0;
        int produccionDeGuerreros = 0;
        
        
        System.out.println("Creando Planetas Neutrales");        
        planetasEnMapa = new Planetas[this.cantidadPlanetasNeutrales+2];
        tablero = new Planetas[this.cantidadFilas][this.cantidadColumnas];
        int probabilidadPlanetas = 0;
        char letraNombre = 65;
        String planetaNoAleatorio = "";
        // inicio de creacion de planetas segun cantidad de planetas Neutrales
        for (int i = 0; i < this.cantidadPlanetasNeutrales+2; i++) {
            boolean estaEnAleatorio = false;
            //Crear porcentaje muerte aleatorio
            porcentajeMuerte = (int)(Math.random() *(9000) + 1000);
            boolean posicionPlanetaCorrecta = false;
            
            if (i< this.cantidadPlanetasNeutrales) {
               // Se pregunta sobre la alietoriedad de cada uno de los planetas
                System.out.println("Deseas ingresar los datos del plantena No." + (i+1));
                System.out.println("Escriba S para Si o N para No");
            
                planetaNoAleatorio = scaner.nextLine(); 
            }else{
                //Sirve Para crear planetas del jugador
                planetaNoAleatorio = "N";
            }            
                                    
            if (planetaNoAleatorio.equalsIgnoreCase("S") || planetaNoAleatorio.equalsIgnoreCase("N")){
                
                if (planetaNoAleatorio.equalsIgnoreCase("S")){
                    boolean nombrePlanetaCorrecto = false;
                    while(nombrePlanetaCorrecto == false){
                        System.out.println("Ingrese el nombre del Planeta");
                            System.out.println("El nombre del planeta solo debe contener un caracter");
                            nombrePlaneta = scaner.nextLine();
                        if (nombrePlaneta.length() == 1) {
                            nombrePlanetaCorrecto = true;
                        }
                    }                    
                    System.out.println("Ingrese la cantidad de dinero");
                    cantidadDinero = Integer.parseInt(scaner.nextLine());
                    System.out.println("Ingrese la cantidad de Constructores");
                    cantidadConstructores = Integer.parseInt(scaner.nextLine());
                    System.out.println("Ingrese la cantidad de Naves");
                    cantidadNaves = Integer.parseInt(scaner.nextLine());
                    System.out.println("Ingrese la cantidad de Guerreros");
                    cantidadGuerreros = Integer.parseInt(scaner.nextLine());
                
                    //Pregunta la Posicion del planeta sin repetir la de los demas
                    
                    while (posicionPlanetaCorrecta == false){
                        System.out.println("Ingrese la columna en donde desea que este el planeta");
                        posicionX = Integer.parseInt(scaner.nextLine());
                        System.out.println("Ingrese la fila en donde desea que este el planeta");
                        posicionY = Integer.parseInt(scaner.nextLine());
                        if (posicionX<=this.cantidadColumnas && posicionY <= this.cantidadFilas) {
                            if (i!=0){
                                posicionPlanetaCorrecta = this.HayPlanetaEnPosicion(posicionX, posicionY,i);
                            }else{
                                posicionPlanetaCorrecta = true;
                            }
                            if (posicionPlanetaCorrecta == false){
                                System.out.println("Debe Reingresar los datos de la columna y la fila");
                                System.out.println("Ya que esa posicion ya esta ocupada");
                            }
                        }else{
                            System.out.println("Debe ingresar una coordenada correcta");
                            System.out.println("No debe traspasar el mapa");                            
                        }
                        
                    }                                 
                
                    //Pregunta el tipo de Planeta a crear
                    boolean tipoDePlanetaCorrecto = false;
                    while(tipoDePlanetaCorrecto == false){
                        System.out.println("Ingrese el numero segun el tipo del planeta que desea:");
                        System.out.println("1.Tierra");
                        System.out.println("2.Agua");
                        System.out.println("3.Fuego");
                        System.out.println("4.Organico");
                        System.out.println("5.Radioactivo");
                        tipoPlaneta = Integer.parseInt(scaner.nextLine());
                        if(tipoPlaneta >= 1 && tipoPlaneta <= 5){
                            tipoDePlanetaCorrecto = true;
                        }                    
                }
                
                 //Creacion de Planeta segun tipo de planeta ingresado                                              
                
                }else if(planetaNoAleatorio.equalsIgnoreCase("N")){
                    // Encontrar el planeta a Crear
                    probabilidadPlanetas = (int)(Math.random() * 100 +1);                
                    nombrePlaneta = Character.toString(letraNombre);                
                    cantidadDinero = (int)(Math.random() * 400 + 100);
                    cantidadConstructores = 1;
                    cantidadNaves = (int)(Math.random() * 3 + 1);                
                    letraNombre +=1;
                    ///////// Arreglar posicion de mapa X y Y
                    
                    while (posicionPlanetaCorrecta == false){                        
                        posicionX = (int)(Math.random() * this.cantidadColumnas +1); 
                        posicionY = (int)(Math.random() * this.cantidadFilas +1);
                        if (i!=0){
                            posicionPlanetaCorrecta = this.HayPlanetaEnPosicion(posicionX, posicionY,i);
                        }else{
                            posicionPlanetaCorrecta = true;
                        }                                                 
                    }   
                    
                    if(probabilidadPlanetas >= 1 && probabilidadPlanetas <=45){
                        tipoPlaneta = 1;                        
                    }else if(probabilidadPlanetas > 45 && probabilidadPlanetas <=70){
                        tipoPlaneta = 2;
                    }else if(probabilidadPlanetas > 70 && probabilidadPlanetas <=85){
                        tipoPlaneta = 3;
                    }else if(probabilidadPlanetas > 85 && probabilidadPlanetas <=95){
                        tipoPlaneta = 4;
                    }else if(probabilidadPlanetas > 95 && probabilidadPlanetas <=100){
                        tipoPlaneta = 5;
                    }                    
                    estaEnAleatorio = true;
                }              
                produccionDeGuerreros =0;
                if(tipoPlaneta == 1){
                    // crear Planeta Tierra
                    produccionDeGuerreros = (int) (Math.random() * 11 + 15);
                    cantidadGuerreros = this.AgregarCantidadGuerreros(produccionDeGuerreros, cantidadGuerreros, estaEnAleatorio);
                    this.planetasEnMapa[i] = new Tierra(nombrePlaneta, porcentajeMuerte, cantidadDinero, 
                            cantidadConstructores, posicionX, posicionY, cantidadNaves, cantidadGuerreros);
                }else if (tipoPlaneta == 2){
                    //crear Planeta Agua
                    produccionDeGuerreros = (int) (Math.random() * 12 + 12);
                    cantidadGuerreros = this.AgregarCantidadGuerreros(produccionDeGuerreros, cantidadGuerreros, estaEnAleatorio);
                    this.planetasEnMapa[i] = new Agua(nombrePlaneta, porcentajeMuerte, cantidadDinero, 
                            cantidadConstructores, posicionX, posicionY, cantidadNaves, cantidadGuerreros);  
                }else if (tipoPlaneta == 3){
                    //crear Planeta Fuego
                    produccionDeGuerreros = (int) (Math.random() * 11 + 10);
                    cantidadGuerreros = this.AgregarCantidadGuerreros(produccionDeGuerreros, cantidadGuerreros, estaEnAleatorio);
                    this.planetasEnMapa[i] = new Fuego(nombrePlaneta, porcentajeMuerte, cantidadDinero, 
                            cantidadConstructores, posicionX, posicionY, cantidadNaves, cantidadGuerreros); 
                }else if (tipoPlaneta == 4){
                    //crear Planeta Organico
                    produccionDeGuerreros = (int) (Math.random() * 11 + 5);
                    cantidadGuerreros = this.AgregarCantidadGuerreros(produccionDeGuerreros, cantidadGuerreros, estaEnAleatorio);
                    this.planetasEnMapa[i] = new Organico(nombrePlaneta, porcentajeMuerte, cantidadDinero, 
                            cantidadConstructores, posicionX, posicionY, cantidadNaves, cantidadGuerreros);
                }else if (tipoPlaneta == 5){
                    //crear Planeta RadioActivo
                    produccionDeGuerreros = (int) (Math.random() * 7 + 3);
                    cantidadGuerreros = this.AgregarCantidadGuerreros(produccionDeGuerreros, cantidadGuerreros, estaEnAleatorio);
                    this.planetasEnMapa[i] = new Radioactivo(nombrePlaneta, porcentajeMuerte, cantidadDinero, 
                            cantidadConstructores, posicionX, posicionY, cantidadNaves, cantidadGuerreros);
                }
                // Poner tablero
                this.tablero[(posicionY)-1][(posicionX)-1]=this.planetasEnMapa[i];
                if (i == this.cantidadPlanetasNeutrales) {
                    jugador1 = new Jugador(1,this.nombreJ1,this.planetasEnMapa[i],cantidadDinero);
                }else if(i > this.cantidadPlanetasNeutrales){
                    jugador2 = new Jugador(2,this.nombreJ2,this.planetasEnMapa[i],cantidadDinero);
                }
            }else{
                i -=1;
                        
            }                                        
        }
    }
    
    public void NombresJugadores(){        
        String nombre; 
        for (int i = 1; i <= 2; i++) {
            System.out.println("Ingrese el nombre del Jugador " + i);
            System.out.println("El nombre tiene que ser exactamente de 3 letras");
            nombre = scaner.nextLine();
            if (nombre.length() == 3) {
                if (i == 1) {
                   this.nombreJ1 = nombre;
                }else if(i == 2){
                    this.nombreJ2 = nombre;
                }
            }else{
                i -= 1;
            }            
        }        
        
    }
    
    
    
    /**
     * Este metodo se usa para verificar que cantidad de Guerreros va a tener.
     * Si es aleatorio toma los de produccion.
     * Si no es aleatorio toma los que se indicaron anteriormente.
     * @param produccionGuerreros
     * @param cantidadGuerreros
     * @param estaEnAleatorio
     * @return 
     */
    public Integer AgregarCantidadGuerreros(int produccionGuerreros, int cantidadGuerreros, boolean estaEnAleatorio){
        if (estaEnAleatorio = true){
            cantidadGuerreros = produccionGuerreros;
        }
        return cantidadGuerreros;
    }
    
    
    
    public boolean HayPlanetaEnPosicion(int posicionX, int posicionY, int planetasCreados){
        boolean planetaNoOcupado = true;
        for (int i = 0; i < planetasCreados; i++) {
            Planetas planetaPosicion = planetasEnMapa[i];           
            if (planetaPosicion.obtenerPosicionX() == posicionX &&
                planetaPosicion.obtenerPosicionY() == posicionY){
                planetaNoOcupado = false;
            }
        }
        return planetaNoOcupado;
    }
    
    
    public void DibujarMapa(){
        //No esta terminado
        int contador = 1;
        String color = "\u001B[0m";
        char letraColumna = 65;
        for (int j = 0; j < this.cantidadColumnas; j++) {
            System.out.print(color+"      "+Character.toString(letraColumna)+"     ");
            letraColumna+=1;
        }
        System.out.println("");
        for (int i = 0; i < this.cantidadFilas; i++) {
            for (int j = 0; j < this.cantidadColumnas; j++) {                
                Planetas planetaBuscar = this.tablero[i][j];
                if (planetaBuscar == null){                    
                    if (contador == 1) {
                        if (j== 0) {
                            System.out.print((i+1) + color + "---------- ");
                        }else{
                            System.out.print(color + " ---------- ");                        
                        }
                    }else if (contador == 2){
                        System.out.print(color + "|          |");                        
                    }else if(contador == 3){
                        System.out.print(color + "|          |");
                    }
                }else {
                    boolean planetaEnJugador1 = this.jugador1.EstaPlanetaEnLista(planetaBuscar);
                    boolean planetaEnJugador2 = this.jugador2.EstaPlanetaEnLista(planetaBuscar);
                    if(planetaEnJugador1 == true){
                        color = "\u001B[32m";
                    }else if(planetaEnJugador2 == true){
                        color = "\u001B[33m";
                    }else{
                        color = "\u001B[36m";
                    }
                    if (contador == 1) {
                        if (j== 0) {
                            System.out.print((i+1) + color + "---------- ");
                        }else{
                            System.out.print(color + " ---------- ");                        
                        }
                    }else if (contador == 2){
                        if (planetaEnJugador1 == true) {
                            System.out.print(color + "|Dueno: "+this.jugador1.getNombreJugador() +"|");
                        }else if(planetaEnJugador2 == true){
                            System.out.print(color + "|Dueno: "+this.jugador2.getNombreJugador() +"|");
                        }else{
                            System.out.print(color + "|Neutral   |");;
                        }                     
                    }else if(contador == 3){
                        System.out.print(color + "|Nombre: "+ planetaBuscar.obtenerNombrePlaneta() +" |");
                    }
                    planetaEnJugador1 = false;
                    planetaEnJugador2 = false;
                }
                color = "\u001B[0m";
                if (j == (this.cantidadColumnas-1)) {
                    contador+=1;
                    System.out.println("");
                    if (contador <= 3){
                        j = -1;
                    }else{
                        contador = 0;
                        break;
                    }
                }                                    
                    color = "\u001B[0m";
            }
            for (int j = 0; j < this.cantidadColumnas; j++) {
                 
                if (j == (this.cantidadColumnas-1)) {
                 System.out.println(color +" ---------- ");   
                }else{
                 System.out.print(color +" ---------- ");   
                }
            }
            
            
        }
        color = "\u001B[0m";
    }
    
    
    
//    public void PruebaPosiciones(){
//        for (Planetas planetas : this.planetasEnMapa) {
//            System.out.println("Nombre: " + planetas.obtenerNombrePlaneta());
//            System.out.println("Posicion en x " + planetas.obtenerPosicionX());
//            System.out.println("Posicion en y " + planetas.obtenerPosicionY());
//        }
////        System.out.println("\u001B[32m" + "Texto de color Verde");
//    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }
    
    
    
    
    public int obtenerCantidadPlanetasNeutrales(){        
        return this.cantidadPlanetasNeutrales;
    }
    public int obtenerCantidadFilas(){        
        return this.cantidadFilas;
    }
    public int obtenerCantidadColumnas(){        
        return this.cantidadColumnas;
    }

    public Planetas[][] obtenerTablero() {
        return this.tablero;
    }
    
    
    
}
