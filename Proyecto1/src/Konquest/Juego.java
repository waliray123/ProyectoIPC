/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Konquest;

import Jugadores.Jugador;
import java.util.Scanner;

/**
 *
 * @author user-ubunto
 */
public class Juego {

    private Flotas[] flotasJugador1 = new Flotas[1];
    private Flotas[] flotasJugador2 = new Flotas[1];

    Scanner scaner = new Scanner(System.in);

    public boolean TurnosPlanetaEnJugador(int contadorTurnos, Jugador jugador1, Jugador jugador2, Planetas planetaBuscar) {
        boolean estaPlanetaEnJugador = false;
        if (contadorTurnos == 1) {
            estaPlanetaEnJugador = jugador1.EstaPlanetaEnLista(planetaBuscar);
        } else if (contadorTurnos == 2) {
            estaPlanetaEnJugador = jugador2.EstaPlanetaEnLista(planetaBuscar);
        }
        return estaPlanetaEnJugador;
    }

    public boolean sePuedeCargarLaNave(int cantidadGuerreros, String tipoGuerrero, Naves naveParaViaje) {
        int espacioOcupadoEnNave = 0;
        if (tipoGuerrero.equalsIgnoreCase("Mole")) {
            espacioOcupadoEnNave = 1;
        } else if (tipoGuerrero.equalsIgnoreCase("Nemo")) {
            espacioOcupadoEnNave = 1;
        } else if (tipoGuerrero.equalsIgnoreCase("Magma")) {
            espacioOcupadoEnNave = 2;
        } else if (tipoGuerrero.equalsIgnoreCase("Groot")) {
            espacioOcupadoEnNave = 3;
        } else if (tipoGuerrero.equalsIgnoreCase("Fision Guy")) {
            espacioOcupadoEnNave = 4;
        }
        espacioOcupadoEnNave *= cantidadGuerreros;
        if (espacioOcupadoEnNave <= naveParaViaje.obtetenerEspacioTransporte()) {
            return true;
        }
        return false;
    }

    public void revisarFlotas(int turno, Jugador jugador1, Jugador jugador2) {
        for (int i = 0; i < this.flotasJugador1.length; i++) {
            try {
                if (turno <= flotasJugador1[i].obtenerTurnoDeLleguada()) {
                    boolean estaPlanetaEnJugador1 = jugador1.EstaPlanetaEnLista(this.flotasJugador1[i].obtenerPlanetaLlegada());
                    if (estaPlanetaEnJugador1 == true) {
                        this.arriboGuerreros(flotasJugador1[i]);
                    } else {
                        this.conflicto(flotasJugador1[i], jugador2, jugador1);
                    }
                }
            } catch (NullPointerException e) {

            }
        }
        for (int i = 0; i < this.flotasJugador2.length; i++) {
            try {
                if (turno <= flotasJugador2[i].obtenerTurnoDeLleguada()) {
                    boolean estaPlanetaEnJugador2 = jugador2.EstaPlanetaEnLista(this.flotasJugador2[i].obtenerPlanetaLlegada());
                    if (estaPlanetaEnJugador2 == true) {
                        this.arriboGuerreros(flotasJugador2[i]);
                    } else {
                        this.conflicto(flotasJugador2[i], jugador1, jugador2);
                    }
                }
            } catch (NullPointerException e) {

            }
        }
    }

