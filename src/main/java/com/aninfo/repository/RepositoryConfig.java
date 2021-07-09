package com.aninfo.repository;

import com.aninfo.model.CargaDeHoras;
import com.aninfo.model.Recurso;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;

@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {
    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(CargaDeHoras.class);
        config.exposeIdsFor(Recurso.class);
    }
}
