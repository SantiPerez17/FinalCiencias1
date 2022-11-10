package archivos;

import archivos.ast.Base.Resultado;
import archivos.lexico.MiLexico;
import archivos.lexico.MiToken;
import archivos.sintactico.MiParser;
import java_cup.runtime.Symbol;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AbrirParteGráfica extends JFrame implements ActionListener {

    public AbrirParteGráfica() {

        JFrame frame = new JFrame("Ciencias 1 - Calculadora");

        ImageIcon img = new ImageIcon("iconocalculador.png");
        frame.setIconImage(img.getImage());
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new BorderLayout());
        frame.setSize(500, 180);
        frame.setLocationRelativeTo(null);
        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.CENTER);

        // Se crea un botón para generar y mostrar los tokens del código ingresado.
        JButton btn3 = new JButton("Ver Tokens");
        btn3.setBackground(new java.awt.Color(31, 82, 7));
        btn3.setForeground(Color.WHITE);
        btn3.addActionListener(this);
        panel1.add(btn3);

        // Se crea un botón para generar y mostrar el parser del código ingresado.
        JButton btn4 = new JButton("Ver Parsing");
        btn4.setBackground(Color.BLACK);
        btn4.setForeground(Color.WHITE);
        btn4.addActionListener(this);
        panel1.add(btn4);

        // Se crea un botón para generar y mostrar el árbol AST del código ingresado.
        JButton btn5 = new JButton("Generar Arbol");
        btn5.setBackground(new java.awt.Color(31, 82, 7));
        btn5.setForeground(Color.WHITE);
        btn5.addActionListener(this);
        panel1.add(btn5);

        // Se crea un botón para generar y mostrar el código IR/LLVM.
        JButton btn6 = new JButton("Resolver");
        btn6.setBackground(Color.BLACK);
        btn6.setForeground(Color.WHITE);
        btn6.addActionListener(this);
        panel1.add(btn6);

        JButton btn7 = new JButton("Historial");
        btn7.setBackground(Color.BLACK);
        btn7.setForeground(Color.WHITE);
        btn7.addActionListener(this);
        panel1.add(btn7);


        // Se crea el editor de texto y se agrega a un scroll.
        txp = new JTextPane();
        txp.setBackground(Color.WHITE);
        txp.setForeground(Color.BLACK);
        JScrollPane jsp = new JScrollPane();
        jsp.setViewportView(txp);
        panel2.add(jsp, BorderLayout.CENTER);
        String path = archivo.getAbsolutePath();
        String contenido = getArchivo(path);
        txp.setFont(new Font("Dialog", Font.BOLD, 20));
        txp.setText(contenido);
        frame.setVisible(true);

    }

    //------------------------------Action Performed-------------------------------//
    public void actionPerformed(ActionEvent e) {

        JButton btn = (JButton) e.getSource();
        if (btn.getText().equals("Abrir")) {
            if (abrirArchivo == null) abrirArchivo = new JFileChooser();
            // Con esto solamente podemos abrir archivos.
            abrirArchivo.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int seleccion = abrirArchivo.showOpenDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                File f = abrirArchivo.getSelectedFile();
                archivo = f;
                try {
                    String nombre = f.getName();
                    String path = f.getAbsolutePath();
                    String contenido = getArchivo(path);
                    // Colocamos en el título de la aplicación el nombre del archivo.
                    this.setTitle(nombre);
                    // En el editor de texto colocamos su contenido.
                    txp.setBackground(Color.orange);
                    txp.setBackground(Color.BLACK);
                    txp.setText(contenido);
                } catch (Exception exp) {
                }
            }
        } else if (btn.getText().equals("Guardar")) {
            // Se guardan los cambios realizados en el archivo.
            try {
                setArchivo(archivo, txp.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, ex.getMessage());
            }
        } else if (btn.getText().equals("Ver Tokens")) {
            try {
                setArchivo(archivo, txp.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JFrame frame2 = new JFrame("Tokens");
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setLayout(new BorderLayout());
            frame2.setSize(400, 500);
            frame2.setLocationRelativeTo(null);
            txp2 = new JTextPane();
            txp2.setFont(new Font("Dialog", Font.BOLD, 15));
            txp2.setEditable(false);
            txp2.setForeground(Color.BLACK);
            JScrollPane jsp2 = new JScrollPane();
            jsp2.setViewportView(txp2);
            frame2.add(jsp2, BorderLayout.CENTER);
            frame2.setVisible(true);
            try {
                Compilar(txp.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
            }
        } else if (btn.getText().equals("Ver Parsing")) {
            try {
                setArchivo(archivo, txp.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JFrame frame2 = new JFrame("Parser");
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setLayout(new BorderLayout());
            frame2.setSize(900, 600);
            frame2.setLocationRelativeTo(null);
            txp3 = new JTextPane();
            txp3.setForeground(Color.BLACK);
            txp3.setEditable(false);
            txp4 = new JTextPane();
            txp4.setForeground(Color.BLACK);
            JScrollPane jsp2 = new JScrollPane();
            JScrollPane jsp3 = new JScrollPane();
            jsp2.setViewportView(txp3);
            jsp3.setViewportView(txp4);
            frame2.add(jsp2, BorderLayout.CENTER);
            //frame3.add(jsp3, BorderLayout.CENTER);
            frame2.setVisible(true);
            //frame3.setVisible( true );
            try {
                Parsing(txp.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (btn.getText().equals("Generar Arbol")) {
            try {
                setArchivo(archivo, txp.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                Graficar(txp.getText());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (btn.getText().equals("Resolver")) {
            try {
                setArchivo(archivo, txp.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JFrame frame4 = new JFrame("RESULTADO");
            frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame4.setLayout(new BorderLayout());
            frame4.setSize(350, 120);
            frame4.setLocationRelativeTo(null);
            txp5 = new JTextPane();
            txp5.setFont(new Font("Dialog", Font.BOLD, 38));
            txp5.setEditable(false);
            txp5.setForeground(Color.BLACK);
            JScrollPane jsp4 = new JScrollPane();
            jsp4.setViewportView(txp5);
            frame4.add(jsp4, BorderLayout.CENTER);
            frame4.setVisible(true);
            try {
                GenerarResultado();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(rootPane, "Error: " + ex.getMessage());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (btn.getText().equals("Historial")) {
            try {
                setArchivo(archivo, txp.getText());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            JFrame frame5 = new JFrame("HISTORIAL");
            frame5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame5.setLayout(new BorderLayout());
            frame5.setSize(600, 300);
            frame5.setLocationRelativeTo(null);
            txp6 = new JTextPane();
            txp6.setFont(new Font("Dialog", Font.BOLD, 20));
            txp6.setEditable(false);
            txp6.setForeground(Color.BLACK);
            JScrollPane jsp5 = new JScrollPane();
            jsp5.setViewportView(txp6);
            frame5.add(jsp5, BorderLayout.CENTER);
            frame5.setVisible(true);
            try {
                VerHistorial();
            } catch (Exception ex) {
                ex.printStackTrace();
            }


        }
    }

    //-------------------------Se obtiene el contenido del archivo----------------//
    public String getArchivo(String ruta) {
        FileReader fr = null;
        BufferedReader br = null;
        // Cadena de texto donde se guardara el contenido del archivo.
        String contenido = "";
        try {
            // La ruta puede ser de tipo String o tipo File.
            fr = new FileReader(ruta);
            br = new BufferedReader(fr);
            String linea;
            // Obtenemos el contenido del archivo línea por línea.
            while ((linea = br.readLine()) != null) {
                contenido += linea + "\n";
            }
        } catch (Exception e) {
        }
        // Finally se utiliza para que si todo ocurre correctamente o si ocurre algún error se cierre el archivo que anteriormente abrimos.
        finally {
            try {
                br.close();
            } catch (Exception ex) {
            }
        }
        return contenido;
    }


    public void setArchivo(File archivo, String contenido) throws IOException {

        FileWriter fw = new FileWriter(archivo.getAbsolutePath());
        String texto = contenido;
        System.out.println(texto);
        PrintWriter imprime = new PrintWriter(fw);
        imprime.print(texto);
        fw.close();
    }

    public void Compilar(String contenido) throws IOException {
        try {
            FileReader entrada = new FileReader(archivo);
            MiLexico lexico = new MiLexico(entrada);
            StringBuilder tokens = new StringBuilder();
            tokens.append("Analisis léxico iniciado: \n\n");
            StringBuilder errores = new StringBuilder("Errores: ");
            while (!lexico.yyatEOF()) {
                Symbol token = lexico.next_token();
                if (token.value == null) {
                    break;
                }
                if (Objects.equals(((MiToken) token).nombre, "ERROR")) {
                    errores.append("\n").append(((MiToken) token).valor).append(" - Linea: ").append(((MiToken) token).linea + 1);
                } else {
                    tokens.append("Token: ").append(token).append("\n\n");
                }
            }
            if (errores.toString().equals("Errores: ")) {
                tokens.append("Análisis léxico terminado.");
                txp2.setForeground(Color.BLACK);
                txp2.setText(tokens.toString());
            } else {
                errores.append("\n\nEl análisis se ejecuto con errores.");
                JOptionPane.showMessageDialog(rootPane, errores.toString());
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane, "Error" + ex.getMessage());
        }
    }

    public void Parsing(String contenido) throws IOException, Exception {

        try {
            MiLexico lexer = new MiLexico(new FileReader(archivo));
            MiParser parser = new MiParser(lexer);
            parser.reglas = "";
            StringBuilder Reglas = new StringBuilder();
            StringBuilder Simbolos = new StringBuilder();
            Symbol p = parser.parse();
            Reglas.append(parser.reglas);
            txp3.setText("Analisis Sintactico Iniciado: \n"+parser.reglas+"\n\nAnálisis Sintactico Terminado.");
            txp3.setForeground(Color.BLACK);
            txp3.setFont(new Font("Dialog", Font.BOLD, 18));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(rootPane, "Error : " + e.getMessage());
        }

    }

    public void Graficar(String contenido) throws IOException, Exception {

        try {
            MiLexico lexer = new MiLexico(new FileReader(archivo));
            MiParser parser = new MiParser(lexer);
            final Resultado resultado = (Resultado) parser.parse().value;
            PrintWriter grafico = new PrintWriter(new FileWriter("arbol.dot"));
            grafico.println(resultado.graficar());
            grafico.close();
            String cmdDot = "dot -Tpng arbol.dot -o arbol.png";
            Runtime.getRuntime().exec(cmdDot);

        } catch (Exception e) {
            UIManager.put("OptionPane.background", Color.GRAY);
            UIManager.put("OptionPane.messagebackground", Color.GRAY);
            UIManager.put("Panel.background", Color.GRAY);
            JOptionPane.showMessageDialog(rootPane, "Error : " + e.getMessage());
        }

    }

    public void GenerarResultado() throws Exception {

        try {
            MiLexico lexer = new MiLexico(new FileReader(archivo));
            MiParser parser = new MiParser(lexer);
            final Resultado resultado = (Resultado) parser.parse().value;
            PrintWriter pw = new PrintWriter(new FileWriter("arbol.dot"));
            pw.println(resultado.graficar());
            pw.close();
            String cmdDot = "dot -Tpng arbol.dot -o arbol.png";
            Runtime.getRuntime().exec(cmdDot);
            System.out.println("Arbol Generado");
            Scanner input = new Scanner(new FileReader(archivo));
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (resultado.toString().contains(".") || resultado.toString().contains(",")) {
                    DecimalFormat df = new DecimalFormat("0.######");
                    historial.add(line + " = " + df.format(resultado.darResultado())+"\n");
                } else {
                    historial.add(line + " = " + resultado.darResultado().toString()+"\n");
                }
            }
            input.close();
            String f;
            Number res = resultado.darResultado();
            if (res.toString().contains(".") || res.toString().contains(",")) {
                DecimalFormat df = new DecimalFormat("0.######");
                f = df.format(res);
            } else {
                f = resultado.darResultado().toString();
            }
            pw = new PrintWriter(new FileWriter("resultado.txt"));
            pw.println(f);
            pw.println(((Object) resultado.darResultado()).getClass().getSimpleName());
            txp5.setText(f);
            pw.close();
        } catch (Exception e) {
            UIManager.put("OptionPane.background", Color.GRAY);
            UIManager.put("OptionPane.messagebackground", Color.GRAY);
            UIManager.put("Panel.background", Color.GRAY);
            JOptionPane.showMessageDialog(rootPane, "Error : " + e.getMessage());
        }
    }

    public void VerHistorial() {
        String a = null;
        try {
            a = "";
            for (String cadena : historial) {
                a += cadena + "\n";
            }
        } catch (Exception e) {
            UIManager.put("OptionPane.background", Color.GRAY);
            UIManager.put("OptionPane.messagebackground", Color.GRAY);
            UIManager.put("Panel.background", Color.GRAY);
            JOptionPane.showMessageDialog(rootPane, "Error : " + e.getMessage());
        }
        txp6.setText(a);
    }

    public static void main(String[] arg) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        new AbrirParteGráfica();

    }

    JTextPane txp;
    JTextPane txp2;
    JTextPane txp3;
    JTextPane txp4;
    JTextPane txp5;
    JTextPane txp6;
    JFileChooser abrirArchivo;
    File archivo = new File("C:\\Users\\santi\\Desktop\\FinalCiencias1\\CalculadoraCiencias1\\pruebas.txt");
    File llvm = new File("programa.txt");
    //cambiar por pruebas.txt para pruebas en la gui
    //hay que copiar el path del archivo pruebas.txt para que pueda trabajar el lexer-parser.

    ArrayList<String> historial = new ArrayList<>();
}