    public void conflicto(Flotas flotaIngreso, Jugador jugadorDefensa, Jugador jugadorAtaque) {
        Planetas planetaDefensa = flotaIngreso.obtenerPlanetaLlegada();
        Guerreros[] guerrerosDefensa = planetaDefensa.obtenerGuerrerosDelPlaneta();
        Guerreros[] guerrerosAtaque = flotaIngreso.obtenerGuerrerosEnNave();
        boolean conflictoTerminado = false;
        boolean defensaExitosa = false;
        int contadorGuerrerosDefensa = 0;
        int contadorGuerrerosAtaque = 0;
        while (conflictoTerminado == false) {
            if (guerrerosDefensa[0].valorMuerte >= guerrerosAtaque[0].valorMuerte) {
                Guerreros[] guerrerosAtaque2 = guerrerosAtaque;
                guerrerosAtaque = new Guerreros[guerrerosAtaque2.length - 1];
                for (int i = 1; i < guerrerosAtaque2.length; i++) {
                    guerrerosAtaque[i-1] = guerrerosAtaque2[i];
                }
                contadorGuerrerosAtaque = 0;
                contadorGuerrerosDefensa += 1;
            } else {
                Guerreros[] guerrerosDefensa2 = guerrerosDefensa;
                guerrerosDefensa = new Guerreros[guerrerosDefensa2.length - 1];
                for (int i = 1; i < guerrerosDefensa2.length; i++) {
                    guerrerosDefensa[i-1] = guerrerosDefensa2[i];
                }
                contadorGuerrerosDefensa = 0;
                contadorGuerrerosAtaque += 1;
            }
            if (contadorGuerrerosDefensa == 2) {
                contadorGuerrerosDefensa = 0;
                Guerreros[] guerrerosDefensa2 = guerrerosDefensa;
                guerrerosDefensa = new Guerreros[guerrerosDefensa2.length - 1];
                for (int i = 1; i < guerrerosDefensa2.length; i++) {
                    guerrerosDefensa[i-1] = guerrerosDefensa2[i];
                }
            } else if (contadorGuerrerosAtaque == 2) {
                Guerreros[] guerrerosAtaque2 = guerrerosAtaque;
                guerrerosAtaque = new Guerreros[guerrerosAtaque2.length - 1];
                for (int i = 1; i < guerrerosAtaque2.length; i++) {
                    guerrerosAtaque[i-1] = guerrerosAtaque2[i];
                }
                contadorGuerrerosAtaque = 0;
            }
            if (guerrerosDefensa.length == 1) {
                defensaExitosa = false;
                conflictoTerminado = true;
            } else if (guerrerosAtaque.length == 1) {
                defensaExitosa = true;
                conflictoTerminado = true;
            }
        }
        if (defensaExitosa == true) {
            planetaDefensa.setGuerrerosDelPlaneta(guerrerosDefensa);
            System.out.println("Defensa Exitosa");
            System.out.println("Planeta: " + planetaDefensa.obtenerNombrePlaneta() + "Sigue siendo del mismo dueño");
        } else if (defensaExitosa == false) {
            planetaDefensa.setGuerrerosDelPlaneta(guerrerosAtaque);
            boolean estaPlanetaEnJugadorDefensa = jugadorDefensa.EstaPlanetaEnLista(planetaDefensa);
            if (estaPlanetaEnJugadorDefensa == true) {
                jugadorDefensa.quitarPlaneta(planetaDefensa);

            }
            jugadorAtaque.AsignarPlaneta(planetaDefensa);
            System.out.println("Ataque exitoso");
            System.out.println("Planeta: " + planetaDefensa.obtenerNombrePlaneta() + "Ahora es de: " + jugadorAtaque.getNombreJugador());
        }        
    }

    public void arriboGuerreros(Flotas flotaIngreso) {
        Planetas planetaEntrada = flotaIngreso.obtenerPlanetaLlegada();
        Planetas planetaSalida = flotaIngreso.obtenerPlanetaSalida();
        planetaEntrada.agregrarGuerreros(flotaIngreso.obtenerGuerrerosEnNave());

        planetaSalida.regresarNaveDelViaje(flotaIngreso.obtenerNaveEnFlota());

        flotaIngreso.setNaveEnFlota(null);
    }

