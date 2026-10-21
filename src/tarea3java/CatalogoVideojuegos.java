
package tarea3java;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Videojuego {
    String titulo;
    String consola;
    int anioLanzamiento;

    public Videojuego(String titulo, String consola,
                      int anioLanzamiento) {
        this.titulo = titulo;
        this.consola = consola;
        this.anioLanzamiento = anioLanzamiento;
    }

    @Override
    public String toString() {
        return "Título: " + titulo
                + " | Consola: " + consola
                + " | Año: " + anioLanzamiento;
    }
}

public class CatalogoVideojuegos extends JFrame {

    private ArrayList<Videojuego> catalogo =
            new ArrayList<>();

    private JTextField txtTitulo = new JTextField(15);
    private JTextField txtConsola = new JTextField(15);
    private JTextField txtAnio = new JTextField(10);
    private JTextArea area = new JTextArea();

    public CatalogoVideojuegos() {
        setTitle("Catálogo de Videojuegos");
        setSize(550, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(
                new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Título del videojuego:"));
        panel.add(txtTitulo);

        panel.add(new JLabel("Consola:"));
        panel.add(txtConsola);

        panel.add(new JLabel("Año de lanzamiento:"));
        panel.add(txtAnio);

        JButton btnAgregar = new JButton("Agregar Juego");
        JButton btnMostrar = new JButton("Mostrar Catálogo");
        JButton btnEliminar = new JButton("Eliminar Último");

        panel.add(btnAgregar);
        panel.add(btnMostrar);
        panel.add(btnEliminar);

        area.setEditable(false);

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarVideojuego());
        btnMostrar.addActionListener(e -> mostrarCatalogo());
        btnEliminar.addActionListener(e -> eliminarUltimo());
    }

    private void agregarVideojuego() {
        String titulo = txtTitulo.getText().trim();
        String consola = txtConsola.getText().trim();
        String textoAnio = txtAnio.getText().trim();

        if (titulo.isEmpty() || consola.isEmpty()
                || textoAnio.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this, "Completa todos los campos.");
            return;
        }

        try {
            int anio = Integer.parseInt(textoAnio);

            if (anio <= 0) {
                JOptionPane.showMessageDialog(
                        this, "Ingresa un año válido.");
                return;
            }

            Videojuego videojuego =
                    new Videojuego(titulo, consola, anio);

            catalogo.add(videojuego);

            txtTitulo.setText("");
            txtConsola.setText("");
            txtAnio.setText("");

            JOptionPane.showMessageDialog(
                    this, "Videojuego agregado correctamente.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this, "El año debe ser un número válido.");
        }
    }

    private void mostrarCatalogo() {
        area.setText("");

        for (int i = 0; i < catalogo.size(); i++) {
            Videojuego videojuego = catalogo.get(i);

            area.append("Título: " + videojuego.titulo
                    + " | Consola: " + videojuego.consola
                    + " | Año: " + videojuego.anioLanzamiento
                    + "\n");
        }

        area.append("\nTotal de videojuegos registrados: "
                + catalogo.size());
    }

    private void eliminarUltimo() {
        if (catalogo.size() > 0) {
            Videojuego eliminado =
                    catalogo.remove(catalogo.size() - 1);

            JOptionPane.showMessageDialog(
                    this, "Se eliminó: " + eliminado.titulo);
        } else {
            JOptionPane.showMessageDialog(
                    this, "No hay videojuegos para eliminar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CatalogoVideojuegos().setVisible(true);
        });
    }
}
