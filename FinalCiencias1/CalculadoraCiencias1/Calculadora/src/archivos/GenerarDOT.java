package archivos;

import archivos.ast.Base.Resultado;
import archivos.lexico.MiLexico;
import archivos.sintactico.MiParser;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

public class GenerarDOT {

    public static void main(String[] args) throws Exception {
        final FileReader entrada = new FileReader("C:\\Users\\santi\\Desktop\\FinalCiencias1\\CalculadoraCiencias1\\pruebas.txt");
        final MiLexico lexico = new MiLexico(entrada);
        final MiParser sintactico= new MiParser(lexico);
        final Resultado resultado = (Resultado) sintactico.parse().value;

        try {
            PrintWriter grafico = new PrintWriter(new FileWriter("arbol.dot"));
            grafico.println(resultado.graficar());
            grafico.close();
            String cmdDot = "dot -Tpng arbol.dot -o arbol.png";
            Runtime.getRuntime().exec(cmdDot);
            Desktop.getDesktop().open(new File("arbol.png"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
