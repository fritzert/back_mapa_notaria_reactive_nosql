package com.frodas.notaria.mapa.repo;

import com.frodas.notaria.mapa.model.Notaria;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotariaRepo extends MongoRepository<Notaria, String> {

    @Query(value = "{'habilitado' : ?0 , 'distritonotaid':  ?1}", count = true)
    Mono<Integer> countByHabilitadoAndDistritoId(Boolean habilitado, String codigo);

    @Query("{'habilitado' : ?0 , 'distritonotaid':  ?1}")
    Flux<Notaria> findAllByHabilitadoAndDistritoId(Boolean habilitado, String codigo);

}