package com.frodas.notaria.mapa.repo;

import com.frodas.notaria.mapa.model.DistritoNotarial;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

public interface DistritoRepo extends ReactiveMongoRepository<DistritoNotarial, String> {

    @Query("{'habilitado' : ?0 }")
    Flux<DistritoNotarial> findAllByHabilitado(Boolean habilitado);

    @Query("{'habilitado' : ?0 , 'codigo':  ?1}")
    Mono<Optional<DistritoNotarial>> findByHabilitadoAndCodigo(Boolean habilitado, String codigo);

}


