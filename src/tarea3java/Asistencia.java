package tarea3java;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Asistencia extends JFrame {

    private String[] asistentes = new String[5];
    private int indice = 0;

    private JTextField txtNombre = new JTextField(15);
    private JTextArea area = new JTextArea();

    public Asistencia() {
        setTitle("Registro de Asistencia a Conferencia");
        setSize(700, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

        panel.add(new JLabel("Nombre del asistente:"));
        panel.add(txtNombre);

        JButton btnRegistrar = new JButton("Registrar Asistente");
        JButton btnExportar = new JButton("Exportar Lista");

        // Agregar AMBOS botones a la ventana
        panel.add(btnRegistrar);
        panel.add(btnExportar);

        area.setEditable(false);
        area.setFont(new Font("Arial", Font.PLAIN, 16));

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btnRegistrar.addActionListener(e -> registrarAsistente());
        btnExportar.addActionListener(e -> exportarLista());
    }

    private void registrarAsistente() {
        if (indice >= asistentes.length) {
            JOptionPane.showMessageDialog(this,
                    "Ya registraste los 5 asistentes.");
            return;
        }

        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Escribe el nombre del asistente.");
            return;
        }

        asistentes[indice] = nombre;
        indice++;

        area.append(indice + ". " + nombre + "\n");

        txtNombre.setText("");
        txtNombre.requestFocus();
    }

    private void exportarLista() {
        if (indice != 5) {
            JOptionPane.showMessageDialog(this,
                    "Debes registrar los 5 asistentes primero.");
            return;
        }

        StringBuilder contenido = new StringBuilder();
        contenido.append("Lista de Asistencia\n");

        for (int i = 0; i < asistentes.length; i++) {
            contenido.append(i + 1)
                     .append(". ")
                     .append(asistentes[i])
                     .append("\n");
        }

        try {
            File carpeta = new File("temp");
            carpeta.mkdirs();

            File archivo = new File(
                    carpeta, "fatima_asistencia.txt");

            try (FileWriter escritor = new FileWriter(archivo)) {
                escritor.write(contenido.toString());
            }

            JOptionPane.showMessageDialog(this,
                    "Lista exportada correctamente.");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Asistencia().setVisible(true);
        });
    }
}



