package archivos.ast.Base.Expresiones.Operaciones.binarias.arismeticos;

import archivos.ast.Base.Expresiones.Expresion;
import archivos.ast.Base.Expresiones.Operaciones.binarias.OperacionBinaria;

public class Resta extends OperacionBinaria {

    public Resta(String nombre, Expresion izquierda, Expresion derecha) {
        super(nombre, izquierda, derecha);
    }

    @Override
    public Number darResultado() {
        Number c;
        if (tipo(this.getDerecha()).equals("Double") | tipo(this.getIzquierda()).equals("Double") ){
            c =castDouble(this.getIzquierda())-castDouble(this.getDerecha());
        }
        else{
            c = castInt(this.getIzquierda())-castInt(this.getDerecha());
        }
        return c;
    }

}