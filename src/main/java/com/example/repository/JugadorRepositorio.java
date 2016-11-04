package com.example.repository;

import com.example.controler.DTO.EstadisticasPosicion;
import com.example.domain.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@SuppressWarnings("JpaQlInspection")
public interface JugadorRepositorio extends JpaRepository<Jugador, Long> {


    List<Jugador> findByNombre(String nombre);
    List<Jugador> findByCanastoGreaterThanEqual(int canastas);
    List<Jugador> findByAsistoBetween(int min, int max);
    //List<Jugador> findByPosicion(String posicion);
    List<Jugador> findByNacimientoBefore(LocalDate fecha);

    @Query("SELECT jugador.posicion,  AVG(jugador.canasto), AVG(jugador.asisto), " +
            "AVG(jugador.reboto)" +
            "FROM Jugador jugador " +
            "GROUP BY jugador.posicion")
    List<Object[]> AvgCanastoAndAvgAsistoAndAvgRebotoGroupbyPosicion();


   /* @Query("SELECT car.brand, AVG(car.price), MIN(car.price), MAX(car.price) " +
            "FROM Car car " +
            "GROUP BY car.brand " +
            "ORDER BY AVG(car.price) DESC ")
    List<Object[]> AvgAndMaxAndMinPricesPerBrandOrderedByAVGPrice(); */

   @Query("SELECT jugador.posicion,  AVG(jugador.canasto), AVG(jugador.asisto), " +
           "AVG(jugador.reboto)," +
           "MAX(jugador.canasto),"+
           "MAX(jugador.asisto),"+
           "MAX(jugador.reboto),"+

           "MIN(jugador.canasto),"+
           "MIN(jugador.reboto),"+
           "MIN(jugador.asisto)"+
           "FROM Jugador jugador " +
           "GROUP BY jugador.posicion")
   List<Object[]> AvgCanastoAndAvgAsistoAndAvgRebotoGroupbyPosicion2();

    //equipos

    //List<Jugador>findByEquipo(Integer equipo);
    //List<Jugador>findByEquipoAndPosicion(String equipo, String posicion);
    //List<Jugador>findByEquipoWhereCanastoMax(String equipo);
//    List<Jugador>findByEquipoNombre(String nombre);
//    List<Jugador>findByEquipoNombreAndPosicion(String equipo, String posicion);
//
//    List<Jugador>findByAsistoGreaterThan(int asistencias);


    //List<Jugador> findByNombre(String name);

//    List<Jugador> findByCanastoGreaterThanEqual(Integer points);
//
//    List<Jugador> findByCanastoBetween(Integer min, Integer max);
//
//    List<Jugador> findByCanastoGreaterThan(Integer points);
//
//    List<Jugador> findByPoints(Integer points);
//
//    List<Jugador> findAllOrderByCanasto();
//
//    List<Jugador> findByPosicion(String posicion);

    @Query("SELECT jugador.posicion, " +
            " MIN(jugador.canasto)," +
            " MAX(jugador.canasto), " +
            "AVG(jugador.canasto)" +
            "FROM Jugador jugador " +
            "GROUP BY jugador.posicion")
    List<Object[]> findByPosicionAndMedia();

//    List<Jugador> findAllOrderByCanasto();
}
