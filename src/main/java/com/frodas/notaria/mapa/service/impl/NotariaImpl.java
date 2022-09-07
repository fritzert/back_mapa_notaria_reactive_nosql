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
        List<Caracteristica> list = distritoRepo.findAllByHabilitado(Boolean.TRUE).stream()
                .map(x ->
                        new Caracteristica(FEATURE,
                                new Propiedades(x.getCodigo(), x.getNombre(),
                                        notariaRepo.countByHabilitadoAndDistritoId(Boolean.TRUE, x.getCodigo()).toString(),
                                        //x.getCantidad().toString(),
                                        x.getUrlImg()
                                ),
                                new Geometria(POINT, Arrays.asList(x.getCoordLongitud(), x.getCoordLatitud())))

                )
                .collect(Collectors.toList());
        return new DistritoGis(FEATURE_COLLECTION, list);
    }

    @Override
    public Mono<DistritoInfo> buscarDistritoPorId(String codDistrito) {
        Optional<DistritoNotarial> op = distritoRepo.findByHabilitadoAndCodigo(Boolean.TRUE, codDistrito);
        if (op.isEmpty()) {
            log.info("No se encontro el distrito : " + codDistrito);
            return new DistritoInfo();
        }
        DistritoNotarial distrito = op.get();
        System.out.println(distrito.toString());

        List<Notaria> lista = notariaRepo.findAllByHabilitadoAndDistritoId(Boolean.TRUE, codDistrito);
        if (lista.isEmpty()) {
            log.info("No se encontraron notarias");
            return new DistritoInfo();
        }
        DistritoInfo info = new DistritoInfo(new Propiedades(distrito.getCodigo(), distrito.getNombre(),
                notariaRepo.countByHabilitadoAndDistritoId(Boolean.TRUE, distrito.getCodigo()).toString(),
                //distrito.getCantidad().toString()),
                distrito.getUrlImg()
        ), lista);
        return info;
    }

}