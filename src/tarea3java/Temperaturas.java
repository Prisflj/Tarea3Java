package tarea3java;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Temperaturas extends JFrame {

    private double[] temperaturas = new double[6];
    private int indice = 0;

    private JTextArea area = new JTextArea(12, 30);

    public Temperaturas() {
        setTitle("Analizador de Temperaturas");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton btnCargar = new JButton("Cargar Datos");
        JButton btnCalcular = new JButton("Calcular Estadísticas");

        JPanel panel = new JPanel();
        panel.add(btnCargar);
        panel.add(btnCalcular);

        area.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btnCargar.addActionListener(e -> cargarDatos());
        btnCalcular.addActionListener(e -> calcularEstadisticas());
    }

    private void cargarDatos() {
        File archivo = new File("temp/temperaturas.txt");

        try {
            if (!archivo.exists()) {
                JOptionPane.showMessageDialog(this,
                        "No existe temp/temperaturas.txt");
                return;
            }

            Scanner sc = new Scanner(archivo);
            indice = 0;

            while (sc.hasNextLine() && indice < 6) {
                String linea = sc.nextLine().trim();

                if (!linea.isEmpty()) {
                    temperaturas[indice] =
                            Double.parseDouble(linea);
                    indice++;
                }
            }

            sc.close();

            if (indice != 6) {
    indice = 0;
    JOptionPane.showMessageDialog(this,
            "El archivo debe contener 6 temperaturas.");
    return;
}

            JOptionPane.showMessageDialog(this,
                    "Los datos fueron cargados en memoria.");

        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al leer el archivo.");
        }
    }

    private void calcularEstadisticas() {
        if (indice != 6) {
            JOptionPane.showMessageDialog(this,
                    "Primero debes cargar los datos.");
            return;
        }

        double suma = 0;
        int calidas = 0;
        int frescas = 0;

        for (int i = 0; i < temperaturas.length; i++) {
            suma += temperaturas[i];

            if (temperaturas[i] >= 25.0) {
                calidas++;
            } else {
                frescas++;
            }
        }

        double promedio = suma / temperaturas.length;

        area.setText("Análisis Climático\n");
        area.append("------------------\n\n");
        area.append("Promedio de Temperatura: "
                + String.format("%.2f", promedio) + "\n\n");
        area.append("Días Cálidos (>= 25°): "
                + calidas + "\n\n");
        area.append("Días Frescos (< 25°): "
                + frescas + "\n");
    }
}

