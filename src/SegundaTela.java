
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.*;

public class SegundaTela extends JFrame {

    JPanel[][] botoesJogador;
    JButton[] botoesCores;
    JButton[] botoesSubClear;
    private Color selectedColor;
    private int selectedColumn;
    ArrayList<String> colorsS;
    int senhas, tentativas;
    String modo;
        

    public SegundaTela(int senhas, int tentativas, String modo) {
        this.senhas = senhas;
        this.tentativas = tentativas;
//        botoesJogador = new JPanel[tentativas][senhas];
//        botoesCores = new JButton[6];
//        botoesSubClear = new JButton[2];
//        JPanel panelJogador = new JPanel();
//        panelJogador.setLayout(new GridLayout(tentativas,senhas));
//        JPanel panelCores = new JPanel();
//        panelCores.setLayout(new GridLayout(1, 6));
//        JPanel panelSubClear = new JPanel();
//        panelSubClear.setLayout(new GridLayout(1, 2));
//        String[] colorsA = {"RED", "BLUE", "GREEN", "YELLOW", "MAGENTA", "DARK_GRAY"};
//        colorsS = new ArrayList<>();
//        Collections.addAll(colorsS, colorsA);

        setTitle("Master Mind");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
      
        JPanel linhaInfo = new JPanel(new FlowLayout(FlowLayout.CENTER));


//        for(int i = 0; i < senhas; i++){
//            for(int j = 0; j < tentativas; j++){
//                botoesJogador[j][i] = new JPanel();
//                botoesJogador[j][i].setBackground(Color.darkGray);
//                botoesJogador[j][i].setBorder(BorderFactory.createLineBorder(Color.black));
//                panelJogador.add(botoesJogador[j][i]);
//            }
//        }

//        for(int i = 0; i < 6; i++){
//            botoesCores[i] = new JButton();
//            Color[] colors = {Color.red, Color.blue, Color.green, Color.yellow, Color.MAGENTA, Color.PINK};
//            botoesCores[i].setBackground(colors[i]);
//            botoesCores[i].setActionCommand(colorsS.get(i));
//            botoesCores[i].addActionListener(event -> clickColor(event));
//            panelCores.add(botoesCores[i]);
//        }

//        botoesSubClear[0] = new JButton("LIMPAR");
//        botoesSubClear[1] = new JButton("ENVIAR");
//
//        for(int i = 0; i < 2; i++) {
//            botoesSubClear[i].addActionListener(event -> limpaTentativas(event));
//            panelSubClear.add(botoesSubClear[i]);
//        }

        JLabel lbSenha = new JLabel("Senhas: " + senhas);
        JLabel lbTentativas = new JLabel("   Tentativas: " + tentativas);
        JLabel lbModo = new JLabel("   Modo: " + modo);

        linhaInfo.add(lbSenha);
        linhaInfo.add(lbTentativas);
        linhaInfo.add(lbModo);

        contentPane.add(linhaInfo);
        Jogo jogo = new Jogo(tentativas, senhas, modo);
        add(jogo.getPainel());
//        add(panelJogador);
//        Tentativa tentativa = new Tentativa();
//        add(tentativa.getPainel());
//        add(panelCores);
//        add(panelSubClear);


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
 