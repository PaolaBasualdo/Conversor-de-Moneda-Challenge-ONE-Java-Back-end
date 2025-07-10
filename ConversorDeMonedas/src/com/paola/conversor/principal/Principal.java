package com.paola.conversor.principal;

import com.paola.conversor.calculos.Conversor;
import com.paola.conversor.calculos.SolicitudApi;
import com.paola.conversor.modelos.Historial;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SolicitudApi solicitud = new SolicitudApi();
        Conversor conversor = new Conversor();
        Historial historial = new Historial();


        int opcion = 0;

        while (opcion != 8) {
            System.out.println("\n+---------------------------------------------+");
            System.out.println("|         CONVERSOR DE MONEDAS INTERNACIONAL  |");
            System.out.println("+---------------------------------------------+");
            System.out.println("| 1) Dólar => Peso Argentino                  |");
            System.out.println("| 2) Peso Argentino => Dólar                  |");
            System.out.println("| 3) Dólar => Real Brasileño                  |");
            System.out.println("| 4) Real Brasileño => Dólar                  |");
            System.out.println("| 5) Dólar => Peso Colombiano                 |");
            System.out.println("| 6) Peso Colombiano => Dólar                 |");
            System.out.println("7) Ver historial de conversiones");
            System.out.println("8) Salir");
            System.out.println("+---------------------------------------------+");
            System.out.println("| Ingrese una opción del 1 al 7:              |");
            System.out.println("+---------------------------------------------+");


            opcion = scanner.nextInt();

            if (opcion >= 1 && opcion <= 6) {
                System.out.print("Ingrese el valor que desea convertir: ");
                double monto = scanner.nextDouble();

                String monedaBase = "";
                String monedaDestino = "";

                switch (opcion) {
                    case 1 -> {
                        monedaBase = "USD";
                        monedaDestino = "ARS";
                    }
                    case 2 -> {
                        monedaBase = "ARS";
                        monedaDestino = "USD";
                    }
                    case 3 -> {
                        monedaBase = "USD";
                        monedaDestino = "BRL";
                    }
                    case 4 -> {
                        monedaBase = "BRL";
                        monedaDestino = "USD";
                    }
                    case 5 -> {
                        monedaBase = "USD";
                        monedaDestino = "COP";
                    }
                    case 6 -> {
                        monedaBase = "COP";
                        monedaDestino = "USD";
                    }
                }

                try {
                    double tasa = solicitud.obtenerTasa(monedaBase, monedaDestino);
                    double resultado = conversor.convertir(monto, tasa);
                    System.out.printf("El valor %.2f %s corresponde a un valor final de %.2f %s\n",
                            monto, monedaBase, resultado, monedaDestino);

                    LocalDateTime ahora = LocalDateTime.now();
                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    String marcaTiempo = ahora.format(formato);

                    String log = String.format("%.2f %s => %.2f %s el %s",
                            monto, monedaBase, resultado, monedaDestino, marcaTiempo);

                    historial.agregar(log);

                } catch (Exception e) {
                    System.out.println("Ocurrió un error durante la conversión: " + e.getMessage());
                }

            } else if (opcion == 7) {
                historial.mostrar();
            } else if (opcion != 8) {
                System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

        System.out.println("Gracias por usar el conversor de monedas. Hasta pronto!");
        scanner.close();
    }
}
