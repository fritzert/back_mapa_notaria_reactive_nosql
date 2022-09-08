package com.frodas.notaria.mapa.service.impl;

import com.frodas.notaria.mapa.dto.*;
import com.frodas.notaria.mapa.model.DistritoNotarial;
import com.frodas.notaria.mapa.model.Notaria;
import com.frodas.notaria.mapa.repo.DistritoRepo;
import com.frodas.notaria.mapa.repo.NotariaRepo;
import com.frodas.notaria.mapa.service.NotariaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("notariaService")
public class NotariaImpl implements NotariaService {

    private static final Logger log = LoggerFactory.getLogger(NotariaImpl.class);

    private static final String FEATURE_COLLECTION = "FeatureCollection";
    private static final String FEATURE = "Feature";
    private static final String POINT = "Point";

    private final DistritoRepo distritoRepo;

    private final NotariaRepo notariaRepo;

    public NotariaImpl(DistritoRepo distritoRepo, NotariaRepo notariaRepo) {
        this.distritoRepo = distritoRepo;
        this.notariaRepo = notariaRepo;
    }

    @Override
    public Mono<DistritoGis> listarDistritos() {
        return distritoRepo.findAllByHabilitado(Boolean.TRUE)
                .map(x ->
                        new Caracteristica(FEATURE,
                                new Propiedades(x.getCodigo(), x.getNombre(),
                                        notariaRepo.countByHabilitadoAndDistritoId(Boolean.TRUE, x.getCodigo()).toString(),
                                        //x.getCantidad().toString(),
                                        x.getUrlImg()
                                ),
                                new Geometria(POINT, Arrays.asList(x.getCoordLongitud(), x.getCoordLatitud())))
                ).collectList()
                .flatMap(u -> {
                    return Mono.just(new DistritoGis(FEATURE_COLLECTION, u));
                });
    }

    @Override
    public Mono<DistritoInfo> buscarDistritoPorId(String codDistrito) {
        return distritoRepo.findByHabilitadoAndCodigo(Boolean.TRUE, codDistrito)
                .flatMap(dis -> {
                            return Mono.just(new Propiedades(dis.getCodigo(), dis.getNombre(),
                                    notariaRepo.countByHabilitadoAndDistritoId(Boolean.TRUE, dis.getCodigo()).toString(),
                                    //distrito.getCantidad().toString()),
                                    dis.getUrlImg()));
                        }
                )
                .flatMap(x -> {
                    return notariaRepo.findAllByHabilitadoAndDistritoId(Boolean.TRUE, codDistrito)
                            .collectList()
                            .flatMap(c -> {
                                return Mono.just(new DistritoInfo(x, c));
                            });
                });
    }

}