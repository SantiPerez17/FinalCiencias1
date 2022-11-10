/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package archivos.lexico;

import java.util.Arrays;
import archivos.sintactico.MiParserSym;
import java_cup.runtime.Symbol;


public class MiToken extends Symbol{

    public final String nombre;
    public final int linea;
    public final int columna;
    public final Object valor;

    MiToken (String nombre) {
        this(nombre, null);
    }

    MiToken (String nombre, Object valor) {
        this(nombre, -1, -1, valor);
    }

    public MiToken(String nombre, int linea, int columna) {
        this(nombre, linea, columna, null);
    }

    public MiToken(String nombre, int linea, int columna, Object valor) {
        super(Arrays.asList(MiParserSym.terminalNames).indexOf(nombre), linea, columna, valor);
        this.nombre = nombre;
        this.valor = valor;
        this.linea = linea;
        this.columna = columna;
    }

    @Override
    public String toString() {
        String posicion = "";
        if (this.linea != -1 && this.columna != -1)
            posicion = " pos (columna:" + (this.columna+1) + ")";
        if (valor == null)
            return "[" + this.nombre + "]" + posicion;
        else
            return "[" + this.nombre + "] -> (" + this.valor + ")" + posicion;
    }
}
