package com.example.controler;

import com.example.controler.DTO.EstadisticasPosicion;
import com.example.domain.HeaderUtil;
import com.example.domain.Jugador;
import com.example.domain.Posicion;
import com.example.repository.JugadorRepositorio;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;
import static sun.audio.AudioPlayer.player;

@RestController
@RequestMapping("/jugadores")
public class jugadorController {
    @Autowired
    private JugadorRepositorio jugadorRepository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Jugador createJugador(@RequestBody Jugador jugador) {

        return jugadorRepository.save(jugador);
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET)
    public Jugador findById(@PathVariable Long id) {
        Jugador jugador = jugadorRepository.findOne(id);

        return jugador;
    }

    //    @RequestMapping(value = "/byasistencias" ,method = RequestMethod.GET)
//    public List<Jugador> findByAssistenciasGreater(){
//
//     return jugadorRepository.findByAsistoGreaterThan(30);
//    }
//
    @GetMapping("/orderByPoints")
    public List<Jugador> findAllOrderByCanasto() {
        return jugadorRepository.findAllByOrderByCanasto();
    }

    ////
    @GetMapping("/greaterThanPoints/{points}")
    public List<Jugador> findByPointsGreaterThan(@PathVariable Integer points) {
        return jugadorRepository.findByCanastoGreaterThan(points);
    }

    @GetMapping("/between/{points1}/{points2}")
    public List<Jugador> findByPointsBetween(@PathVariable Integer points1, Integer points2) {
        return jugadorRepository.findByCanastoBetween(points1, points2);
    }

    //    Ordenar por posicion con multimap
    @GetMapping("/posicion")
    public Map<Posicion, Collection<Jugador>> findByPosicion() {

//
//       ArrayList<Jugador> jugadores = new ArrayList<>();
//
//       Map<String,List<Jugador>> posicionJugador = new HashMap<>();
//
//       posicionJugadores.
//       forEach(posicionJugadore -> {
//           posicionJugador.put(posicionJugadores.get(0),(List<Jugador>) posicionJugadores.get(1));
//       });
//        return posicionJugador;

        List<Jugador> posicionJugadores = jugadorRepository.findAll();

        ListMultimap<Posicion, Jugador> posiciones = ArrayListMultimap.create();

        for (Jugador p : posicionJugadores) {
            posiciones.put(p.getPosicion(), p);
        }
        posicionJugadores.forEach(jugador ->
                posiciones.put(jugador.getPosicion(), jugador));

        System.out.println();

        return posiciones.asMap();
    }

    //    Ordenar por posicion con JAVA8
    @GetMapping("/GroupByPosition")
    public Map<Posicion, List<Jugador>> getJugadoresGroupByPosicion() {
        return jugadorRepository
                .findAll()
                .parallelStream()
                .collect(groupingBy(Jugador::getPosicion));
    }

    //Devolver un Map
    @GetMapping("/posicionAndMedia")
    public Map<String, EstadisticasPosicion> findByPosicionAndMedia() {

        List<Object[]> estadisticasPosicions = jugadorRepository.findByPosicionAndMedia();

        Map<String, EstadisticasPosicion> estadisticasPosicionMap = new HashMap<>();

        estadisticasPosicions.
                forEach(estadisticasPosicion -> {

                    EstadisticasPosicion aux = new EstadisticasPosicion();
                    aux.setPosicion((String) estadisticasPosicion[0]);
                    aux.setMinCanastas((Integer) estadisticasPosicion[1]);
                    aux.setMaxCanastas((Integer) estadisticasPosicion[2]);
                    aux.setAvgCanastas((Double) estadisticasPosicion[3]);

                    estadisticasPosicionMap.put(aux.getPosicion(), aux);

                });

        return estadisticasPosicionMap;
    }
//  OrderBy con @RequestParam y control de errores
    @GetMapping
    public List<Jugador> findAllOrderBy(
            @RequestParam(name = "orderBy", required = false) String orderBy,
            @RequestParam(name = "direction", defaultValue = "ASC") String direccion
    ) {
        Sort sort;
        if (orderBy != null) {
            if (direccion.equals("ASC")) {
                sort = new Sort(Sort.Direction.ASC, orderBy);
            } else {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            }
            return jugadorRepository.findAll(sort);
        }
        return jugadorRepository.findAll();
    }

   //Control de errores agregando jugadores
   @PostMapping
   @ResponseStatus(HttpStatus.CREATED)
   public ResponseEntity<Jugador> createJugadorControl(@RequestBody Jugador jugador) throws URISyntaxException {

       if (jugador.getId() != null) {
           return ResponseEntity.
                   badRequest().
                   headers(
                           HeaderUtil.
                                   createFailureAlert("player", "idexists", "A new player cannot already have an ID")).body(null);
       }
       Jugador result = jugadorRepository.save(jugador);
       return ResponseEntity.created(new URI("/players/" + result.getId()))
               .headers(HeaderUtil.createEntityCreationAlert("player", result.getId().toString()))
               .body(result);
   }

}