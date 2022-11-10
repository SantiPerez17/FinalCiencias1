package archivos.ast.Base.Expresiones.Operaciones.unarias;

import archivos.ast.Base.Expresiones.Expresion;

public abstract class OperacionUnaria extends Expresion {
    private Expresion expresion;

    public OperacionUnaria(String nombre, Expresion expresion) {
        super(nombre);
        this.expresion = expresion;
    }


    public Expresion getExpresion() {
        return expresion;
    }

    public void setExpresion(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public String getEtiqueta() {
        return " - [ " + this.getNombre() + " ] - ";
    }

    @Override
    public String graficar(String idPadre) {
        StringBuilder grafico = new StringBuilder();
        //Se llaman a las funciones que tienen graficar, en la clase Nodo tiene graficar que conecta dos nodos
        //El nodo padre y el nombre de esta clase, a su vez tambien grafica su expresion mandando el ID.
        grafico.append(super.graficar(idPadre));
        grafico.append(expresion.graficar(this.getId()));
        return grafico.toString();
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

    @Override
    public Number darResultado() {
        return this.expresion.darResultado();
    }


}