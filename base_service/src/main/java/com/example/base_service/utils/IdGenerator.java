package com.example.base_service.utils;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class IdGenerator extends UUIDGenerator {

    public static String ENTITY_PREFIX;
    public static final String NAME = "IdGenerator";
    public static final String PATH = "com.example.base_service.utils.IdGenerator";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return ENTITY_PREFIX + super.generate(session, object).toString().substring(24);
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {
        super.configure(type, params, serviceRegistry);
    }

    public static void setEntityCode(String name){
        ENTITY_PREFIX = name;
    }
}
