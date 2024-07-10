import javax.swing.SwingUtilities;
/**
 * Clase principal que inicia la aplicación de registro de pacientes.
 * Esta clase contiene el método main que inicializa la interfaz gráfica.
 * @author Oscar Roman Valdez 2D
 * @version 1.0
 */
public class Main {
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