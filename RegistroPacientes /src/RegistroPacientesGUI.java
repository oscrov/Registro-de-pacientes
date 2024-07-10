import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroPacientesGUI extends JFrame {
    private JTextField nombreTextField, domicilioTextField, edadTextField, pulsacionesTextField;
    private JComboBox<String> sexoComboBox, tipoSangreComboBox;
    private JCheckBox seguroCheckBox;
    private JButton guardarButton, limpiarButton, borrarButton;
    private JTable pacientesTable;
    private DefaultTableModel tableModel;

    public RegistroPacientesGUI() {
        // Configuración de la ventana principal
        setTitle("Registro de Pacientes");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Creación de componentes
        nombreTextField = new JTextField();
        domicilioTextField = new JTextField();
        edadTextField = new JTextField();
        pulsacionesTextField = new JTextField();

        String[] sexos = {"Masculino", "Femenino"};
        sexoComboBox = new JComboBox<>(sexos);

        String[] tiposSangre = {"A", "B", "AB", "O"};
        tipoSangreComboBox = new JComboBox<>(tiposSangre);

        seguroCheckBox = new JCheckBox("Cuenta con seguro");

        guardarButton = new JButton("Guardar");
        limpiarButton = new JButton("Limpiar Formulario");
        borrarButton = new JButton("Borrar Registro");

        // Configuración de la tabla
        String[] columnas = {"Nombre", "Domicilio", "Edad", "Sexo", "Tipo de Sangre", "Seguro", "Pulsaciones"};
        tableModel = new DefaultTableModel(columnas, 0);
        pacientesTable = new JTable(tableModel);
        pacientesTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Configuración del diseño con BorderLayout
        setLayout(new BorderLayout());

        // Panel superior para ingresar datos
        JPanel datosPanel = new JPanel(new GridLayout(7, 2));
        datosPanel.add(new JLabel("Nombre:"));
        datosPanel.add(nombreTextField);
        datosPanel.add(new JLabel("Domicilio:"));
        datosPanel.add(domicilioTextField);
        datosPanel.add(new JLabel("Edad:"));
        datosPanel.add(edadTextField);
        datosPanel.add(new JLabel("Sexo:"));
        datosPanel.add(sexoComboBox);
        datosPanel.add(new JLabel("Tipo de Sangre:"));
        datosPanel.add(tipoSangreComboBox);
        datosPanel.add(new JLabel("Cuenta con seguro:"));
        datosPanel.add(seguroCheckBox);
        datosPanel.add(new JLabel("Pulsaciones por minuto:"));
        datosPanel.add(pulsacionesTextField);

        // Panel inferior para botones y tabla
        JPanel botonesPanel = new JPanel(new FlowLayout());
        botonesPanel.add(guardarButton);
        botonesPanel.add(limpiarButton);
        botonesPanel.add(borrarButton);

        // Agregar componentes a la ventana
        add(datosPanel, BorderLayout.NORTH);
        add(new JScrollPane(pacientesTable), BorderLayout.CENTER);
        add(botonesPanel, BorderLayout.SOUTH);

        // Configuración de eventos
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPaciente();
            }
        });

        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarFormulario();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarRegistro();
            }
        });
    }

    private void guardarPaciente() {
        String nombre = nombreTextField.getText();
        String domicilio = domicilioTextField.getText();
        String edad = edadTextField.getText();
        String sexo = (String) sexoComboBox.getSelectedItem();
        String tipoSangre = (String) tipoSangreComboBox.getSelectedItem();
        boolean tieneSeguro = seguroCheckBox.isSelected();
        String pulsaciones = pulsacionesTextField.getText();

        // Validación básica
        if (nombre.isEmpty() || edad.isEmpty() || pulsaciones.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Agregar fila a la tabla
        Object[] fila = {nombre, domicilio, edad, sexo, tipoSangre, tieneSeguro, pulsaciones};
        tableModel.addRow(fila);

        // Limpiar formulario después de guardar
        limpiarFormulario();
    }

    private void limpiarFormulario() {
        nombreTextField.setText("");
        domicilioTextField.setText("");
        edadTextField.setText("");
        sexoComboBox.setSelectedIndex(0);
        tipoSangreComboBox.setSelectedIndex(0);
        seguroCheckBox.setSelected(false);
        pulsacionesTextField.setText("");
    }

    private void borrarRegistro() {
        int selectedRow = pacientesTable.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un registro para borrar.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistroPacientesGUI().setVisible(true);
            }
        });
    }
}
//oscrov 2023