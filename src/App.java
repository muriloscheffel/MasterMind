import javax.swing.SwingUtilities;

public class App {

    // Método main
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Chama a classe Tela que é um JFrame
            Tela tela = new Tela();
        });
    }
}