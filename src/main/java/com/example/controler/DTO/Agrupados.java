package com.example.controler.DTO;

import com.example.domain.Jugador;

/**
 * Created by Alan on 07/11/2016.
 */
public class Agrupados {

    private String posicion;
    private Jugador jugador;

    public Agrupados() {
    }

    public Agrupados(String posicion, Jugador jugador) {
        this.posicion = posicion;
        this.jugador = jugador;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }
}
