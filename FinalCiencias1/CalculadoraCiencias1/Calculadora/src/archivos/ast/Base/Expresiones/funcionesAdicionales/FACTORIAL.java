package archivos.ast.Base.Expresiones.funcionesAdicionales;

import archivos.ast.Base.Expresiones.Expresion;

public class FACTORIAL extends Expresion {
    private Expresion expresion;

    public FACTORIAL(String nombre, Expresion expresion) {
        super(nombre);
        this.expresion=expresion;
    }

    public static int factorial( int numero ) {
        int fact = 1;
        for (int i = 1; i <= numero; i++) {
            fact = i * fact;
        }
        return fact;
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
        return factorial(this.expresion.darResultado().intValue());
    }
}
