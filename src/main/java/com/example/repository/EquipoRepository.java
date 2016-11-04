package com.example.repository;

import com.example.domain.Equipo;
import com.example.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Alan on 21/10/2016.
 */
public interface EquipoRepository extends JpaRepository<Equipo, Long> {

    List<Equipo>findByLocalidad(String localidad);
   /* List<Jugador>findByEquipo(String equipo);  */
   /*@Query("SELECT jugador FROM Jugador jugador, Equipo equipo WHERE equipo.id = jugador.equipo AND equipo.nombre = :nombre")
    List<Jugador>findByEquipo(@Param("nombre")String nombre);

    @Query("SELECT jugador FROM Jugador jugador, Equipo equipo WHERE equipo.id = jugador.equipo AND equipo.nombre = :nombre AND jugador.posicion = :posicion")
    List<Jugador>findByEquipoAndPosicion(@Param("nombre")String nombre, String posicion); */

    @Query("SELECT jugador FROM Jugador jugador WHERE jugador.equipo.nombre = :nombre order by jugador.canasto desc")
    List<Jugador> findByJugadorEquipoCanasto(@Param("nombre") String nombre);
    //@Query("SELECT jugador from Jugador jugador WHERE jugador.equipo = :equipo AND jugador.numcana IN (SELECT MAX(jugador.numcana) FROM Jugador jugador WHERE jugador.equipo = :equipo)")
   // List<Jugador> findByEquipoAndCanasto(@Param("equipo") Equipo equipo);
    //List<Jugador>findByEquipoAndCanasto(@Param("nombre")String nombre);
   /* List<Jugador>findByEquipoAndPosicion(String equipo, String posicion);
    List<Jugador>findByEquipoWhereCanastoMax(String equipo); */

}
