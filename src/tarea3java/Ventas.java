package tarea3java;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Ventas extends JFrame {

    private double[] ventas = new double[5];
    private int indice = 0;

    private JTextField txtVenta;
    private JTextArea area;

    public Ventas() {
        setTitle("Control de Ventas Semanales");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new FlowLayout());

        panel.add(new JLabel("Monto de venta Q:"));

        txtVenta = new JTextField(10);
        panel.add(txtVenta);

        JButton btnAgregar = new JButton("Agregar Venta");
        JButton btnReporte = new JButton("Generar Reporte");

        panel.add(btnAgregar);
        panel.add(btnReporte);

        area = new JTextArea();
        area.setEditable(false);
        area.setFont(new Font("Monospaced", Font.PLAIN, 14));

        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(area), BorderLayout.CENTER);

        btnAgregar.addActionListener(e -> agregarVenta());
        btnReporte.addActionListener(e -> generarReporte());
    }

    private void agregarVenta() {
        if (indice == ventas.length) {
            JOptionPane.showMessageDialog(this,
                    "Ya ingresaste las 5 ventas.");
            return;
        }

        try {
            double monto = Double.parseDouble(txtVenta.getText().trim());

            if (monto < 0) {
                JOptionPane.showMessageDialog(this,
                        "El monto no puede ser negativo.");
                return;
            }

            ventas[indice] = monto;
            indice++;

           String[] dias = {
    "Lunes", "Martes", "Miércoles",
    "Jueves", "Viernes"
};

area.append(dias[indice - 1] + ": Q"
        + String.format("%.2f", monto) + "\n");

            txtVenta.setText("");
            txtVenta.requestFocus();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Escribe un monto válido.");
        }
    }

    private void generarReporte() {
        if (indice < 5) {
            JOptionPane.showMessageDialog(this,
                    "Primero debes ingresar las 5 ventas.");
            return;
        }

        double total = 0;
        int mayores = 0;

        for (double venta : ventas) {
            total += venta;

            if (venta > 500) {
                mayores++;
            }
        }

        String reporte = "\n===== REPORTE DE VENTAS =====\n"
                + "Total vendido: Q"
                + String.format("%.2f", total) + "\n"
                + "Ventas superiores a Q500: " + mayores + "\n";

        area.append(reporte);

        try {
            File carpeta = new File("temp");
            carpeta.mkdirs();

            File archivo = new File(
                    carpeta, "fatima_reporte_ventas.txt");

            try (FileWriter escritor = new FileWriter(archivo)) {
                escritor.write("Reporte de Ventas\n");
                escritor.write("Total vendido: Q"
                        + String.format("%.2f", total) + "\n");
                escritor.write("Ventas superiores a Q500: "
                        + mayores + "\n");
            }

            JOptionPane.showMessageDialog(this,
                    "Reporte guardado correctamente.");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Error al guardar: " + ex.getMessage());
        }
    }
    public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
        new Ventas().setVisible(true);
    });
}
}

