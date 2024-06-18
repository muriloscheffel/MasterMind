public class PinoCL extends Pino {

    protected PinoCL(Cor cor) {
        super(cor);
    }

    public static PinoCL criaPinoColorido(String nomeCor) {
        Cor cor = Cores.getInstance().getCor(nomeCor);
        if(cor == null) {
            throw new IllegalArgumentException("Cor inválida");
        }
        return new PinoCL(cor);
    }
}
