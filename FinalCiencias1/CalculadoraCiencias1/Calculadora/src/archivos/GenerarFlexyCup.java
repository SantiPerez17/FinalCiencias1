/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos;


import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java_cup.Main;


public class GenerarFlexyCup {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //si le cambio el path se rompe asique lo dejo así.
        String path = "C:\\Users\\santi\\Desktop\\FinalCiencias1\\CalculadoraCiencias1\\Calculadora\\src\\archivos\\lexico\\lexico.flex";
        generarLexer(path);
        String[] param = new String[5];
        param[0] = "-destdir";
        param[1] = "C:\\Users\\santi\\Desktop\\FinalCiencias1\\CalculadoraCiencias1\\Calculadora\\src\\archivos\\sintactico";
        param[2] = "-parser";
        param[3] = "MiParser";
        param[4] = "C:\\Users\\santi\\Desktop\\FinalCiencias1\\CalculadoraCiencias1\\Calculadora\\src\\archivos\\sintactico\\parser.cup";
        generarParser(param);
    }

    
    /**
     *
     * @param path
     */
    public static void generarLexer(String path){
        File file=new File(path);
        jflex.generator.LexGenerator generator = new jflex.generator.LexGenerator(file);
        generator.generate();
    }
    public static void generarParser(String[] param){
        try {
            Main.main(param);
        } catch (IOException ex) {
            Logger.getLogger(GenerarFlexyCup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GenerarFlexyCup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
