
package tarea3java;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal() {
        setTitle("Tarea 3 - Java");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 8, 8));

        JLabel titulo = new JLabel(
            "Tarea 3 - Programación Java",
            SwingConstants.CENTER
        );

        JButton btnVentas = new JButton("1. Control de ventas");
        JButton btnAsistencia = new JButton("2. Registro de asistencia");
        JButton btnTemperaturas = new JButton("3. Temperaturas");
        JButton btnDiscos = new JButton("4. Biblioteca de discos");
        JButton btnStreaming = new JButton("5. Plataforma de streaming");
        JButton btnVideojuegos = new JButton("6. Catálogo de videojuegos");

        add(titulo);
        add(btnVentas);
        add(btnAsistencia);
        add(btnTemperaturas);
        add(btnDiscos);
        add(btnStreaming);
        add(btnVideojuegos);

        btnVentas.addActionListener(e ->
            new Ventas().setVisible(true));

        btnAsistencia.addActionListener(e ->
            new Asistencia().setVisible(true));

        btnTemperaturas.addActionListener(e ->
            new Temperaturas().setVisible(true));

        btnDiscos.addActionListener(e ->
            new BibliotecaDiscos().setVisible(true));

        btnStreaming.addActionListener(e ->
            new Streaming().setVisible(true));

        btnVideojuegos.addActionListener(e ->
            new CatalogoVideojuegos().setVisible(true));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() ->
            new MenuPrincipal().setVisible(true));
    }
}

