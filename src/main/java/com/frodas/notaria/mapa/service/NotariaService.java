package com.frodas.notaria.mapa.service;

import com.frodas.notaria.mapa.dto.DistritoGis;
import com.frodas.notaria.mapa.dto.DistritoInfo;
import reactor.core.publisher.Mono;

public interface NotariaService {

	Mono<DistritoGis> listarDistritos();

	Mono<DistritoInfo> buscarDistritoPorId(String codDistrito);

}
