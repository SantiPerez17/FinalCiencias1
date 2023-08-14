# Ciencias 1 - Calculadora

### Descripción

Esta aplicación, denominada "Ciencias 1 - Calculadora", es una calculadora avanzada que es capaz de realizar operaciones aritméticas, junto con otras funciones adicionales. La aplicación utiliza análisis léxico y sintáctico para interpretar y procesar las operaciones proporcionadas por el usuario.

### Lexer (`MiLexico`)

El analizador léxico, o lexer, es responsable de tomar una cadena de entrada y dividirla en tokens. Estos tokens representan diferentes tipos de información en la cadena de entrada, como números, operadores y funciones.

#### Tokens

- **Operadores**: SUMA (+), RESTA (-), MULT (*), DIV (/)
- **Números**: INT (números enteros), FLOAT (números decimales)
- **Funciones adicionales**: POTENCIA, RAIZ, ABS
- **Otros**: PARENTESISO, PARENTESISC, COMA
- **Errores**: ERROR

**Ejemplo**:
Si proporcionas la entrada "3+5", el lexer producirá los siguientes tokens:
- INT: 3
- SUMA: +
- INT: 5

### Parser (`MiParser`)

El analizador sintáctico, o parser, toma los tokens producidos por el lexer y los organiza en una estructura, generalmente un árbol, que representa la semántica de la entrada. En el caso de esta calculadora, el parser construye un Árbol Sintáctico Abstracto (AST) de la operación para facilitar su evaluación.

#### Gramática libre de contexto

```
resultado ::= expresion
expresion ::= expresion SUMA termino
           |  expresion RESTA termino
           |  termino
termino    ::= termino MULT menos_unario
           |  termino DIV menos_unario
           |  menos_unario
menos_unario ::= RESTA menos_unario
              |  factor
factor     ::= INT
           |  FLOAT
           |  PARENTESISO expresion PARENTESISC
           |  funcionesAdicionales
funcionesAdicionales ::= ABS PARENTESISO expresion PARENTESISC
                       |  POTENCIA PARENTESISO expresion COMA expresion PARENTESISC
                       |  RAIZ PARENTESISO expresion COMA expresion PARENTESISC
```

### Árbol Sintáctico Abstracto (AST)

El AST es una herramienta que abstrae el árbol de derivación y lo visualiza utilizando Graphviz.

**Ejemplo**:
Para la operación "3+5", el AST generado será:

```
graph G {
    nodo_resultado[label="Resultado  8"]
    nodo_1853053672[label=" - [ Suma ] - "]
    nodo_resultado--nodo_1853053672
    nodo_1546182157[label="ConstanteEntera  Valor: 3"]
    nodo_1853053672--nodo_1546182157
    nodo_1149118037[label="ConstanteEntera  Valor: 5"]
    nodo_1853053672--nodo_1149118037
}
```

### Historial

El historial es una característica que permite a los usuarios ver un registro de las operaciones o expresiones que se han calculado.

**Ejemplo**:
Después de calcular "3+5", se añadirá una entrada al historial que mostrará "3+5 = 8".

---

# Carpeta del final de la materia.
Calculadora con ER y GLC para validaciones, y para muestra graphviz.
Se utilizaron las librerias Jflex ( https://www.jflex.de/ ) para el analizador léxico y JCup para el analizador Sintactico.
Se utilizó Graphviz ( https://graphviz.org/ ) para el armado del arbol AVL.

### Vista Simple del programa.

![image](https://github.com/SantiPerez17/FinalCiencias1/assets/55918957/a4028093-ab86-4a3f-b3cb-5c69b370846e)

---

## Funcionalidad de los Botones

1. **Ver Tokens**: Al hacer clic en este botón, el programa analiza la expresión ingresada y muestra una lista de tokens identificados.  
   _Ejemplo:_ `3 + 5` mostrará:
   ```
   Analisis léxico iniciado: 
   Token: [INT] -> (3) pos (columna:1)
   Token: [SUMA] -> (+) pos (columna:2)
   Token: [INT] -> (5) pos (columna:3)
   Análisis léxico terminado.
   ```
   
- Deteccion de tokens utilizando Expresiones Regulares.
  
  ![image](https://github.com/SantiPerez17/FinalCiencias1/assets/55918957/3c4d9c71-b790-4333-8111-9786f0d3d86a).

2. **Ver Parsing**: Este botón inicia el análisis sintáctico de la expresión. Al finalizar, se mostrará un recorrido que describe el proceso de parsing.  
   _Ejemplo:_ para `3 + 5`, se mostrará el proceso de derivación en base a la gramática definida.

   - Gramáticas libres de contexto para las reglas de la calculadora.
- Recorrido de las diferentes reglas.

![image](https://github.com/SantiPerez17/FinalCiencias1/assets/55918957/58689d48-4980-4676-b979-189e1b699ec4)

4. **Generar Árbol**: Este botón crea un árbol de derivación usando Graphviz y representa la estructura jerárquica de la expresión.

- Armado de Árbol donde se simplifican los pasos.

![image](https://github.com/SantiPerez17/FinalCiencias1/assets/55918957/525ef54b-b2a3-40f1-8111-8a0fdba9b422)


5. **Resolver**: Evalúa la expresión y muestra el resultado final. Por ejemplo, para la entrada `3 + 5`, el resultado será `8`.
  - Generación de operaciones arismeticas y funciones.
  - Resultado final.

 ![image](https://github.com/SantiPerez17/FinalCiencias1/assets/55918957/8164fdb6-7423-4f05-9f19-ff3aef4dc852)


6. **Historial**: Muestra un registro de las expresiones calculadas anteriormente y sus resultados.

  ![image](https://github.com/SantiPerez17/FinalCiencias1/assets/55918957/0719ed04-b8dc-4035-ad36-63be4ec5b15b)



---

### Contribuciones

Este proyecto fue contribuido por SantiPerez17
