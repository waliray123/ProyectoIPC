/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konquest;

import DistintasNaves.MillenialFalcon;
import DistintasNaves.Naboo;
import DistintasNaves.StarDestroyer;
import DistintasNaves.XWing;
import DistintosConstructores.Ingeniero;
import DistintosConstructores.MaestroDeObra;
import DistintosConstructores.Obrero;
import java.util.Scanner;


/**
 *
 * @author user-ubunto
 */
public class Planetas {
    
    private String nombrePlaneta;
    protected int porcentajeMuerte;
    protected int cantidadDinero;
    private int cantidadConstructores;
    private int cantidadNaves;
    protected int cantidadGuerreros;    
    private int posicionX;
    private int posicionY;
    protected Guerreros[] guerrerosDelPlaneta;
    private Constructores[] constructoresEnPlaneta;
    private Naves[] navesEnPlaneta;
    
    private Constructores[] constructoresEnCola; 
    private String navesEnCola[];
    
    private Naves[] navesViajando;
    
    private int probabilidadDistribucion;
    
    Scanner scaner = new Scanner(System.in);
    
    public void ProduccionGuerreros(int cantidadGuerrerosInicial){
        
    }
    
    public void ProduccionConstructores(int tipoConstructor,int cantidadConstructores){
        Constructores[] constructoresEnPlaneta2 = this.constructoresEnPlaneta;
        this.constructoresEnPlaneta = new Constructores[constructoresEnPlaneta2.length + cantidadConstructores];
        for (int i = 0; i < constructoresEnPlaneta2.length; i++) {
            this.constructoresEnPlaneta[i] = constructoresEnPlaneta2[i];            
        }
        if(tipoConstructor == 1){
            for (int i = constructoresEnPlaneta2.length ; i < (constructoresEnPlaneta2.length + cantidadConstructores); i++) {
                this.constructoresEnPlaneta[i] = new Obrero();            
            }
        }else if(tipoConstructor == 2){
            for (int i = constructoresEnPlaneta2.length ; i < (constructoresEnPlaneta2.length + cantidadConstructores); i++) {
                this.constructoresEnPlaneta[i] = new MaestroDeObra();            
            }
        }else if(tipoConstructor == 3){
            for (int i = constructoresEnPlaneta2.length ; i < (constructoresEnPlaneta2.length + cantidadConstructores); i++) {
                this.constructoresEnPlaneta[i] = new Ingeniero();            
            }
        }
        this.cantidadConstructores = this.constructoresEnPlaneta.length;
    }

    public void setCantidadDinero(int cantidadDinero) {
        this.cantidadDinero = cantidadDinero;
    }
    
    
    //constructor
    public Planetas(String NombrePlaneta, int porcentajeMuerte, int cantidadDinero, 
            int cantidadConstructores, int posicionX, int posicionY,int cantidadNaves,
            int cantidadGuerreros) {
        this.nombrePlaneta = NombrePlaneta;
        this.porcentajeMuerte = porcentajeMuerte;
        this.cantidadDinero = cantidadDinero;
        this.cantidadConstructores = cantidadConstructores;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.cantidadNaves = cantidadNaves;
        this.cantidadGuerreros = cantidadGuerreros;
        this.constructoresEnPlaneta = new Constructores[0];
        this.ProduccionConstructores(1, cantidadConstructores);
        this.navesEnPlaneta = new Naves[cantidadNaves];
        for (int i = 0; i < cantidadNaves; i++) {
            this.navesEnPlaneta[i] = new Naboo();            
        }
        this.constructoresEnCola = new Constructores[1];
        this.navesEnCola = new String[1];
        this.navesViajando = new Naves[1];
    }                       
    
    public void imprimirDatos(){
        System.out.println("Tipo del planeta: "+ this.tipoPlaneta());
        System.out.println("Nombre del Planeta: "+this.nombrePlaneta);
        System.out.println("CantidadDinero: " +this.cantidadDinero);
        System.out.println("Porcentaje de Muerte: " + (this.porcentajeMuerte/1000));
        System.out.println("Cantidad Constructores: " + this.constructoresEnPlaneta.length);
        System.out.println("Cantidad Naves: " + this.navesEnPlaneta.length);
        System.out.println("Cantidad Guerreros: " + this.guerrerosDelPlaneta.length);        
    }
    
