import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Jogo {

    private int tentativas, senhas;
    private String modo;


    private JPanel painelGeral;

    private JPanel painel;
    private JPanel[] btPainel;

    private JPanel painelBotoes;
    private PinoCL[] pinos;


    public Jogo(int tentativas, int senhas, String modo) {
        this.tentativas = tentativas;
        this.senhas = senhas;
        this.modo = modo;
        painelGeral = new JPanel();
        painelGeral.setLayout(new BoxLayout(painelGeral, BoxLayout.Y_AXIS));
        criaPainelTentativas();
        criaPainelBotoes();
        painelGeral.add(painel);
        painelGeral.add(painelBotoes);
    }

    public void criaPainelTentativas() {
        int senhas = getSenhas();
        this.painel = new JPanel();
        painel.setLayout(new GridLayout(1, senhas));
        this.btPainel = new JPanel[senhas];
        for (int i = 0; i < senhas; i++) {
            btPainel[i] = new JPanel();
            btPainel[i].setBackground(Color.darkGray);
            btPainel[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            painel.add(btPainel[i]);
        }
    }

    public void criaPainelBotoes() {
        pinos = new PinoCL[6];
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 6));

        for(int i = 0; i < pinos.length; i++) {
            String[] s = {"RED", "GREEN", "BLUE", "YELLOW", "PINK", "ORANGE"};
            pinos[i] = PinoCL.criaPinoColorido(s[i]);
            pinos[i].setActionCommand(s[i]);
            pinos[i].addActionListener(this::clickColor);
            painelBotoes.add(pinos[i]);
        }

    }

    private void clickColor(ActionEvent event) {
        Cor selectedColor = Cores.getInstance().getCor(event.getActionCommand());

        for(int i = 0; i < getSenhas(); i++) {
            if(getBtPainel()[i].getBackground() == Color.darkGray) {
                getBtPainel()[i].setBackground(selectedColor.getCor());
                break;
            }
        }
    }

    public int getTentativas() {
        return tentativas;
    }

    public int getSenhas() {
        return senhas;
    }

    public String getModo() {
        return modo;
    }

    public JPanel[] getBtPainel(){
        return btPainel;
    }

    public JPanel getPainel(){
        return painelGeral;
    }
}


