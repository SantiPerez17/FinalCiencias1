package archivos.sintactico;

import java.io.FileReader;
import java.io.StringWriter;

import archivos.lexico.MiLexico;
import java_cup.runtime.Symbol;

/**
 *
 * @author itt
 */
public class EjemploJavaCup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here

        try{
            String path = "C:\\Users\\santi\\Desktop\\FinalCiencias1\\CalculadoraCiencias1\\pruebas.txt";
            System.out.println("Análisis sintáctico iniciado:");
            MiLexico lexer = new MiLexico(new FileReader(path));
            MiParser parser = new MiParser(lexer);
            Symbol p = parser.parse();
            StringWriter Reglas = new StringWriter();
            Reglas.append(parser.reglas);
            System.out.println(Reglas+"\nAnálisis sintáctico finalizado.");}
        catch (Exception e){
            System.out.println(e.getMessage());};

    }
}