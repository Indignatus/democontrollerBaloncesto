package com.example.controler;


import com.example.controler.DTO.EstadisticasPosicion;
import com.example.domain.Equipo;
import com.example.domain.HeaderUtil;
import com.example.domain.Jugador;
import com.example.domain.Posicion;
import com.example.repository.EquipoRepository;
import com.example.repository.JugadorRepositorio;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static java.util.stream.Collectors.groupingBy;

@RestController
@RequestMapping("/equipos")
public class equipoController {
    @Autowired
    private EquipoRepository equipoRepository;
    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Equipo> createJugador(@RequestBody Equipo equipo) throws URISyntaxException {

        if(equipo.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("player", "idexists", "A new player cannot already have an ID")).body(null);
        }
        Equipo result = equipoRepository.save(equipo);
        return ResponseEntity.created(new URI("/equipos/" + result.getId())).headers(HeaderUtil.createEntityCreationAlert("equipo", result.getId().toString())).body(result);
    }

    @GetMapping("/localidad")
    public Map<String,Collection<Equipo>> findByPosicion(){

        List<Equipo> posicionJugadores = equipoRepository.findAll();

        ListMultimap<String, Equipo> localidades = ArrayListMultimap.create();

        /*for(Equipo p: posicionJugadores){
            localidades.put(p.getLocalidad(), p);
        }*/
        posicionJugadores.forEach(equipo ->
                localidades.put(equipo.getLocalidad(), equipo));

        System.out.println();

        return localidades.asMap();
    }

    @GetMapping("/localidad8")
    public Map<String,List<Equipo>> findByPosicion8(){

        return equipoRepository.findAll().parallelStream().collect(groupingBy(Equipo::getLocalidad));
    }
}
