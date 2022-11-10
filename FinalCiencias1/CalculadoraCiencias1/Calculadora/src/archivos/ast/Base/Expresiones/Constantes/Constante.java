package archivos.ast.Base.Expresiones.Constantes;

import archivos.ast.Base.Expresiones.Expresion;

import java.text.NumberFormat;

public class Constante extends Expresion {
    private final Object valor;

    public Constante(String valor, String nombre) {
        super(nombre);
        this.valor = valor ;
    }
    public Object getValor() {
        return valor;
    }

    @Override
    public String getEtiqueta() {
        return String.format("%s  \n Valor: %s", this.getNombre(), getValor());
    }

    @Override
    public Number darResultado() {
        return (Number) valor;
    }

}
