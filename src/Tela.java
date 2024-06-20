import javax.swing.*;

public class Tela extends JFrame {

    // Os atributos são os paineis (ambas classes extends JPanel)
    private PainelOpcoes painelOpcoes;
    private PainelJogo painelJogo;

    public Tela() {
        painelOpcoes = new PainelOpcoes(this);

        setTitle("Master Mind");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Seta para o conteúdo a ser exibido ser o painel de opções
        setContentPane(painelOpcoes);
        pack();
        setVisible(true);
    }

    // Função chamada na função de clicar no botão
    public void iniciarJogo(int senhas, int tentativas, String modo) {
        setSize(500, 500);
        painelJogo = new PainelJogo(senhas, tentativas, modo, this);

        // Define que a tela a ser exibida é o painelJogo
        setContentPane(painelJogo);
    }

    // Método que fecha a tela em caso de vitória ou derrota
    public void fecharTela() {
        dispose();
    }

}
