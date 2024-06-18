import javax.swing.*;

public class Tela extends JFrame {

    private PainelOpcoes painelOpcoes;
    private PainelJogo painelJogo;

    public Tela() {
        painelOpcoes = new PainelOpcoes(this);

        setTitle("Master Mind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(painelOpcoes);
        pack();
        setVisible(true);
    }

    public void iniciarJogo(int senhas, int tentativas, String modo) {
        setSize(500, 500);
        painelJogo = new PainelJogo(senhas, tentativas, modo);
        setContentPane(painelJogo);
    }

}
