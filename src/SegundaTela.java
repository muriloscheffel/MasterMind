
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class SegundaTela extends JFrame {

    JPanel[][] botoesJogador;
    private Color selectedColor;
    int senhas, tentativas;
    String modo;
        

    public SegundaTela(int senhas, int tentativas, String modo) {
        this.senhas = senhas;
        this.tentativas = tentativas;

        setTitle("Master Mind");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
      
        JPanel linhaInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JLabel lbSenha = new JLabel("Senhas: " + senhas);
        JLabel lbTentativas = new JLabel("   Tentativas: " + tentativas);
        JLabel lbModo = new JLabel("   Modo: " + modo);

        linhaInfo.add(lbSenha);
        linhaInfo.add(lbTentativas);
        linhaInfo.add(lbModo);

        contentPane.add(linhaInfo);
//        Jogo jogo = new Jogo(tentativas, senhas, modo);
//        add(jogo.getPainel());
    }

    

    private void limpaTentativas(ActionEvent event) {
        
    }

    private void clickColor(ActionEvent event) {
        selectedColor = ((JButton) event.getSource()).getBackground();
        int count = 0;

        for(int i = count; i < senhas; i++) {
            if(botoesJogador[count][i].getBackground() == Color.darkGray) {
                botoesJogador[count][i].setBackground(selectedColor);
                // botoesJogador[count][i].setBorder(new EmptyBorder(0,400,0,400));

                break;
            }
        }
    }






    public int getSenhas() {
        return senhas;
    }

    public int getTentativas() {
        return tentativas;
    }

    public String getModo() {
        return modo;
    }
}
 