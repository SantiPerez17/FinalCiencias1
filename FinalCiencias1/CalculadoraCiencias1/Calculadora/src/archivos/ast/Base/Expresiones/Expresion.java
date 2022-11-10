package archivos.ast.Base.Expresiones;

import archivos.ast.Base.Nodo;


public abstract class Expresion extends Nodo {

    public Expresion(String nombre) {
        super(nombre);
    }

    public String getEtiqueta() {
       return "- [ " + this.getNombre() + " ] - ";
    }


}
