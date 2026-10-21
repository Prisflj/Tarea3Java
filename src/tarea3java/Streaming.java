package tarea3java;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

class Artista {
    String nombre;
    String paisOrigen;
    int oyentesMensuales;

    public Artista() {
    }

    @Override
    public String toString() {
        return "Artista: " + nombre
                + " | País: " + paisOrigen
                + " | Oyentes mensuales: " + oyentesMensuales;
    }
}

class Cancion {
    String titulo;
    int duracionSegundos;
    String genero;
    String nombreArtista;

    public Cancion() {
    }

    @Override
    public String toString() {
        return "Canción: " + titulo
                + " | Duración: " + duracionSegundos + " segundos"
                + " | Género: " + genero
                + " | Artista: " + nombreArtista;
    }
}

public class Streaming extends JFrame {

    private JTextField txtNombre = new JTextField(15);
    private JTextField txtPais = new JTextField(15);
    private JTextField txtOyentes = new JTextField(10);

    private JTextField txtTitulo = new JTextField(15);
    private JTextField txtDuracion = new JTextField(10);
    private JTextField txtGenero = new JTextField(15);
    private JTextField txtArtista = new JTextField(15);

    private JTextArea area = new JTextArea(12, 30);

    public Streaming() {
        setTitle("Streaming - Artistas y Canciones");
        setSize(600, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));

        panel.add(new JLabel("Nombre del artista:"));
        panel.add(txtNombre);

        panel.add(new JLabel("País de origen:"));
        panel.add(txtPais);

        panel.add(new JLabel("Oyentes mensuales:"));
        panel.add(txtOyentes);

        JButton btnArtista = new JButton("Crear Artista");
        panel.add(new JLabel(""));
        panel.add(btnArtista);

        panel.add(new JLabel("Título de la canción:"));
        panel.add(txtTitulo);

        panel.add(new JLabel("Duración en segundos:"));
        panel.add(txtDuracion);

        panel.add(new JLabel("Género:"));
        panel.add(txtGenero);

        panel.add(new JLabel("Nombre del artista:"));
        panel.add(txtArtista);

        JButton btnCancion = new JButton("Crear Canción");

        area.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);
        add(btnCancion, BorderLayout.SOUTH);

        btnArtista.addActionListener(e -> crearArtista());
        btnCancion.addActionListener(e -> crearCancion());
    }

    private void crearArtista() {
        String nombre = txtNombre.getText().trim();
        String pais = txtPais.getText().trim();
        String textoOyentes = txtOyentes.getText().trim();

        if (nombre.isEmpty() || pais.isEmpty()
                || textoOyentes.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Completa los datos del artista.");
            return;
        }

        try {
            int oyentes = Integer.parseInt(textoOyentes);

            if (oyentes < 0) {
                JOptionPane.showMessageDialog(
                        this, "Los oyentes no pueden ser negativos.");
                return;
            }

            Artista artista = new Artista();

            artista.nombre = nombre;
            artista.paisOrigen = pais;
            artista.oyentesMensuales = oyentes;

            area.append("Artista: " + artista.nombre
                    + " | País: " + artista.paisOrigen
                    + " | Oyentes mensuales: "
                    + artista.oyentesMensuales + "\n");

            guardar(artista.toString());

            txtNombre.setText("");
            txtPais.setText("");
            txtOyentes.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, "Ingresa un número válido de oyentes.");
        }
    }

    private void crearCancion() {
        String titulo = txtTitulo.getText().trim();
        String textoDuracion = txtDuracion.getText().trim();
        String genero = txtGenero.getText().trim();
        String artistaTexto = txtArtista.getText().trim();

        if (titulo.isEmpty() || textoDuracion.isEmpty()
                || genero.isEmpty() || artistaTexto.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Completa los datos de la canción.");
            return;
        }

        try {
            int duracion = Integer.parseInt(textoDuracion);

            if (duracion <= 0) {
                JOptionPane.showMessageDialog(
                        this, "La duración debe ser mayor que cero.");
                return;
            }

            Cancion cancion = new Cancion();

            cancion.titulo = titulo;
            cancion.duracionSegundos = duracion;
            cancion.genero = genero;
            cancion.nombreArtista = artistaTexto;

            area.append("Canción: " + cancion.titulo
                    + " | Duración: " + cancion.duracionSegundos
                    + " segundos | Género: " + cancion.genero
                    + " | Artista: " + cancion.nombreArtista + "\n");

            guardar(cancion.toString());

            txtTitulo.setText("");
            txtDuracion.setText("");
            txtGenero.setText("");
            txtArtista.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, "Ingresa una duración numérica válida.");
        }
    }

    private void guardar(String texto) {
        try (FileWriter fw =
                     new FileWriter("temp/streaming.txt", true)) {
            fw.write(texto + System.lineSeparator());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(
                    this, "No se pudo guardar el archivo.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Streaming().setVisible(true);
        });
    }
}
