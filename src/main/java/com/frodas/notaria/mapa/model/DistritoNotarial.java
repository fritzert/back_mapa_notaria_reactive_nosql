package com.frodas.notaria.mapa.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)// evitar mostrar los nulos en las respuestas json
@Document(collection = "distritos")
public class DistritoNotarial {

    @Id
    private String id;

    private String codigo;

    private String nombre;

    @Field(name = "coordlatitud")
    private Float coordLatitud;

    @Field(name = "coordlongitud")
    private Float coordLongitud;

    private Integer cantidad;

    private Boolean habilitado;

    private String urlImg;

}
