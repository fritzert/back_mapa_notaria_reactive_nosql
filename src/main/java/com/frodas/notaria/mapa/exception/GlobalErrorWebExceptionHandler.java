package com.frodas.notaria.mapa.exception;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
@Order(-1) // para iniciar como primer orden
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes, WebProperties webproperties,
                                          ApplicationContext applicationContext, ServerCodecConfigurer configurer) {
        super(errorAttributes, webproperties.getResources(), applicationContext);
        this.setMessageWriters(configurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(ServerRequest request) {
//        Map<String, Object> errorGeneral = getErrorAttributes(request, false);
        Map<String, Object> errorGeneral = getErrorAttributes(request,
                ErrorAttributeOptions.defaults());
        Map<String, Object> mapException = new HashMap<>();

        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        String statusCode = String.valueOf(errorGeneral.get("status"));

        //Devolver message de valid
        //String mensaje = String.valueOf(errorGeneral.get("message"));

        switch (statusCode) {
            case "500":
                mapException.put("error", "500");
                mapException.put("excepcion", "Error general del backend");
                httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
                break;
            case "400":
                mapException.put("error", "400");
                mapException.put("excepcion", "Petici??n incorrecta");
                httpStatus = HttpStatus.BAD_REQUEST;
                break;
            default:
                mapException.put("error", "900");
                mapException.put("excepcion", errorGeneral.get("error"));
                httpStatus = HttpStatus.CONFLICT;
                break;
        }

        return ServerResponse.status(httpStatus)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorGeneral));
    }

}

