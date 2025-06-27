package algoritmo;

import interfaces.INodo;
import modelo.Grafo;
import modelo.Nodo;

import java.util.*;

public class Dijkstra {

    public static List<Integer> calcularRutaMasCorta(Grafo grafo, int origen, int destino) {
        Map<Integer, Nodo> nodos = grafo.getNodos();
        Map<Integer, Integer> distancias = new HashMap<>();
        Map<Integer, Integer> previos = new HashMap<>();
        Set<Integer> visitados = new HashSet<>();
        PriorityQueue<NodoDistancia> cola = new PriorityQueue<>();

        for (Integer id : nodos.keySet()) {
            distancias.put(id, Integer.MAX_VALUE);
        }

        distancias.put(origen, 0);
        cola.add(new NodoDistancia(origen, 0));

        while (!cola.isEmpty()) {
            NodoDistancia actual = cola.poll();

            if (visitados.contains(actual.id)) continue;
            visitados.add(actual.id);

            Nodo nodoActual = nodos.get(actual.id);
            List<INodo> vecinos = nodoActual.getVecinos();
            List<Integer> pesos = nodoActual.getPesos();

            for (int i = 0; i < vecinos.size(); i++) {
                int vecinoId = vecinos.get(i).getValor();
                int peso = pesos.get(i);
                int nuevaDistancia = distancias.get(actual.id) + peso;

                if (nuevaDistancia < distancias.get(vecinoId)) {
                    distancias.put(vecinoId, nuevaDistancia);
                    previos.put(vecinoId, actual.id);
                    cola.add(new NodoDistancia(vecinoId, nuevaDistancia));
                }
            }
        }

        return reconstruirRuta(previos, origen, destino);
    }

    public static int calcularTiempoTotal(Grafo grafo, int origen, int destino) {
        List<Integer> ruta = calcularRutaMasCorta(grafo, origen, destino);
        if (ruta.isEmpty()) return -1;

        int tiempoTotal = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            Nodo nodo = grafo.getNodo(ruta.get(i));
            List<INodo> vecinos = nodo.getVecinos();
            List<Integer> pesos = nodo.getPesos();
            for (int j = 0; j < vecinos.size(); j++) {
                if (vecinos.get(j).getValor() == ruta.get(i + 1)) {
                    tiempoTotal += pesos.get(j);
                    break;
                }
            }
        }
        return tiempoTotal;
    }

    private static List<Integer> reconstruirRuta(Map<Integer, Integer> previos, int origen, int destino) {
        LinkedList<Integer> ruta = new LinkedList<>();
        Integer actual = destino;

        while (actual != null) {
            ruta.addFirst(actual);
            actual = previos.get(actual);
        }

        if (ruta.getFirst() != origen) {
            return Collections.emptyList(); // No hay ruta
        }

        return ruta;
    }

    private static class NodoDistancia implements Comparable<NodoDistancia> {
        int id, distancia;

        NodoDistancia(int id, int distancia) {
            this.id = id;
            this.distancia = distancia;
        }

        public int compareTo(NodoDistancia otro) {
            return Integer.compare(this.distancia, otro.distancia);
        }
    }
}
