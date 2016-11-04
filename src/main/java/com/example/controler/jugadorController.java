package com.example.controler;

import com.example.controler.DTO.EstadisticasPosicion;
import com.example.domain.Jugador;
import com.example.repository.JugadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/jugadores")
public class jugadorController {
    @Autowired
    private JugadorRepositorio jugadorRepository;
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Jugador createJugador(@RequestBody Jugador jugador){

        return jugadorRepository.save(jugador);
    }
    @RequestMapping(method = RequestMethod.GET)
    public List<Jugador> findAll(){
        List<Jugador> jugadorList = new ArrayList<>();
        return jugadorRepository.findAll();
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
//   @GetMapping("/orderByPoints")
//   public List<Jugador> findAllOrderByPoints() {
//        return jugadorRepository.findAllOrderByCanasto();
//   }
////
//    @GetMapping("/greaterThanPoints/{points}")
//    public List<Jugador>findByPointsGreaterThan(@PathVariable Integer points){
//        return jugadorRepository.findByCanastoGreaterThan(points);
//    }
//    @GetMapping("/between/{points1}/{points2}")
//    public List<Jugador>findByPointsBetween(@PathVariable Integer points1, Integer points2){
//        return jugadorRepository.findByCanastoBetween(points1,points2);
//    }
   /*@GetMapping("/posicion/{posicion}")
    public Map<Long,Jugador> findByPosicion(@PathVariable String posicion){
        return jugadorRepository.findByPosicion(posicion);
    } */
    @GetMapping("/posicionAndMedia")
    public Map<String, EstadisticasPosicion> findByPosicionAndMedia(){

        List<Object[]> estadisticasPosicions = jugadorRepository.findByPosicionAndMedia();

        Map<String, EstadisticasPosicion> estadisticasPosicionMap = new HashMap<>();

        estadisticasPosicions.
                forEach(estadisticasPosicion -> {

                    EstadisticasPosicion aux = new EstadisticasPosicion();
                    aux.setPosicion((String)estadisticasPosicion[0]);
                    aux.setMinCanastas((Integer)estadisticasPosicion[1]);
                    aux.setMaxCanastas((Integer)estadisticasPosicion[2]);
                    aux.setAvgCanastas((Double) estadisticasPosicion[3]);

                    estadisticasPosicionMap.put(aux.getPosicion(), aux);

                });

        return estadisticasPosicionMap;
    }
}