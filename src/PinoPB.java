import java.awt.*;

public class PinoPB extends Pino {

    private PinoPB(Cor cor) {
        super(cor);
    }

    public PinoPB criaPinoPreto() {
        return new PinoPB(new Cor("BLACK", Color.BLACK));
    }

    public static PinoPB criaPinoBranco(){
        return new PinoPB(new Cor("WHITE",Color.WHITE));
    }

    public static PinoPB criaPinoVazio(){
        return new PinoPB(new Cor("GRAY",Color.WHITE));
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
