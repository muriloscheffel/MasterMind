import javax.swing.*;
import java.awt.*;

public class Linha extends JPanel {

    private JPanel[] vetorPaineis;
    private int senhas;

    public Linha(int senhas) {
        this.senhas = senhas;
        setLayout(new GridLayout(1, senhas));
        vetorPaineis = new JPanel[senhas];
        inicializa();
    }

    public void inicializa() {
        for (int i = 0; i < senhas; i++) {
            vetorPaineis[i] = new JPanel();
            vetorPaineis[i].setBackground(Color.darkGray);
            vetorPaineis[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            add(vetorPaineis[i]);
        }
    }

    public void setColor(int index, Cor cor) {
        vetorPaineis[index].setBackground(cor.getCor());
    }

    public void limpar() {
        for (int i = 0; i < senhas; i++) {
            vetorPaineis[i].setBackground(Color.darkGray);
        }
    }

    public Color getColor(int index) {
        return vetorPaineis[index].getBackground();
    }
}
