package modelo;

import interfaces.IGrafo;
import interfaces.INodo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo implements IGrafo {
    private Map<Integer, Nodo> nodos = new HashMap<>();

    @Override
    public void agregarNodo(int valor) {
        nodos.putIfAbsent(valor, new Nodo(valor));
    }

    @Override
    public void agregarArista(int origen, int destino, int peso) {
        Nodo nodoOrigen = nodos.get(origen);
        Nodo nodoDestino = nodos.get(destino);

        if (nodoOrigen != null && nodoDestino != null) {
            if (!yaExisteArista(nodoOrigen, destino)) {
                nodoOrigen.agregarVecino(nodoDestino, peso);
            }
            if (!yaExisteArista(nodoDestino, origen)) {
                nodoDestino.agregarVecino(nodoOrigen, peso); // bidireccional
            }
        }
    }

    private boolean yaExisteArista(Nodo origen, int destinoId) {
        List<INodo> vecinos = origen.getVecinos();
        for (INodo vecino : vecinos) {
            if (vecino.getValor() == destinoId) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void mostrarListaAdyacencia() {
        for (Nodo nodo : nodos.values()) {
            System.out.print("Nodo " + nodo.getValor() + " -> ");
            List<INodo> vecinos = nodo.getVecinos();
            List<Integer> pesos = nodo.getPesos();
            for (int i = 0; i < vecinos.size(); i++) {
                System.out.print("[dest: " + vecinos.get(i).getValor() + ", tiempo=" + pesos.get(i) + "min] ");
            }
            System.out.println();
        }
    }

    public Nodo getNodo(int valor) {
        return nodos.get(valor);
    }

    public Map<Integer, Nodo> getNodos() {
        return nodos;
    }
}
