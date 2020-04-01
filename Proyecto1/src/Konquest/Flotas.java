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
public class Flotas {
    private Naves naveEnFlota;
    private Guerreros[] guerrerosEnNave;
    private int turnoDeLleguada;
    private Planetas planetaLlegada;
    private Planetas planetaSalida;

    public Flotas(Naves naveEnFlota, Guerreros[] guerrerosEnNave, int turnoDeLleguada, Planetas planetaLlegada, Planetas planetaSalida) {
        this.naveEnFlota = naveEnFlota;
        this.guerrerosEnNave = guerrerosEnNave;
        this.turnoDeLleguada = turnoDeLleguada;
        this.planetaLlegada = planetaLlegada;
        this.planetaSalida = planetaSalida;
    }
    
    public void imprimirFlota(){
        System.out.println("El tipo denave enviada es: " + this.naveEnFlota.tipoNave());
        System.out.println("La cantidad de guerreros enviada es: " + this.guerrerosEnNave.length);
        System.out.println("Llega en el turno: " + this.turnoDeLleguada);
        System.out.println("Salio del planeta: "+ this.planetaSalida.obtenerNombrePlaneta());
        System.out.println("Entra al planeta: "+ this.planetaLlegada.obtenerNombrePlaneta());
    }

    public Naves obtenerNaveEnFlota() {
        return naveEnFlota;
    }    

    public Guerreros[] obtenerGuerrerosEnNave() {
        return guerrerosEnNave;
    }

    public int obtenerTurnoDeLleguada() {
        return turnoDeLleguada;
    }

    public Planetas obtenerPlanetaLlegada() {
        return planetaLlegada;
    }

    public Planetas obtenerPlanetaSalida() {
        return planetaSalida;
    }

    public void setNaveEnFlota(Naves naveEnFlota) {
        this.naveEnFlota = naveEnFlota;
    }
    
    
}
