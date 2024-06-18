import javax.swing.*;

public abstract class Pino extends JButton {
    private Cor cor;

    protected Pino(Cor cor) {
        this.setCor(cor);

    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
        this.setForeground(cor.getCor());
        this.setBackground(cor.getCor());
    }
}
