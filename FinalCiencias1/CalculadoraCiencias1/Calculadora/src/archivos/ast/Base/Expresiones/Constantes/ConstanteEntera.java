package archivos.ast.Base.Expresiones.Constantes;

public class ConstanteEntera extends Constante{

    public ConstanteEntera(String valor, String nombre) {super(valor, nombre);
    }

    @Override
    public Number darResultado() {
        return Integer.parseInt(String.valueOf(this.getValor()));
    }
}
