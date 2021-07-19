package com.aninfo.service;

import com.aninfo.exceptions.HoursNotValid;
import com.aninfo.model.CargaDeHoras;
import com.aninfo.repository.CargaDeHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class CargaDeHorasService {

    @Autowired
    private CargaDeHorasRepository cargaDeHorasRepository;

    @Transactional
    public CargaDeHoras createCargaDeHoras(CargaDeHoras carga) {
        if (carga.getHoras() <= 0) {
            throw new HoursNotValid("La cantidad de horas no es valida");
        }

        cargaDeHorasRepository.save(carga);

        return carga;
    }

    public Collection<CargaDeHoras> getCargasDeHoras() {
        Collection<CargaDeHoras> cargas = new ArrayList<>();
        cargaDeHorasRepository.findAll().forEach(cargas::add);
        return cargas;
    }

    public Optional<CargaDeHoras> findById(Long id) {
        return cargaDeHorasRepository.findById(id);
    }

    public void save(CargaDeHoras carga) {
        cargaDeHorasRepository.save(carga);
    }

    public void deleteById(Long id) {
        cargaDeHorasRepository.deleteById(id);
    }

    public Collection<CargaDeHoras> findByLegajo(Long legajo) { return cargaDeHorasRepository.findByLegajoPersona(legajo); }

    public Collection<CargaDeHoras> findByFechaBetween(Date desde, Date hasta) {
//        Calendar from = Calendar.getInstance();
//        from.set(Integer.parseInt(desde.substring(0,4)), Integer.parseInt(desde.substring(5,2))-1, Integer.parseInt(desde.substring(8,2)));
//        Calendar to = Calendar.getInstance();
//        to.set(Integer.parseInt(hasta.substring(0,4)), Integer.parseInt(hasta.substring(5,2))-1, Integer.parseInt(hasta.substring(8,2)));
        return cargaDeHorasRepository.findByFechaBetween(desde, hasta);
    }
}
