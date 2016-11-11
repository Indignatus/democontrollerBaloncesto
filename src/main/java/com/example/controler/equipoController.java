package com.example.controler;


import com.example.controler.DTO.EstadisticasPosicion;
import com.example.domain.Equipo;
import com.example.domain.Jugador;
import com.example.domain.Posicion;
import com.example.repository.EquipoRepository;
import com.example.repository.JugadorRepositorio;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/equipos")
public class equipoController {
    @Autowired
    private EquipoRepository equipoRepository;
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Equipo createJugador(@RequestBody Equipo equipo){

        return equipoRepository.save(equipo);
    }

    @GetMapping("/localidad")
    public Map<String,Collection<Equipo>> findByPosicion(){

        List<Equipo> posicionJugadores = equipoRepository.findAll();

        ListMultimap<String, Equipo> localidades = ArrayListMultimap.create();

        for(Equipo p: posicionJugadores){
            localidades.put(p.getLocalidad(), p);
        }
        posicionJugadores.forEach(jugador ->
                localidades.put(jugador.getLocalidad(), jugador));

        System.out.println();

        return localidades.asMap();
    }
}
