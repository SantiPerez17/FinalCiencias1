package archivos.ast.Base;
import archivos.ast.Base.Expresiones.Expresion;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

public class Resultado extends Nodo{
    public Expresion expresion;
    public Resultado(String nombre, Expresion expresion) {super(nombre); this.expresion = expresion;}
    public String getId() {
        return "nodo_resultado";
    }
    @Override
    public String getEtiqueta(){
        return String.format("%s  \n %s", this.getNombre(), this.expresion.darResultado());
    }
    public String graficar() {
        // Acá se dispara la invocación a los métodos graficar() de los nodos.
        // Como no tiene padre, se inicia pasando null.
        StringBuilder resultado = new StringBuilder();
        resultado.append("graph G {");
        resultado.append(this.graficar(null));
        resultado.append(this.expresion.graficar(this.getId()));
        resultado.append("}");
        return resultado.toString();
    }
    @Override
    public Number darResultado(){
            return this.expresion.darResultado();
    }
}

