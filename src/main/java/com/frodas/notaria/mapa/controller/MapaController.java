package com.frodas.notaria.mapa.controller;

import com.frodas.notaria.mapa.dto.DistritoGis;
import com.frodas.notaria.mapa.dto.DistritoInfo;
import com.frodas.notaria.mapa.exception.ModeloNotFoundException;
import com.frodas.notaria.mapa.service.NotariaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/notarias")
public class MapaController {

    private final NotariaService service;

    public MapaController(NotariaService service) {
        this.service = service;
    }

    // @ApiIgnore // para excluir de la documentacion de swagger-ui
    @GetMapping("/distritos")
    public Mono<ResponseEntity<DistritoGis>> listar() {
        return service.listarDistritos()
                .map(p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p)
                ).defaultIfEmpty(new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @GetMapping("/distritos/{id}")
    public Mono<ResponseEntity<?>> listarPorId(@PathVariable("id") String id) {
        return service.buscarDistritoPorId(id)
                .map(p -> {
                    if (p.getPropiedades() == null) {
                        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("ID NO ENCONTRADO, time: " + new Date());
                    } else {
                        return ResponseEntity.ok()   //Mono<ResponseEntity>
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(p);
                    }
                });
    }

}
