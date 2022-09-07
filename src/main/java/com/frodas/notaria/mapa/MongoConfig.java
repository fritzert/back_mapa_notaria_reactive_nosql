package com.frodas.notaria.mapa;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

@Configuration
public class MongoConfig implements InitializingBean {

    @Lazy // en el momento q se solicita recien se genera la instancia
    private final MappingMongoConverter mappingMongoConverter;

    public MongoConfig(MappingMongoConverter mappingMongoConverter) {
        this.mappingMongoConverter = mappingMongoConverter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // evitar generar el _class que se genera por defecto en las inserciones en mongo
        mappingMongoConverter.setTypeMapper(new DefaultMongoTypeMapper(null));
    }
}
