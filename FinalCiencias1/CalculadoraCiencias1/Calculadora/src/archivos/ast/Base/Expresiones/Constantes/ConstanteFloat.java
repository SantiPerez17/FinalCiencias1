package archivos.ast.Base.Expresiones.Constantes;


public class ConstanteFloat extends Constante{
    public ConstanteFloat(String valor, String nombre) {
        super(valor, nombre);
    }

    @Override
    public Number darResultado() {
        return Double.parseDouble(String.valueOf(this.getValor()));
    }
}
