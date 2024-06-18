import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;

public class PainelJogo extends JPanel {

    private int tentativa, tentativas, senha, senhas;
    private String modo;
    private ArrayList<Color> resposta;

    private JPanel painelCores;
    private PinoCL[] pinos;

    private Linha[] linhas;

    private JPanel painelBotoes;
    private JButton enviar;
    private JButton limpar;
    private String[] CORES = {"RED", "GREEN", "BLUE", "YELLOW", "PINK", "ORANGE"};
    private Tela tela;

    public PainelJogo(int senhas, int tentativas, String modo, Tela tela) {
        this.tentativas = tentativas;
        this.senhas = senhas;
        this.modo = modo;
        this.linhas = new Linha[tentativas];
        this.tentativa = 0;
        this.senha = 0;
        this.resposta = new ArrayList<>();
        this.tela = tela;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        criaLinhas();
        criaPainelBotoes();
        inicializa();
        geraSenha();
    }

    public void criaLinhas() {

        for(int i = 0; i < linhas.length; i++) {

            Linha linha = new Linha(senhas);
            linhas[i] = linha;
            add(linha);
        }
    }

    public void criaPainelBotoes() {
        pinos = new PinoCL[6];
        painelCores = new JPanel();
        painelCores.setLayout(new GridLayout(1, 6));

        for (int i = 0; i < pinos.length; i++) {

            pinos[i] = PinoCL.criaPinoColorido(CORES[i]);
            pinos[i].setActionCommand(CORES[i]);
            pinos[i].addActionListener(this::clickColor);
            painelCores.add(pinos[i]);
        }
        add(painelCores);
    }

    public void inicializa() {
        painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(1, 2));

        enviar = new JButton("Enviar");
        limpar = new JButton("Limpar");

        painelBotoes.add(limpar);
        painelBotoes.add(enviar);
        add(painelBotoes);

        limpar.addActionListener(this::limparLinha);
        enviar.addActionListener(this::enviarLinha);
    }

    private void clickColor(ActionEvent event) {
        Cor selectedColor = Cores.getInstance().getCor(event.getActionCommand());

        if(senha < senhas) {
            linhas[tentativa].setColor(senha, selectedColor);
            senha++;
        }

    }

    private void limparLinha(ActionEvent ev) {
        linhas[tentativa].limpar();
        senha = 0;
    }

    private void enviarLinha(ActionEvent ev) {
        if(senha != senhas) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
        } else {
            if(checaLinha()) {
                JOptionPane.showMessageDialog(this, "Parabéns, você ganhou!");
                tela.fecharTela();
            } else {
                if(tentativa == tentativas - 1) {
                    JOptionPane.showMessageDialog(this, "Fim, você perdeu.");
                    tela.fecharTela();
                } else {
                    verificaAcertos();
                    tentativa++;
                    senha = 0;
                }
            }
        }
    }

    private boolean checaLinha() {
        for(int i = 0; i < senhas; i++) {
            System.out.println(linhas[tentativa].getColor(i));
            if(!resposta.get(i).equals(linhas[tentativa].getColor(i))) {
                return false;
            }
        }
        return true;
    }

    public void geraSenha() {
        Random random = new Random();

        for(int i = 0; i < senhas; i++) {
            int ran = random.nextInt(0, 5);
            String cor = CORES[ran];
            Color core = Cores.getInstance().getCor(cor).getCor();
            if(!resposta.contains(core)) {
                resposta.add(core);
            } else {
                i--;
            }
        }
        System.out.println(resposta);
    }

    private void verificaAcertos() {
        for(int i = 0; i < senhas; i++) {
            Color selecionada = linhas[tentativa].getColor(i);
            Color corCerta = resposta.get(i);

            if(selecionada.equals(corCerta)) {
                linhas[tentativa].setPino(i, "black");
            }
            else if(resposta.contains(selecionada)) {
                linhas[tentativa].setPino(i, "white");
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
}


