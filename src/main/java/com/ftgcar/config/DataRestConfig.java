package com.ftgcar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
public class DataRestConfig implements RepositoryRestConfigurer {

    @Value("${spring.data.rest.allowedOrigins}")
    private String[] allowedOrigins;

    private final EntityManager entityManager;

    @Autowired
    public DataRestConfig(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
        exposeIds(config);

        cors.addMapping(config.getBasePath() + "/**").allowedOrigins(allowedOrigins)
                .allowedMethods("GET", "POST");
    }

    private void exposeIds(RepositoryRestConfiguration config) {
        Set<EntityType<?>> entityTypes = entityManager.getMetamodel().getEntities();

        List<Class> classes = new ArrayList<>();

        for (EntityType entityType : entityTypes) {
            classes.add(entityType.getJavaType());
        }

        Class[] domainTypes = classes.toArray(new Class[0]);
        config.exposeIdsFor(domainTypes);
    }
}
