package com.paola.conversor.modelos;

import java.util.ArrayList;
import java.util.List;

public class Historial {

    private List<String> registros = new ArrayList<>();

    public void agregar(String registro) {
        registros.add(registro);
    }

    public void mostrar() {
        if (registros.isEmpty()) {
            System.out.println("\nNo hay conversiones registradas.");
        } else {
            System.out.println("\nHistorial de conversiones:");
            registros.forEach(System.out::println);
        }
    }
}
