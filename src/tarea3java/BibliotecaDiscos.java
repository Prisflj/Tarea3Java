
package tarea3java;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Scanner;

public class BibliotecaDiscos extends JFrame {

    private String[] titulos = new String[5];
    private int[] anios = new int[5];
    private int cantidad = 0;

    private JTextField txtTitulo = new JTextField(15);
    private JTextField txtAnio = new JTextField(6);
    private JLabel lblAnioActual = new JLabel("Año actual: ");
    private JTextArea area = new JTextArea(10, 30);

    private final File archivo = new File("temp/discos.csv");

    public BibliotecaDiscos() {
        setTitle("Biblioteca de Discos Musicales");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        cargarDiscos();

        JButton btnBuscar = new JButton("Buscar");
        JButton btnActualizar = new JButton("Actualizar Registro");

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));

        panel.add(new JLabel("Buscar Álbum:"));
        panel.add(txtTitulo);

        panel.add(btnBuscar);
        panel.add(lblAnioActual);

        panel.add(new JLabel("Nuevo Año:"));
        panel.add(txtAnio);

        panel.add(new JLabel(""));
        panel.add(btnActualizar);

        area.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btnBuscar.addActionListener(e -> buscarDisco());
        btnActualizar.addActionListener(e -> actualizarAnio());

        mostrarDiscos();
    }

    private void cargarDiscos() {
        cantidad = 0;

        try (Scanner sc = new Scanner(archivo)) {
            while (sc.hasNextLine() && cantidad < titulos.length) {
                String linea = sc.nextLine().trim();

                if (linea.isEmpty()) {
                    continue;
                }

                String[] datos = linea.split(",");

                if (datos.length != 2) {
                    JOptionPane.showMessageDialog(this,
                            "Formato incorrecto en discos.csv");
                    cantidad = 0;
                    return;
                }

                titulos[cantidad] = datos[0].trim();
                anios[cantidad] = Integer.parseInt(datos[1].trim());
                cantidad++;
            }

            if (cantidad != 5) {
                JOptionPane.showMessageDialog(this,
                        "El archivo debe contener 5 discos.");
                cantidad = 0;
            }

        } catch (IOException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al leer discos.csv: " + ex.getMessage());
            cantidad = 0;
        }
    }

    private void mostrarDiscos() {
        area.setText("Biblioteca de Discos Musicales\n\n");

        for (int i = 0; i < cantidad; i++) {
            area.append(titulos[i] + " | Año: "
                    + anios[i] + "\n");
        }
    }

    private int buscarIndice(String titulo) {
        for (int i = 0; i < cantidad; i++) {
            if (titulos[i].equalsIgnoreCase(titulo)) {
                return i;
            }
        }

        return -1;
    }

    private void buscarDisco() {
        String titulo = txtTitulo.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Escribe el título del álbum.");
            return;
        }

        int indice = buscarIndice(titulo);

        if (indice != -1) {
            lblAnioActual.setText("Año actual: " + anios[indice]);
            JOptionPane.showMessageDialog(this,
                    "Álbum: " + titulos[indice]
                    + "\nAño de lanzamiento: " + anios[indice]);
        } else {
            lblAnioActual.setText("Año actual: ");
            JOptionPane.showMessageDialog(this,
                    "Álbum no encontrado.");
        }
    }

    private void actualizarAnio() {
        String titulo = txtTitulo.getText().trim();

        if (titulo.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Escribe el título del álbum.");
            return;
        }

        int indice = buscarIndice(titulo);

        if (indice == -1) {
            JOptionPane.showMessageDialog(this,
                    "Álbum no encontrado.");
            return;
        }

        int nuevoAnio;

        try {
            nuevoAnio = Integer.parseInt(txtAnio.getText().trim());

            if (nuevoAnio <= 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Escribe un año válido.");
            return;
        }

        anios[indice] = nuevoAnio;

        try (FileWriter fw = new FileWriter(archivo, false);
             PrintWriter pw = new PrintWriter(fw)) {

            for (int i = 0; i < cantidad; i++) {
                pw.println(titulos[i] + "," + anios[i]);
            }

            lblAnioActual.setText("Año actual: " + nuevoAnio);
            mostrarDiscos();

            JOptionPane.showMessageDialog(this,
                    "Registro actualizado y archivo guardado.");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BibliotecaDiscos().setVisible(true);
        });
    }
}
