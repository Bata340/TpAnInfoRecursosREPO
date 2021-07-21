package com.aninfo.service;

import com.aninfo.exceptions.HoursNotValid;
import com.aninfo.exceptions.MultipleTaskOnSameDay;
import com.aninfo.exceptions.TaskDoesNotBelong;
import com.aninfo.model.CargaDeHoras;
import com.aninfo.repository.CargaDeHorasRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class CargaDeHorasService {

    @Autowired
    private CargaDeHorasRepository cargaDeHorasRepository;

    @Transactional
    public CargaDeHoras createCargaDeHoras(CargaDeHoras carga) throws Throwable {
        if (carga.getHoras() <= 0 || carga.getHoras() > 24) {
            throw new HoursNotValid("La cantidad de horas no es valida");
        }

        CargaDeHoras onDate = cargaDeHorasRepository.findByFechaAndTarea(carga.getFecha(), carga.getTarea());
        if (onDate != null){
            throw new MultipleTaskOnSameDay("Ya se realizó una carga para la tarea en la fecha especificada");
        }

        String projectId = String.valueOf(carga.getProyecto());
        String taskId = String.valueOf(carga.getTarea());
        String urlString = "https://project-squad5.herokuapp.com/api/projects/"+projectId+"/tasks/"+taskId+"/?format=json";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null)
            sb.append(line+"\n");
        br.close();
        Gson json = new Gson();
        JsonObject ret = json.fromJson(sb.toString(), JsonObject.class);
        if (ret.get("employee_id").getAsLong() != carga.getLegajoPersona())
            throw new TaskDoesNotBelong("La tarea no pertenece a la persona");

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

    public Collection<CargaDeHoras> findByFechaBetween(Date desde, Date hasta, long legajo) {
        return cargaDeHorasRepository.findByFechaBetweenAndLegajoPersona(desde, hasta, legajo);
    }
}
