# TPO Programación II - Ambulancia: Ruta más rápida

## 🎯 Objetivo

Simular la elección de la **ruta más rápida para una ambulancia** desde su base hasta un hospital central, utilizando grafos ponderados y el algoritmo de Dijkstra implementado bajo el paradigma TDA (Tipos Abstractos de Datos).

---

## 🏙️ Descripción del problema

- La ciudad se representa como un **grafo** donde:
  - Cada **nodo** es una intersección.
  - Cada **arista** es una calle, con un **peso** que representa el tiempo estimado (en minutos) de tránsito.

- Se busca encontrar la **ruta más rápida** (en tiempo) desde la base (nodo origen) hasta el hospital (nodo destino).

---

## 🧠 Algoritmo utilizado

Se utilizó el **algoritmo de Dijkstra**, que permite calcular el camino más corto desde un nodo origen hasta todos los nodos accesibles, garantizando la **mínima distancia temporal** cuando todos los pesos son positivos.

Ventajas:
- Exactitud.
- Velocidad en grafos urbanos pequeños o medianos.
- Totalmente determinista.

---

## 🧱 Estructura del proyecto