    public void Partida(Jugador jugador1, Jugador jugador2, Planetas[][] tablero, Mapa mapaJuego, int cantidadColumnas, int cantidadFilas) {
        boolean Ganar = false;
        int contadorTurnosDeJugador = 1;
        int contadorTurnosJuego = 1;
        flotasJugador1 = new Flotas[1];
        flotasJugador2 = new Flotas[1];

        System.out.println("Ahora empieza la verdadera Batalla");
        System.out.println("");
        while (Ganar == false) {
            System.out.println("Turno numero: " + contadorTurnosJuego);
            System.out.println("Turno de jugador: " + contadorTurnosDeJugador);
            System.out.println("Que deseas hacer?");
            boolean opcionCorrecta = false;
            boolean terminarTurno = false;
            int opcionIngresada = 0;
            while (terminarTurno == false) {
                while (opcionCorrecta == false) {
                    System.out.println("Ingresa el numero de la opcion a realizar");
                    System.out.println("1. Medir Distacia");
                    System.out.println("2. Ver planeta");
                    System.out.println("3. Consulta flota");
                    System.out.println("4. Envio Flota");
                    System.out.println("5. Construir Nave");
                    System.out.println("6. Ir a la Tienda");
                    System.out.println("7. Terminar Turno");
                    try {
                        opcionIngresada = Integer.parseInt(scaner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Ingrese una opcion correcta");
                    }

                    if (opcionIngresada >= 1 && opcionIngresada <= 7) {
                        opcionCorrecta = true;
                    } else {
                        System.out.println("Ingrese un numero de una opcion existente");
                        System.out.println("");
                    }
                }
                if (opcionIngresada == 1) {
                    int posicionXPlaneta1 = 0;
                    int posicionYPlaneta1 = 0;
                    int posicionXPlaneta2 = 0;
                    int posicionYPlaneta2 = 0;
                    boolean coordenadasCorrectas = false;
                    while (coordenadasCorrectas == false) {
                        System.out.println("Ingrese las coordenadas de los planetas a medir");
                        String coordenadas = scaner.nextLine();
                        if (coordenadas.length() <= 7) {

                            String[] coordenadasSeparadas = coordenadas.split(",");
                            coordenadasSeparadas[0] = coordenadasSeparadas[0].toUpperCase();
                            coordenadasSeparadas[1] = coordenadasSeparadas[1].toUpperCase();
                            posicionXPlaneta1 = (coordenadasSeparadas[0].charAt(0)) - 65;
                            coordenadasSeparadas[0] = coordenadasSeparadas[0].substring(1);
                            posicionYPlaneta1 = Integer.parseInt(coordenadasSeparadas[0]) - 1;
                            posicionXPlaneta2 = (coordenadasSeparadas[1].charAt(0)) - 65;
                            coordenadasSeparadas[1] = coordenadasSeparadas[1].substring(1);
                            posicionYPlaneta2 = Integer.parseInt(coordenadasSeparadas[1]) - 1;

                            if (posicionXPlaneta1 <= cantidadColumnas && posicionXPlaneta2 <= cantidadColumnas
                                    && posicionYPlaneta1 <= cantidadFilas && posicionYPlaneta2 <= cantidadFilas) {
                                if (tablero[posicionYPlaneta1][posicionXPlaneta1] != null
                                        && tablero[posicionYPlaneta2][posicionXPlaneta2] != null) {

                                    double distancia = Math.sqrt(Math.pow(posicionXPlaneta1 - posicionXPlaneta2, 2)
                                            + Math.pow(posicionYPlaneta1 - posicionYPlaneta2, 2));
                                    System.out.println("La distancia es: " + distancia);
                                    coordenadasCorrectas = true;
                                } else {
                                    System.out.println("Alguno de los planetas ingresados no existe");
                                }
                            }
                        } else {
                            System.out.println("Ingrese las coordenadas correctas");
                        }
                    }
                } else if (opcionIngresada == 2) {
                    boolean coordenadasCorrectas = false;

                    int posicionX = 0;
                    int posicionY = 0;
                    while (coordenadasCorrectas == false) {
                        boolean estaPlanetaEnJugador = true;
                        System.out.println("Ingrese las coordenadas del planeta");
                        String coordenadas = scaner.nextLine();
                        if (coordenadas.length() >= 2) {
                            coordenadas = coordenadas.toUpperCase();
                            posicionX = (coordenadas.charAt(0)) - 65;
                            coordenadas = coordenadas.substring(1);
                            posicionY = Integer.parseInt(coordenadas) - 1;
                            if (posicionX <= cantidadColumnas && posicionY <= cantidadFilas) {
                                if (tablero[posicionY][posicionX] != null) {
                                    estaPlanetaEnJugador = this.TurnosPlanetaEnJugador(contadorTurnosDeJugador, jugador2, jugador1, tablero[posicionY][posicionX]);
                                    if (estaPlanetaEnJugador == true) {
                                        System.out.println("No estas autorizado a ver el planeta");
                                        System.out.println("porque este planeta no te Pertenece");
                                    } else {
                                        tablero[posicionY][posicionX].imprimirDatos();
                                    }
                                    coordenadasCorrectas = true;
                                } else {
                                    System.out.println("El planeta en esas coordenadas no existe");
                                }
                            }
                        } else {
                            System.out.println("Ingrese las coordenadas correctas");
                        }
                    }
                } else if (opcionIngresada == 3) {
                    //Consulta Flota
                    if (contadorTurnosDeJugador == 1) {
                        for (int i = 0; i < this.flotasJugador1.length; i++) {
                            if (this.flotasJugador1[i] != null) {
                                if (this.flotasJugador1[i].obtenerNaveEnFlota() != null) {
                                    this.flotasJugador1[i].imprimirFlota();
                                }
                            }
                        }
                    } else if (contadorTurnosDeJugador == 2) {                        
                        for (int i = 0; i < this.flotasJugador2.length; i++) {
                            if (this.flotasJugador2[i] != null) {
                                if (this.flotasJugador2[i].obtenerNaveEnFlota() != null) {
                                    this.flotasJugador2[i].imprimirFlota();
                                }
                            }
                        }
                    }
                } else if (opcionIngresada == 4) {
                    //Envio Flota
                    //A1,35,Mole,Naboo N-1,G4
                    int posicionXPlaneta1 = 0;
                    int posicionYPlaneta1 = 0;
                    int posicionXPlaneta2 = 0;
                    int posicionYPlaneta2 = 0;
                    int cantidadGuerreros = 0;
                    String tipoGuerrero = "";
                    String tipoNave = "";

                    System.out.println("Envio de flota");
                    System.out.println("Escribe las coordenadas de envio de flota");
                    String coordenadas = scaner.nextLine();
                    coordenadas = coordenadas.toUpperCase();

                    String[] coordenadasSeparadas = coordenadas.split(",");

                    posicionXPlaneta1 = (coordenadasSeparadas[0].charAt(0)) - 65;
                    coordenadasSeparadas[0] = coordenadasSeparadas[0].substring(1);
                    posicionYPlaneta1 = Integer.parseInt(coordenadasSeparadas[0]) - 1;
                    posicionXPlaneta2 = (coordenadasSeparadas[4].charAt(0)) - 65;
                    coordenadasSeparadas[4] = coordenadasSeparadas[4].substring(1);
                    posicionYPlaneta2 = Integer.parseInt(coordenadasSeparadas[4]) - 1;
                    cantidadGuerreros = Integer.parseInt(coordenadasSeparadas[1]);
                    tipoGuerrero = coordenadasSeparadas[2];
                    tipoNave = coordenadasSeparadas[3];

                    if (posicionXPlaneta1 < cantidadColumnas && posicionXPlaneta2 < cantidadColumnas
                            && posicionYPlaneta1 < cantidadFilas && posicionYPlaneta2 < cantidadFilas) {
                        if (tablero[posicionYPlaneta1][posicionXPlaneta1] != null
                                && tablero[posicionYPlaneta2][posicionXPlaneta2] != null) {
                            if (tablero[posicionYPlaneta1][posicionXPlaneta1].cantidadGuerrerosPorTipo(tipoGuerrero) >= cantidadGuerreros) {
                                Naves naveListaParaViajar = tablero[posicionYPlaneta1][posicionXPlaneta1].navesNoEnColaPorTipo(tipoNave);
                                if (naveListaParaViajar != null) {
                                    if (this.sePuedeCargarLaNave(cantidadGuerreros, tipoGuerrero, naveListaParaViajar) == true) {
                                        double distancia = Math.sqrt(Math.pow(posicionXPlaneta1 - posicionXPlaneta2, 2)
                                                + Math.pow(posicionYPlaneta1 - posicionYPlaneta2, 2));
                                        int turnoDeLlegada = contadorTurnosJuego + (int) ((int) distancia / naveListaParaViajar.obtetenerVelocidad());
                                        if (contadorTurnosDeJugador == 1) {
                                            Flotas[] flotasJugador = this.flotasJugador1;
                                            this.flotasJugador1 = new Flotas[flotasJugador.length];
                                            for (int i = 0; i < flotasJugador.length; i++) {
                                                this.flotasJugador1[i] = flotasJugador[i];
                                            }

                                            this.flotasJugador1[flotasJugador.length - 1] = new Flotas(naveListaParaViajar,
                                                    tablero[posicionYPlaneta1][posicionXPlaneta1].guerrerosEnNave(tipoGuerrero, cantidadGuerreros),
                                                    turnoDeLlegada, tablero[posicionYPlaneta2][posicionXPlaneta2],
                                                    tablero[posicionYPlaneta1][posicionXPlaneta1]);
                                            tablero[posicionYPlaneta1][posicionXPlaneta1].agregrarNaveAlViaje(naveListaParaViajar);
                                        } else if (contadorTurnosDeJugador == 2) {
                                            Flotas[] flotasJugador = this.flotasJugador2;
                                            this.flotasJugador2 = new Flotas[flotasJugador.length];
                                            for (int i = 0; i < flotasJugador.length; i++) {
                                                this.flotasJugador2[i] = flotasJugador[i];
                                            }
                                            this.flotasJugador2[flotasJugador.length - 1] = new Flotas(naveListaParaViajar,
                                                    tablero[posicionYPlaneta1][posicionXPlaneta1].guerrerosEnNave(tipoGuerrero, cantidadGuerreros),
                                                    turnoDeLlegada, tablero[posicionYPlaneta2][posicionXPlaneta2],
                                                    tablero[posicionYPlaneta1][posicionXPlaneta1]);
                                            tablero[posicionYPlaneta1][posicionXPlaneta1].agregrarNaveAlViaje(naveListaParaViajar);
                                        }
                                    }
                                } else {
                                    System.out.println("No hay naves de ese tipo Diponibles");
                                }
                            } else {
                                System.out.println("No tienes los suficientes Guerreros");
                            }
                        } else {
                            System.out.println("Alguno de los planetas ingresados no existe");
                        }
                    }

                } else if (opcionIngresada == 5) {
                    // Producir Nave
                    System.out.println("Ingrese el numero del tipo de nave que quiere producir");
                    System.out.println("El precio se encuentra a su derecha de cada una");
                    System.out.println("1. Naboo-N1 | 40 galactus");
                    System.out.println("2. Xwing | 50 galactus");
                    System.out.println("3. Millenial Falcon | 70 galactus");
                    System.out.println("4. Star Destroyer | 100 galactus");
                    int tipoNave = Integer.parseInt(scaner.nextLine());
                    int posicionX = 0;
                    int posicionY = 0;
                    int precioNave = 0;
                    if (tipoNave >= 1 && tipoNave <= 4) {
                        if (tipoNave == 1) {
                            precioNave = 40;
                        } else if (tipoNave == 2) {
                            precioNave = 40;
                        } else if (tipoNave == 3) {
                            precioNave = 40;
                        } else if (tipoNave == 4) {
                            precioNave = 40;
                        }
                        System.out.println("Ingrese la coordenada del Planeta y el tipo de constructor ");
                        String coordenadaYConstructor = scaner.nextLine();
                        coordenadaYConstructor = coordenadaYConstructor.toUpperCase();
                        String[] coordenadaYConstructorSeparadas = coordenadaYConstructor.split(",");
                        posicionX = (coordenadaYConstructorSeparadas[0].charAt(0)) - 65;
                        coordenadaYConstructorSeparadas[0] = coordenadaYConstructorSeparadas[0].substring(1);
                        posicionY = Integer.parseInt(coordenadaYConstructorSeparadas[0]) - 1;
                        boolean estaPlanetaEnJugador = false;
                        if (posicionX <= cantidadColumnas && posicionY <= cantidadFilas) {
                            if (tablero[posicionY][posicionX] != null) {
                                estaPlanetaEnJugador = this.TurnosPlanetaEnJugador(contadorTurnosDeJugador, jugador1, jugador2, tablero[posicionY][posicionX]);
                                if (estaPlanetaEnJugador == false) {
                                    System.out.println("No estas autorizado a usar este planeta");
                                    System.out.println("porque este planeta no te Pertenece");
                                } else {
                                    // El planeta esta en Jugador                                    
                                    int cantidadDinero = tablero[posicionY][posicionX].obtenerCantidadDinero();
                                    if (precioNave < cantidadDinero) {
                                        String constructor = coordenadaYConstructorSeparadas[1];
                                        int tipoConstructor = 0;
                                        if (constructor.equals("OBRERO")) {
                                            tipoConstructor = 3;
                                        } else if (constructor.equals("MAESTRO DE OBRA")) {
                                            tipoConstructor = 2;
                                        } else if (constructor.equals("INGENIERO")) {
                                            tipoConstructor = 1;
                                        }
                                        boolean producirNave = tablero[posicionY][posicionX].hayTipoConstructorNoEnCola(tipoConstructor);
                                        if (producirNave == true) {
                                            tablero[posicionY][posicionX].produccionNaves(tipoNave, tipoConstructor, contadorTurnosJuego);
                                            tablero[posicionY][posicionX].setCantidadDinero(cantidadDinero - precioNave);
                                            System.out.println("Se esta produciendo la nave");
                                        }
                                    } else {
                                        System.out.println("El planeta no tiene suficiente Dinero para crear la nave");
                                    }
                                }
                            } else {
                                System.out.println("El planeta en esas coordenadas no existe");
                            }
                        }

                    } else {
                        System.out.println("Ingrese un numero del tipo de nave");
                    }
                } else if (opcionIngresada == 6) {
                    //tiendita
                    System.out.println("Bienvenido a la tienda espacial");
                    System.out.println("Que deseas hacer aqui?");
                    System.out.println("1. Comprar Constructores");
                    System.out.println("2. Vender Constructores");
                    System.out.println("3. Vender Naves");
                    int opcionTienda = Integer.parseInt(scaner.nextLine());
                    if (opcionTienda >= 1 && opcionTienda <= 3) {
                        System.out.println("En que planeta Deseas realizar esta opcion");
                        System.out.println("Ingrese las coordenadas del planeta");
                        String coordenadas = scaner.nextLine();
                        int posicionX = 0;
                        int posicionY = 0;
                        if (coordenadas.length() >= 2) {
                            coordenadas = coordenadas.toUpperCase();
                            posicionX = (coordenadas.charAt(0)) - 65;
                            coordenadas = coordenadas.substring(1);
                            posicionY = Integer.parseInt(coordenadas) - 1;
                            if (posicionX <= cantidadColumnas && posicionY <= cantidadFilas) {
                                boolean estaPlanetaEnJugador = false;
                                estaPlanetaEnJugador = this.TurnosPlanetaEnJugador(contadorTurnosDeJugador, jugador1, jugador2, tablero[posicionY][posicionX]);
                                    if (estaPlanetaEnJugador == false) {
                                        System.out.println("No estas autorizado a usar este planeta");
                                        System.out.println("porque este planeta no te Pertenece");
                                    } else {
                                    if (opcionTienda == 1) {
                                    tablero[posicionY][posicionX].compraConstructores();
                                    } else if (opcionTienda == 2) {
                                    tablero[posicionY][posicionX].ventaConstructores();
                                    } else if (opcionTienda == 3) {
                                    tablero[posicionY][posicionX].ventaNaves();
                                    }
                                }
                            }
                        }

                    }

                } else if (opcionIngresada == 7) {
                    terminarTurno = true;
                }

                opcionIngresada = 0;
                opcionCorrecta = false;
            }
            terminarTurno = false;
            if (contadorTurnosDeJugador == 1) {
                contadorTurnosDeJugador += 1;
            } else if (contadorTurnosDeJugador == 2) {
                contadorTurnosDeJugador = 1;
                contadorTurnosJuego += 1;
                this.revisarFlotas(contadorTurnosJuego, jugador1, jugador2);                
                mapaJuego.DibujarMapa();                
                for (int i = 0; i < cantidadFilas; i++) {
                    for (int j = 0; j < cantidadColumnas; j++) {
                        if (tablero[i][j] != null) {
                            tablero[i][j].ProduccionGuerreros(0);
                            tablero[i][j].produccionNaves(0, 0, contadorTurnosJuego);
                            tablero[i][j].produccionDinero();
                        }
                    }
                }
                Ganar = this.verificarTerminoJuego(jugador1, jugador2);
            }
        }
    }
    public boolean verificarTerminoJuego(Jugador jugador1, Jugador jugador2){
        if(jugador1.obtenerCantidadPlanetasConquistados() <=0){
            System.out.println("Ha ganado el jugador 2");
            return true;
        }else if(jugador2.obtenerCantidadPlanetasConquistados() <=0){
            System.out.println("Ha ganado el jugador 1");
            return true;
        }
        return false;
    }
}
