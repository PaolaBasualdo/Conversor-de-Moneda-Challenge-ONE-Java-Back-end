package com.paola.conversor.calculos;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.paola.conversor.modelos.ExchangeResponse;

public class SolicitudApi {

    private static final String API_KEY = "6f20bc29d5d244e1ccf8aba6";

    public String obtenerDatos(String monedaBase) {
        String direccion = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/latest/" + monedaBase;

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al hacer la solicitud a la API", e);
        }
    }

    public double obtenerTasa(String monedaBase, String monedaDestino) {
        String json = obtenerDatos(monedaBase);
        Gson gson = new Gson();
        ExchangeResponse respuesta = gson.fromJson(json, ExchangeResponse.class);

        if (respuesta.conversion_rates().containsKey(monedaDestino)) {
            return respuesta.conversion_rates().get(monedaDestino);
        } else {
            throw new IllegalArgumentException("La moneda destino no se encuentra en la respuesta.");
        }
    }
}