    /**
    * El metodo sirve para verificar que constructores estan disponibles para construir una nave
    * @param tipoConstructor
    */
    public boolean hayTipoConstructorNoEnCola(int tipoConstructor){
        boolean sePuedePonerConstructorEnCola = false;
        for (int i = 0; i < this.constructoresEnPlaneta.length; i++) {
            if (this.constructoresEnPlaneta[i].turnosParaConstruir == tipoConstructor) {
                sePuedePonerConstructorEnCola = this.hayConstructorEnCola(this.constructoresEnPlaneta[i]);
                if(sePuedePonerConstructorEnCola == true){
                    Constructores[] constructoresEnCola2 = this.constructoresEnCola;
                    this.constructoresEnCola = new Constructores[constructoresEnCola2.length+1];
                    for (int j = 0; j < constructoresEnCola2.length; j++) {
                        this.constructoresEnCola[i] = constructoresEnCola2[j];                        
                    }
                    this.constructoresEnCola[constructoresEnCola2.length] = this.constructoresEnPlaneta[i];
                    break;
                }
            }
        }        
        if(sePuedePonerConstructorEnCola == false){
            System.out.println("No se pudo Construir la nave");
            System.out.println("porque no hay constructores Disponibles de ese tipo");
        }
        return sePuedePonerConstructorEnCola;
    }
    
    /**
    * El metodo sirve para verificar que constructores estan en cola para no ponerlos a construir una nave
    * @param constructorEnPlanetaListo
    */
    public boolean hayConstructorEnCola(Constructores constructorEnPlanetaListo){
        boolean ponerConstructorEnCola = true;
        for (int i = 0; i < this.constructoresEnCola.length; i++) {
            if (this.constructoresEnCola[i] != null) {
                if (this.constructoresEnCola[i] == constructorEnPlanetaListo) {
                    ponerConstructorEnCola = false;
                }
            }
        }
        return ponerConstructorEnCola;
    }
    
    /**
    * El metodo sirve para poner a construir una nave y cuando este lista crearla como objeto y asignarla a su planeta
    * @param tipoNave
    * @param tipoConstructor
    * @param turno
    */
    public void produccionNaves(int tipoNave, int tipoConstructor,int turno){
        if (tipoNave != 0 && tipoConstructor != 0) {
            String navesEnCola2[] = this.navesEnCola;
            this.navesEnCola = new String[navesEnCola2.length+1];
            for (int i = 0; i < navesEnCola2.length; i++) {
                this.navesEnCola[i] = navesEnCola2[i];
            
            }
            int turnoCrearNave = turno + tipoConstructor;                                                                                                                                                                                  
            //DejarNavesEnCola
            this.navesEnCola[navesEnCola2.length] = tipoNave+","+turnoCrearNave;
            
        }
        
        for (int i = 0; i < this.navesEnCola.length; i++) {
            if (this.navesEnCola[i] != null){
                String navesEnColaSeparadas[] = this.navesEnCola[i].split(",");            
                if (Integer.parseInt(navesEnColaSeparadas[1]) == turno) {
                    Naves[] navesEnPlaneta2 = this.navesEnPlaneta;
                    this.navesEnPlaneta = new Naves[navesEnPlaneta2.length+1];
                    for (int j = 0; j < navesEnPlaneta2.length; j++) {
                        this.navesEnPlaneta[j] = navesEnPlaneta2[j];                    
                    }
                    if(Integer.parseInt(navesEnColaSeparadas[0]) == 1){                        
                        navesEnPlaneta[navesEnPlaneta2.length] = new Naboo();
                    }else if(Integer.parseInt(navesEnColaSeparadas[0]) == 2){
                        navesEnPlaneta[navesEnPlaneta2.length] = new XWing();
                    }else if(Integer.parseInt(navesEnColaSeparadas[0]) == 3){
                        navesEnPlaneta[navesEnPlaneta2.length] = new MillenialFalcon();
                    }else if(Integer.parseInt(navesEnColaSeparadas[0]) == 4){
                        navesEnPlaneta[navesEnPlaneta2.length] = new StarDestroyer();
                    }
                    this.cantidadNaves = this.navesEnPlaneta.length;                    
                    this.navesEnCola[i] = null;
                    this.constructoresEnCola[i] = null;
                }
            }
                                    
        }
        
    }
    
