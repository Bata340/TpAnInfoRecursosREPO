package com.aninfo.service;

import com.aninfo.exceptions.DateNotBeforeToday;
import com.aninfo.exceptions.HoursNotValid;
import com.aninfo.exceptions.HoursSumOver24;
import com.aninfo.model.CargaDeHoras;
import com.aninfo.repository.CargaDeHorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
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

        Collection<CargaDeHoras> onDate = cargaDeHorasRepository.findByFechaAndTarea(carga.getFecha(), carga.getTarea());
        Iterator<CargaDeHoras> iterator = onDate.iterator();

        double total = 0;

        while(iterator.hasNext()){
            CargaDeHoras horas = iterator.next();
            total += horas.getHoras();
        }

        if (total + carga.getHoras() > 24){
            throw new HoursSumOver24("La cantidad de horas en el día ingresado para la tarea es mayor a 24");
        }

        Date today = Calendar.getInstance().getTime();
                /*.format(Calendar.getInstance().getTime());*/

        if (carga.getFecha().after(today)){
            throw new DateNotBeforeToday("La fecha especificada es mayor a la actual");
        }
        /*String projectId = String.valueOf(carga.getProyecto());
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
            throw new DateNotBeforeToday("La tarea no pertenece a la persona");*/

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
