package com.aninfo.service;

import com.aninfo.exceptions.LegajoNotFound;
import com.aninfo.model.Recurso;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

@Service
public class RecursoService {

    public String getFromApi() throws Throwable {
        StringBuilder fullResponseBuilder = new StringBuilder();

        String urlString = "https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos";
        URL url = new URL(urlString);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        int i=0;
        while ((inputLine = in.readLine()) != null) {
            //System.out.println(i);
            content.append(inputLine);
        }
        in.close();
        con.disconnect();
        return content.toString();
    }

    public Collection<Recurso> getRecursos() throws Throwable {
        Collection<Recurso> recursos = new ArrayList<Recurso>();
        String content = getFromApi();
        while (content.length()>2){
            int inicio = content.indexOf("{");
            int fin = content.indexOf("}");
            String obj = content.substring(inicio+1, fin);
            content = content.substring(fin+1);
            String[] attributes = obj.split(",");
            Long legajo = null;
            String nombre = null;
            String apellido = null;
            for (int i=0;i<attributes.length;i++) {
                String attribute = attributes[i];
                String[] div = attribute.split(":");
                if(i == 0){legajo = Long.valueOf(div[1]);}
                else if(i == 1){nombre = div[1].substring(1, div[1].length()-1);}
                else {apellido = div[1].substring(1, div[1].length()-1);}
            }
            Recurso rec = new Recurso(legajo, nombre, apellido);
            recursos.add(rec);
        }
        return recursos;
    }

    public Recurso getRecursoByLegajo(long legajo) throws Throwable {
        ArrayList<Recurso> recursos = new ArrayList<>(getRecursos());
        for(int i=0;i<recursos.size();i++){
            if(recursos.get(i).getLegajo() == legajo){ return recursos.get(i); }
        }
        throw new LegajoNotFound("El legajo solicitado no existe");
    }

}
