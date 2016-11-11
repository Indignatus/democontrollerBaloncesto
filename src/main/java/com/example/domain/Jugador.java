package com.example.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Alan on 10/10/2016.
 */
@Entity
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nombre;
    @JsonIgnore
    private LocalDate nacimiento;
    private int canasto;
    private int asisto;
    private int reboto;
    @Enumerated(EnumType.STRING)
    private Posicion posicion;
    @ManyToOne
    private Equipo equipo;

    public Jugador(String nombre, LocalDate nacimiento, int canasto, int asisto, int reboto, Posicion posicion) {
        this.nombre = nombre;
        this.nacimiento = nacimiento;
        this.canasto = canasto;
        this.asisto = asisto;
        this.reboto = reboto;
        this.posicion = posicion;
    }

    public Jugador(){}

    public void setId(long id) { this.id = id;}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNacimiento(LocalDate nacimiento) {
        this.nacimiento = nacimiento;
    }

    public void setCanasto(int canasto) {
        this.canasto = canasto;
    }

    public void setAsisto(int asisto) {
        this.asisto = asisto;
    }

    public void setReboto(int reboto) {
        this.reboto = reboto;
    }

    public void setPosicion(Posicion posicion) {this.posicion = posicion;}

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public LocalDate getNacimiento() {
        return nacimiento;
    }

    public int getCanasto() {
        return canasto;
    }

    public int getAsisto() {
        return asisto;
    }

    public int getReboto() {
        return reboto;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Equipo getEquipo() {
        return equipo;
    }

    public void setEquipo(Equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "Jugador{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", canasto=" + canasto +
                ", asisto=" + asisto +
                ", reboto=" + reboto +
                ", posicion='" + posicion + '\'' +
                '}';
    }
}
