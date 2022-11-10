package archivos.ast.Base.Expresiones.Operaciones.binarias;

import archivos.ast.Base.Expresiones.Expresion;

public abstract class OperacionBinaria extends Expresion {
    private Expresion izquierda;
    private Expresion derecha;
    private Number resultado;

    public OperacionBinaria(String nombre, Expresion izquierda, Expresion derecha) {
        super(nombre);
        this.izquierda = izquierda;
        this.derecha = derecha;
    }

    public String tipo(Expresion e){
        return e.darResultado().getClass().getSimpleName();
    }
    public double castDouble(Expresion e){
        return e.darResultado().doubleValue();
    }
    public int castInt(Expresion e){
        return e.darResultado().intValue();
    }

    public Number getResultado() {
        return resultado;
    }
    public void setResultado(Number valor){
        this.resultado=valor;
    }

    @Override
    public String getEtiqueta() {
        return " - [ " + this.getNombre() + " ] - ";
    }

    public Expresion getDerecha() {
        return this.derecha;
    }

    public Expresion getIzquierda() {
        return this.izquierda;
    }

    public void setIzquierda(Expresion izquierda) {
        this.izquierda=izquierda;
    }
    public void setDerecha(Expresion derecha) {
        this.derecha=derecha;
    }

    @Override
    public String graficar(String idPadre) {
        //Se crea un Stringbuilder
        StringBuilder grafico = new StringBuilder();
        //Se llaman a las funciones que tienen graficar, en la clase Nodo tiene graficar que conecta dos nodos
        //El nodo padre y el nombre de esta clase, a su vez tambien grafica sus dos expresiones.
        grafico.append(super.graficar(idPadre));
        grafico.append(izquierda.graficar(this.getId()));
        grafico.append(derecha.graficar(this.getId()));
        return grafico.toString();
    }

    @Override
    public Number darResultado() {
        return this.getResultado();
    }
}
