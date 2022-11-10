package archivos.ast.Base.Expresiones.funcionesAdicionales;

import archivos.ast.Base.Expresiones.Expresion;

public class ABS extends Expresion {
    private Expresion expresion;

    public ABS(String nombre,Expresion expresion) {
        super(nombre);
        this.expresion=expresion;
    }

    @Override
    public String graficar(String idPadre) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(super.graficar(idPadre));
        resultado.append(this.expresion.graficar(this.getId()));
        return resultado.toString();
    }

    @Override
    public Number darResultado() {
        return Math.abs(expresion.darResultado().doubleValue());
    }
}