    public void compraConstructores(){
        int tipoDeConstructor = 0;
        System.out.println("De que tipo de constructor quieres hacerte");
        System.out.println("Escribelo");
        String NombreTipoContructor = scaner.nextLine();
        int costoDeCompra = 0;
        if(NombreTipoContructor.equalsIgnoreCase("obrero")){
            costoDeCompra = 50;
            tipoDeConstructor = 1;
        }else if(NombreTipoContructor.equalsIgnoreCase("maestro de obra")){
            costoDeCompra = 100;
            tipoDeConstructor = 2;
        }else if(NombreTipoContructor.equalsIgnoreCase("ingeniero")){
            costoDeCompra = 300;
            tipoDeConstructor = 3;
        }
        System.out.println("Cuantos constructores de esta clase deseas?");
        int cantidadConstructores = Integer.parseInt(scaner.nextLine());
        costoDeCompra *= cantidadConstructores;
        if(this.cantidadDinero >= costoDeCompra){
            System.out.println("Este constructor de costara: " +costoDeCompra+" Galactus");
            System.out.println("Quedaras unicamente con: " + (this.cantidadDinero-costoDeCompra) + "Galactus");
            System.out.println("Estas Seguro?  S/N");
            String autorizacionDeCompra= scaner.nextLine();
            if(autorizacionDeCompra.equalsIgnoreCase("S")){
                this.ProduccionConstructores(tipoDeConstructor, cantidadConstructores);
                this.cantidadDinero = this.cantidadDinero - costoDeCompra;
            }
        }else{
            System.out.println("No puedes comprar este constructor");
            System.out.println("Porque no tienes suficiente dinero en este planeta");
        }
        this.cantidadConstructores = this.constructoresEnPlaneta.length;
    }
    
    public void ventaConstructores(){
        int tipoDeConstructor = 0;
        boolean listoParaVender = false;
        
        System.out.println("De que tipo de constructor quieres deshacerte");
        System.out.println("Escribelo");
        String NombreTipoContructor = scaner.nextLine();
        if(NombreTipoContructor.equalsIgnoreCase("obrero")){
            tipoDeConstructor = 3;
        }else if(NombreTipoContructor.equalsIgnoreCase("maestro de obra")){
            tipoDeConstructor = 2;
        }else if(NombreTipoContructor.equalsIgnoreCase("ingeniero")){;
            tipoDeConstructor = 1;
        }        
        for (int i = 0; i < this.constructoresEnPlaneta.length; i++) {
            if(this.constructoresEnPlaneta[i].turnosParaConstruir == tipoDeConstructor){
                 listoParaVender = this.hayConstructorEnCola(this.constructoresEnPlaneta[i]);
                if (listoParaVender == true) {
                    Constructores[] constructoresEnPlaneta2 = this.constructoresEnPlaneta;
                    this.constructoresEnPlaneta = new Constructores[constructoresEnPlaneta2.length - 1];
                    for (int j = 0; j < (constructoresEnPlaneta2.length-1); j++) {
                        if(j != i){
                            this.constructoresEnPlaneta[j] = constructoresEnPlaneta2[j]; 
                        }                                               
                    }
                    break;
                }                
            }            
        }
        if(listoParaVender == false){
            System.out.println("No se ha podido vender");
        }
        this.cantidadConstructores =  this.constructoresEnPlaneta.length;
    }
    
    public void ventaNaves(){        
        System.out.println("De que tipo de constructor quieres deshacerte");
        System.out.println("Escribelo");
        String nombreTipoNave = scaner.nextLine();
        for (int i = 0; i < this.navesEnPlaneta.length; i++) {
            if (this.navesEnPlaneta[i] != null) {
                String tipoNave = this.navesEnPlaneta[i].tipoNave();
                if (tipoNave.equalsIgnoreCase(nombreTipoNave)) {
                    this.cantidadDinero += this.navesEnPlaneta[i].obtetenerCostoProduccion();
                    Naves[] navesEnPlaneta2 = this.navesEnPlaneta;
                        this.navesEnPlaneta = new Naves[navesEnPlaneta2.length - 1];
                        for (int j = 0; j < (navesEnPlaneta2.length-1); j++) {
                            if(j != i){
                                this.navesEnPlaneta[j] = navesEnPlaneta2[j]; 
                            }                                               
                        }
                    break;
                }
            }
                                    
        }
        this.cantidadNaves = this.navesEnPlaneta.length;
    }
    
