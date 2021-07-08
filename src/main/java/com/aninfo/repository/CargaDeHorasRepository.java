package com.aninfo.repository;

import com.aninfo.model.CargaDeHoras;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CargaDeHorasRepository extends CrudRepository<CargaDeHoras, Long> {

    CargaDeHoras findCargaDeHorasByIdCarga(Long idCarga);

}
