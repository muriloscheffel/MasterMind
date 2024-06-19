import java.awt.*;

public class PinoPB extends Pino {

    public PinoPB(Cor cor) {
        super(cor);
    }

    public void setCor(Cor cor){
        if (cor.getNomeDaCor().equals("BLACK") ||
                cor.getNomeDaCor().equals("WHITE") ||
                cor.getNomeDaCor().equals("GRAY")){
            super.setCor(cor);
        }else{
            throw new UnsupportedOperationException();
        }
    }
}
