import javax.swing.*;
import java.awt.*;

public class Linha extends JPanel {

    private JPanel[] vetorTentativas;
    private PinoPB[] vetorAcertos;
    private int senhas;

    public Linha(int senhas) {
        this.senhas = senhas;
        setLayout(new GridLayout(1, senhas));
        vetorTentativas = new JPanel[senhas];
        vetorAcertos = new PinoPB[senhas];
        inicializa();
    }

    public void inicializa() {
        for(int i = 0; i < senhas; i++) {
            vetorTentativas[i] = new JPanel();
            vetorTentativas[i].setBackground(Color.darkGray);
            vetorTentativas[i].setBorder(BorderFactory.createLineBorder(Color.WHITE));
            add(vetorTentativas[i]);
        }
        for(int i = 0; i < senhas; i++) {
            vetorAcertos[i] = new PinoPB(new Cor("GRAY", Color.lightGray));
            vetorAcertos[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
            add(vetorAcertos[i]);
        }
    }

    public void setColor(int index, Cor cor) {
        vetorTentativas[index].setBackground(cor.getCor());
    }

    public void limpar() {
        for (int i = 0; i < senhas; i++) {
            vetorTentativas[i].setBackground(Color.darkGray);
        }
    }

    public Color getColor(int index) {
        return vetorTentativas[index].getBackground();
    }

    public void setPino(int index, String cor) {
        if(cor.equals("black")) {
            vetorAcertos[index].setCor(new Cor("BLACK", Color.BLACK));
        }else if(cor.equals("white")) {
            vetorAcertos[index].setCor(new Cor("WHITE", Color.WHITE));
        }

    }
}
