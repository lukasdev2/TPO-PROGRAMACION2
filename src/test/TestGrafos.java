package test;

import algoritmo.Dijkstra;
import modelo.Grafo;

import java.util.List;

public class TestGrafos {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        // Crear intersecciones (nodos)
        for (int i = 0; i <= 5; i++) {
            grafo.agregarNodo(i);
        }

        // Agregar calles con tiempos en minutos.
        grafo.agregarArista(0, 1, 10); // Base -> Nodo 1
        grafo.agregarArista(0, 2, 3);  // Base -> Nodo 2
        grafo.agregarArista(1, 2, 1);
        grafo.agregarArista(1, 3, 2);
        grafo.agregarArista(2, 3, 8);
        grafo.agregarArista(2, 4, 2);
        grafo.agregarArista(3, 5, 7);  // Nodo 3 -> Hospital
        grafo.agregarArista(4, 3, 5);
        grafo.agregarArista(2, 6, 60);  // Nodo 4 -> Hospital

        // Mostrar lista de adyacencia
        System.out.println("Mapa de intersecciones:");
        grafo.mostrarListaAdyacencia();

        int base = 0;      // Nodo de la base de ambulancia
        int hospital = 5;  // Nodo del hospital

        System.out.println("\nCalculando ruta mas rapida desde la base hasta el hospital...");
        List<Integer> ruta = Dijkstra.calcularRutaMasCorta(grafo, base, hospital);
        int tiempo = Dijkstra.calcularTiempoTotal(grafo, base, hospital);

        if (ruta.isEmpty()) {
            System.out.println("No se encontro ruta entre la base y el hospital.");
        } else {
            System.out.println("Ruta más rapida: " + ruta);
            System.out.println("Tiempo total estimado: " + tiempo + " minutos");
        }
    }
}
