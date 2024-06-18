import java.awt.Color;

public class Cor {
    private String nomeDaCor;
    private Color cor;

    public Cor(String nomeDaCor, Color cor) {
        this.nomeDaCor = nomeDaCor;
        this.cor = cor;
    }

    public String getNomeDaCor() {
        return nomeDaCor;
    }

    public Color getCor() {
        return cor;
    }

    @Override
    public String toString() {
        return "Cor: " + getNomeDaCor();
    }
}
