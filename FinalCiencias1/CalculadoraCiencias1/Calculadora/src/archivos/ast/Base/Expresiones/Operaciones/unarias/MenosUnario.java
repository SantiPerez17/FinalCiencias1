package archivos.ast.Base.Expresiones.Operaciones.unarias;

import archivos.ast.Base.Expresiones.Expresion;


public class MenosUnario extends OperacionUnaria{
    public MenosUnario(String nombre, Expresion expresion) {
        super(nombre, expresion);
    }

    @Override
    public Number darResultado() {
        if (tipo(this.getExpresion()).equals("Double")){
            return  -castDouble(this.getExpresion());
        }else {
            return -castInt(this.getExpresion());
        }
    }
}