    /**
    * El metodo sirve para obtener cuantos guerreros de ese tipo(parametro) hay en el planeta
    * @param tipo
    */
    public Integer cantidadGuerrerosPorTipo(String tipo){
        double factorMuerte = 0;
        int cantidadGuerrerosPorTipo = 0;
        if (tipo.equalsIgnoreCase("Mole")) {
            factorMuerte = 1.5;
        }else if (tipo.equalsIgnoreCase("Nemo")) {
            factorMuerte = 1.6;
        }else if (tipo.equalsIgnoreCase("Magma")) {
            factorMuerte = 1.75;
        }else if (tipo.equalsIgnoreCase("Groot")) {
            factorMuerte = 1.85;
        }else if (tipo.equalsIgnoreCase("Fision Guy")) {
            factorMuerte = 1.95;
        }
        for (int i = 0; i < guerrerosDelPlaneta.length; i++) {
            if(guerrerosDelPlaneta[i] != null){
                if (guerrerosDelPlaneta[i].factorMuerte == factorMuerte) {
                    cantidadGuerrerosPorTipo += 1;
                }            
            }
        }                        
        return cantidadGuerrerosPorTipo;
    }
    
    /**
    * El metodo sirve para verificar que una nave no este en cola para enviarla a una flota
    * @param tipoNave
    * @return Naves
    */
    public  Naves navesNoEnColaPorTipo(String tipoNave){
        boolean naveListaParaViajar = true;
        for (int i = 0; i < this.navesEnPlaneta.length; i++) {
            if(this.navesEnPlaneta[i] != null){
                if (tipoNave.equalsIgnoreCase(this.navesEnPlaneta[i].tipoNave())) {
                    naveListaParaViajar = true;
                    for (int j = 0; j < this.navesViajando.length; j++) {
                        if (this.navesViajando[j] != null) {
                            if (this.navesViajando[j] == this.navesEnPlaneta[i]) {
                                naveListaParaViajar = false;
                            }   
                        }                                        
                    }
                    if(naveListaParaViajar == true){
                        return this.navesEnPlaneta[i];                
                    }                
                }
            }
            
        }
        
        return null;
    }
    
    /**
    * El metodo sirve para asignar guerreros de un solo tipo a una flota enviando el arreglo con los guerreros
    * @param tipo
    * @param cantidadGuerreros
    * @return Naves
    */
    public Guerreros[] guerrerosEnNave(String tipo, int cantidadGuerreros){
        Guerreros[] guerrerosEnNave = new Guerreros[cantidadGuerreros];
        double factorMuerte = 0;
        int contadorGuerrerosSubidos = 0;
        if (tipo.equalsIgnoreCase("Mole")) {
            factorMuerte = 1.5;
        }else if (tipo.equalsIgnoreCase("Nemo")) {
            factorMuerte = 1.6;
        }else if (tipo.equalsIgnoreCase("Magma")) {
            factorMuerte = 1.75;
        }else if (tipo.equalsIgnoreCase("Groot")) {
            factorMuerte = 1.85;
        }else if (tipo.equalsIgnoreCase("Fision Guy")) {
            factorMuerte = 1.95;
        }
        
        for (int i = 0; i < guerrerosDelPlaneta.length; i++) {
            if (guerrerosDelPlaneta[i].factorMuerte == factorMuerte) {                
                guerrerosEnNave[contadorGuerrerosSubidos] = guerrerosDelPlaneta[i];
                this.guerrerosDelPlaneta[i] = null;
                contadorGuerrerosSubidos +=1;
                if (contadorGuerrerosSubidos == cantidadGuerreros) {
                    break;
                }
            }            
        }
        Guerreros[] guerrerosDelPlaneta2 = this.guerrerosDelPlaneta;
        this.guerrerosDelPlaneta = new Guerreros[guerrerosDelPlaneta2.length - contadorGuerrerosSubidos];
        for (int i = 0; i < (guerrerosDelPlaneta2.length-contadorGuerrerosSubidos); i++) {
            if(guerrerosDelPlaneta2[i] != null){
                this.guerrerosDelPlaneta[i] = guerrerosDelPlaneta2[i];
            }                        
        }
        
        return guerrerosEnNave;
    }
    
