package archivos.ast.Base.Expresiones.funcionesAdicionales;
import archivos.ast.Base.Expresiones.Expresion;
public class POTENCIA extends Expresion {
    private Expresion arg1;
    private Expresion arg2;
    public POTENCIA(String nombre,Expresion a,Expresion b) {
        super(nombre); this.arg1=a; this.arg2=b;}
    @Override
    public String graficar(String idPadre) {
        StringBuilder resultado = new StringBuilder();
        resultado.append(super.graficar(idPadre));
        resultado.append(String.format("%1$s[label=\"%2$s\"]\n", this.getId()+1, "BASE"));
        resultado.append(String.format("%1$s--%2$s\n", this.getId(), this.getId()+1));
        resultado.append(this.arg1.graficar(this.getId()+1));
        resultado.append(String.format("%1$s[label=\"%2$s\"]\n", this.getId()+2, "EXPONENTE"));
        resultado.append(String.format("%1$s--%2$s\n", this.getId(), this.getId()+2));
        resultado.append(this.arg2.graficar(this.getId()+2));
        return resultado.toString();
    }
    @Override
    public Number darResultado() {
        return Math.pow(arg1.darResultado().doubleValue(),arg2.darResultado().doubleValue());
    }
}
