package com.frodas.notaria.mapa.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)// evitar mostrar los nulos en las respuestas json
@Document(collection = "notarias")
public class Notaria {

    @Id
    private String id;

    @Field(name = "notariaid")
    private Integer notariaId;

    @Field(name = "apellidonombre")
    private String nombreCompleto;

    @Field(name = "nomdepartamento")
    private String departamento;

    @Field(name = "nomprovincia")
    private String provincia;

    @Field(name = "nomdistrito")
    private String distrito;

    @Field(name = "direccoficina")
    private String direccion;

    @Field(name = "distritonotaid")
    private String distritoId;

    private Boolean habilitado;

    @Field(name = "estadoreapertura")
    private String estadoReapertura;

    @Field(name = "fecinicioservicio")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fecInicioServicio;

}