    /**
    * El metodo sirve para asignar guerreros al planeta cuando una flota aliada arribe
    * @param guerrerosEntrar
    */
    public void agregrarGuerreros(Guerreros[] guerrerosEntrar){
        Guerreros[] guerrerosDelPlaneta2 = this.guerrerosDelPlaneta;
        int contadorGuerrerosEntrar = 0;
        this.guerrerosDelPlaneta = new Guerreros[guerrerosDelPlaneta2.length + guerrerosEntrar.length];
        for (int i = 0; i < (guerrerosDelPlaneta2.length); i++) {
            this.guerrerosDelPlaneta[i] = guerrerosDelPlaneta2[i];            
        }
        for (int i = guerrerosDelPlaneta2.length; i < (guerrerosDelPlaneta2.length + guerrerosEntrar.length); i++) {
            this.guerrerosDelPlaneta[i] = guerrerosEntrar[contadorGuerrerosEntrar];            
            contadorGuerrerosEntrar += 1; 
        }
    }
    
    /**
    * El metodo sirve para asignar una nave a la flota por lo tanto no puede ser usada mientra esta en ella
    * @param naveListaParaViajar
    */
    public void agregrarNaveAlViaje(Naves naveListaParaViajar){
        for (int i = 0; i < this.navesEnPlaneta.length; i++) {
            if (this.navesEnPlaneta[i] != null) {
                if (naveListaParaViajar == this.navesEnPlaneta[i]) {                
                    Naves[] navesViajando2 = this.navesViajando;
                    this.navesViajando = new Naves[navesViajando2.length];
                    for (int j = 0; j < navesViajando2.length; j++) {
                        this.navesViajando[j] = navesViajando2[j];                    
                    }
                    this.navesViajando[navesViajando2.length-1] = this.navesEnPlaneta[i];
                    this.navesEnPlaneta[i] = null;
                    break;
                }  
            }                       
        }
    }
    
    /**
    * El metodo sirve para que cuando una nave llegue a su destino regrese al planeta para volver a usarse
    * @param naveViajando
    */
    public void regresarNaveDelViaje(Naves naveViajando){
        for (int i = 0; i < this.navesViajando.length; i++) {
            if(this.navesViajando[i] != null){
                if (naveViajando == this.navesViajando[i]) {                    
                    Naves[] navesEnPlaneta2 = this.navesEnPlaneta;
                    this.navesEnPlaneta = new Naves[navesEnPlaneta2.length];
                    for (int j = 0; j < navesEnPlaneta2.length; j++) {
                        this.navesEnPlaneta[j] = navesEnPlaneta2[j];                    
                    }
                    this.navesEnPlaneta[navesEnPlaneta2.length-1] = this.navesViajando[i];
                    this.navesViajando[i] = null;                                        
                    break;   
                }    
            }                        
        }
        
    }

    /**
    * El metodo asigna una cantidad de guerreros al planeta
    * @param guerrerosDelPlaneta
    */
    public void setGuerrerosDelPlaneta(Guerreros[] guerrerosDelPlaneta) {
        this.guerrerosDelPlaneta = guerrerosDelPlaneta;
        this.cantidadGuerreros = this.guerrerosDelPlaneta.length;
    }
    
    /**
    * El metodo se usa para que los planetas produzcan la cantidad de dinero segun su capacidad 
    */
    public void produccionDinero(){
        
    }
    
    /**
    * El metodo devuelve el tipo del planeta que es
    * @return tipo del Planeta
    */
    public String tipoPlaneta(){
        return "";
    }
    
    public Guerreros[] obtenerGuerrerosDelPlaneta() {
        return guerrerosDelPlaneta;
    }            
    public String obtenerNombrePlaneta(){
        return this.nombrePlaneta;
    }
    public int obtenerPorcentajeMuerte(){
        return this.porcentajeMuerte;
    }
    public int obtenerCantidadDinero(){
        return this.cantidadDinero;
    }
    public int obtenerCantidadConstructores(){
        return this.cantidadConstructores;
    }
    public int obtenerCantidadNaves(){
        return this.cantidadNaves;        
    }
    public int obtenerCantidadGuerreros(){
        return this.cantidadGuerreros;        
    }
    public int obtenerProbabilidadDistribucion(){
        return this.probabilidadDistribucion;        
    }
    public int obtenerPosicionX(){
        return this.posicionX;        
    }
    public int obtenerPosicionY(){
        return this.posicionY;        
    }
    
    
    
    
}
