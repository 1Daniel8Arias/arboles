package arboles.arboles.controller;

import java.net.URL;
import javafx.scene.paint.Color;
import arboles.arboles.model.ArbolBinario;
import arboles.arboles.model.Nodo;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.shape.Line;

import java.util.ResourceBundle;

public class ArbolController {
    @FXML private TextField txtDato;
    @FXML private TextArea txtResultado;
    @FXML private Pane arbolPane;
    @FXML private ScrollPane scrollPane;



    private ArbolBinario<Integer> arbol = new ArbolBinario<>();

    @FXML
    void initialize() {
        arbolPane.prefWidthProperty().bind(scrollPane.widthProperty());
        arbolPane.prefHeightProperty().bind(scrollPane.heightProperty());

        int[] datosIniciales = {14, 15, 4, 9, 7, 18, 3, 5, 16, 4, 20, 17};
        for (int dato : datosIniciales) {
            arbol.agregar(dato);
        }

        txtResultado.setText("Árbol cargado con datos iniciales.");
        dibujarArbol();

    }
    @FXML
    public void agregar() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            arbol.agregar(dato);
            txtResultado.setText("Dato agregado: " + dato);
            dibujarArbol();
            initialize();
            txtDato.setText("");
        } catch (NumberFormatException e) {
            txtResultado.setText("Ingrese un número válido.");
        }
    }

    @FXML
    public void eliminar() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            arbol.eliminar(dato);
            txtResultado.setText("Dato eliminado: " + dato);
            dibujarArbol();
            txtDato.setText("");
        } catch (NumberFormatException e) {
            txtResultado.setText("Ingrese un número válido.");
        }
    }

    @FXML
    public void existe() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            boolean existe = arbol.existe(dato);
            txtResultado.setText(existe ? "El dato existe." : "El dato NO existe.");
            txtDato.setText("");
        } catch (NumberFormatException e) {
            txtResultado.setText("Ingrese un número válido.");
        }
    }

    @FXML
    public void inorden() {
        txtResultado.setText(arbol.inorden());
    }

    @FXML
    public void preorden() {
        txtResultado.setText(arbol.preorden());
    }

    @FXML
    public void postorden() {
        txtResultado.setText(arbol.postorden());
    }

    @FXML
    public void porNiveles() {
        txtResultado.setText(arbol.recorridoPorNiveles());
    }

    @FXML
    public void altura() {
        txtResultado.setText("Altura del árbol: " + arbol.obtenerAltura());
    }

    @FXML
    public void peso() {
        txtResultado.setText("Peso del árbol: " + arbol.obtenerPeso());
    }

    @FXML
    public void nivel() {
        try {
            int dato = Integer.parseInt(txtDato.getText());
            txtResultado.setText("Nivel del dato: " + arbol.obtenerNivel(dato));
        } catch (NumberFormatException e) {
            txtResultado.setText("Ingrese un número válido.");
        }
    }

    @FXML
    public void contarHojas() {
        txtResultado.setText("Número de hojas: " + arbol.contarHojas());
    }

    @FXML
    public void mayor() {
        txtResultado.setText("Mayor: " + arbol.obtenerNodoMayor());
    }

    @FXML
    public void menor() {
        txtResultado.setText("Menor: " + arbol.obtenerNodoMenor());
    }

    @FXML
    public void borrar() {
        arbol = new ArbolBinario<>();
        txtResultado.setText("Árbol borrado.");
        dibujarArbol();
    }
    @FXML
    void vacio() {
if (arbol.estaVacio()) {
    txtResultado.setText("el arbol esta vacio");
}else {
    txtResultado.setText("el arbol no esta vacio");
}
    }

    private void dibujarArbol() {
        arbolPane.getChildren().clear();
        dibujarNodo(arbol.getRaiz(), 250, 30, 120);
    }

    private void dibujarNodo(Nodo<Integer> nodo, double x, double y, double separacion) {
        Circle circulo = new Circle(x, y, 20);
        circulo.setFill(Color.TRANSPARENT);
        circulo.setStroke(Color.BLUE);
        circulo.setStrokeWidth(2);


        Text texto = new Text(nodo.getDato().toString());
        texto.setX(x - texto.getLayoutBounds().getWidth() / 2);
        texto.setY(y + texto.getLayoutBounds().getHeight() / 4);


        arbolPane.getChildren().addAll(circulo, texto);


        if (nodo.getIzquierdo() != null) {
            double xIzq = x - separacion;
            double ySig = y + 60;
            Line linea = new Line(x, y + 20, xIzq, ySig - 20);
            arbolPane.getChildren().add(linea);
            dibujarNodo(nodo.getIzquierdo(), xIzq, ySig, separacion / 1.5);
        }

        if (nodo.getDerecho() != null) {
            double xDer = x + separacion;
            double ySig = y + 60;
            Line linea = new Line(x, y + 20, xDer, ySig - 20);
            arbolPane.getChildren().add(linea);
            dibujarNodo(nodo.getDerecho(), xDer, ySig, separacion / 1.5);
        }
    }

}