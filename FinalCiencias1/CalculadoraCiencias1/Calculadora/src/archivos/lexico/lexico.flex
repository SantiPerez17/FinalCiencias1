package archivos.lexico;

%%

%public
%class MiLexico
%unicode
%cup
%type MiToken
%line
%column

%{
    /*************************************************************************
    * En esta sección se puede incluir código que se copiará textualmente
    * como parte de la definición de la clase del analizador léxico.
    * Típicamente serán variables de instancia o nuevos métodos de la clase.
    *************************************************************************/

    int string_yyline = 1;
    int string_yycolumn = 1;
    int cota_int = 20;
    int cota_float = 25;


    StringBuffer string = new StringBuffer();

    private MiToken token(String nombre) {
        return new MiToken(nombre, this.yyline, this.yycolumn);
    }
    private MiToken token(String nombre, Object valor) {
        return new MiToken(nombre, this.yyline, this.yycolumn, valor);
    }

    private MiToken token(String nombre, int line, int column, Object valor) {
        return new MiToken(nombre, line, column, valor);
    }
%}

//Factorial = "fact"|"FACTORIAL"|"FACT"
Potencia = "potencia"|"POTENCIA"
Raiz = "raiz"|"RAIZ"
Abs = "abs"|"ABS"
Int= \d+
Float = \d+\.\d+
LineTerminator = \r|\n|\r\n
WhiteSpace     =  \s | {LineTerminator} | [\t\f]
%%
<YYINITIAL> {

  /* "operators" */
  "+" {return token("SUMA", yytext()); }
  "-" {return token("RESTA", yytext());}
  "*" {return token("MULT", yytext());}
  "/" {return token("DIV", yytext());}

      {Int} {
          int a = yytext().length();
          if ( a < cota_int ) {return token("INT", yytext());}
          else{
              return token("ERROR", "Error supera cantidad maxima de caracteres permitidos");
          }
      }

      {Float} {
          int a = yytext().length();
          if ( a < cota_float) {return token("FLOAT", yytext());}
          else{
              return token("ERROR", "Error supera cantidad maxima de caracteres permitidos");}}

    "\," {return token("COMA", yytext());}

    "\(" {return token("PARENTESISO", yytext());}

    "\)" {return token("PARENTESISC", yytext());}

    {Potencia} {return token("POTENCIA", yytext());}
    {Raiz} {return token("RAIZ", yytext());}
    {Abs}  {return token("ABS",yytext());}
    //{Factorial} {return token("FACTORIAL",yytext());}




  /* whitespace */
  {WhiteSpace}                   { /* ignore */ }
}

<<EOF>>     {return token("EOF");}
/* error fallback */
[^]  { return token("ERROR", "Illegal character <"+yytext()+">"); }
