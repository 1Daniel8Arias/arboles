package arboles.arboles.model;



import java.util.*;

public class ArbolBinario<T extends Comparable<T>> {
    private Nodo<T> raiz;

    public ArbolBinario() {
        this.raiz = null;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public void agregar(T dato) {
        raiz = agregarRecursivo(raiz, dato);
    }

    private Nodo<T> agregarRecursivo(Nodo<T> actual, T dato) {
        if (actual == null) {
            return new Nodo<>(dato);
        }

        if (dato.compareTo(actual.getDato()) < 0) {
            actual.setIzquierdo(agregarRecursivo(actual.getIzquierdo(), dato));
        } else if (dato.compareTo(actual.getDato()) > 0) {
            actual.setDerecho(agregarRecursivo(actual.getDerecho(), dato));
        }

        return actual;
    }

    public boolean existe(T dato) {
        return buscar(raiz, dato);
    }

    private boolean buscar(Nodo<T> actual, T dato) {
        if (actual == null) return false;
        if (dato.compareTo(actual.getDato()) == 0) return true;
        return dato.compareTo(actual.getDato()) < 0
                ? buscar(actual.getIzquierdo(), dato)
                : buscar(actual.getDerecho(), dato);
    }



    public void borrarArbol() {
        raiz = null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    public String inorden() {
        StringBuilder sb = new StringBuilder();
        inordenRecursivo(raiz, sb);
        return sb.toString();
    }

    private void inordenRecursivo(Nodo<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            inordenRecursivo(nodo.getIzquierdo(), sb);
            sb.append(nodo.getDato()).append(" ");
            inordenRecursivo(nodo.getDerecho(), sb);
        }
    }

    public String preorden() {
        StringBuilder sb = new StringBuilder();
        preordenRecursivo(raiz, sb);
        return sb.toString();
    }

    private void preordenRecursivo(Nodo<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            sb.append(nodo.getDato()).append(" ");
            preordenRecursivo(nodo.getIzquierdo(), sb);
            preordenRecursivo(nodo.getDerecho(), sb);
        }
    }

    public String postorden() {
        StringBuilder sb = new StringBuilder();
        postordenRecursivo(raiz, sb);
        return sb.toString();
    }

    private void postordenRecursivo(Nodo<T> nodo, StringBuilder sb) {
        if (nodo != null) {
            postordenRecursivo(nodo.getIzquierdo(), sb);
            postordenRecursivo(nodo.getDerecho(), sb);
            sb.append(nodo.getDato()).append(" ");
        }
    }
    public String recorridoPorNiveles() {
        StringBuilder sb = new StringBuilder();
        if (raiz == null) {
            return sb.toString();
        }

        Queue<Nodo<T>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Nodo<T> nodo = cola.poll();
            sb.append(nodo.getDato()).append(" ");

            if (nodo.getIzquierdo() != null) {
                cola.add(nodo.getIzquierdo());
            }

            if (nodo.getDerecho() != null) {
                cola.add(nodo.getDerecho());
            }
        }

        return sb.toString();
    }

    // Eliminar nodo
    public void eliminar(T dato) {
        raiz = eliminarRecursivo(raiz, dato);
    }

    private Nodo<T> eliminarRecursivo(Nodo<T> actual, T dato) {
        if (actual == null) return null;

        if (dato.compareTo(actual.getDato()) < 0) {
            actual.setIzquierdo(eliminarRecursivo(actual.getIzquierdo(), dato));
        } else if (dato.compareTo(actual.getDato()) > 0) {
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), dato));
        } else {
            // Caso 1: sin hijos
            if (actual.getIzquierdo() == null && actual.getDerecho() == null) {
                return null;
            }
            // Caso 2: un hijo
            if (actual.getIzquierdo() == null) return actual.getDerecho();
            if (actual.getDerecho() == null) return actual.getIzquierdo();

            // Caso 3: dos hijos - reemplazar por el menor del subárbol derecho
            T menorValor = encontrarMenor(actual.getDerecho());
            actual.setDato(menorValor);
            actual.setDerecho(eliminarRecursivo(actual.getDerecho(), menorValor));
        }

        return actual;
    }

    private T encontrarMenor(Nodo<T> nodo) {
        return nodo.getIzquierdo() == null ? nodo.getDato() : encontrarMenor(nodo.getIzquierdo());
    }

    // Obtener altura del árbol
    public int obtenerAltura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo<T> actual) {
        if (actual == null) return 0;
        int izquierda = alturaRecursiva(actual.getIzquierdo());
        int derecha = alturaRecursiva(actual.getDerecho());
        return Math.max(izquierda, derecha) + 1;
    }

    // Obtener peso (número de nodos)
    public int obtenerPeso() {
        return contarNodos(raiz);
    }

    private int contarNodos(Nodo<T> actual) {
        if (actual == null) return 0;
        return 1 + contarNodos(actual.getIzquierdo()) + contarNodos(actual.getDerecho());
    }

    // Obtener nivel de un nodo
    public int obtenerNivel(T dato) {
        return nivelRecursivo(raiz, dato, 1);
    }

    private int nivelRecursivo(Nodo<T> actual, T dato, int nivel) {
        if (actual == null) return -1;
        if (dato.compareTo(actual.getDato()) == 0) return nivel;

        if (dato.compareTo(actual.getDato()) < 0)
            return nivelRecursivo(actual.getIzquierdo(), dato, nivel + 1);
        else
            return nivelRecursivo(actual.getDerecho(), dato, nivel + 1);
    }

    // Contar hojas
    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo<T> actual) {
        if (actual == null) return 0;
        if (actual.getIzquierdo() == null && actual.getDerecho() == null) return 1;
        return contarHojasRecursivo(actual.getIzquierdo()) + contarHojasRecursivo(actual.getDerecho());
    }

    // Obtener el nodo con mayor valor
    public T obtenerNodoMayor() {
        if (raiz == null) return null;
        Nodo<T> actual = raiz;
        while (actual.getDerecho() != null) {
            actual = actual.getDerecho();
        }
        return actual.getDato();
    }

    // Obtener el nodo con menor valor
    public T obtenerNodoMenor() {
        if (raiz == null) return null;
        Nodo<T> actual = raiz;
        while (actual.getIzquierdo() != null) {
            actual = actual.getIzquierdo();
        }
        return actual.getDato();
    }
}

