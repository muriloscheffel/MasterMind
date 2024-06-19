import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class PainelJogo extends JPanel {

    private int tentativa, tentativas, senha, senhas;
    private String modo;
    private ArrayList<Color> resposta;
    private JPanel painelCores;
    private PinoCL[] pinos;
    private Linha[] linhas;
    private JPanel topBar;
    private JPanel painelBotoes;
    private JButton enviar;
    private JButton limpar;
    private String[] CORES = {"RED", "GREEN", "BLUE", "YELLOW", "PINK", "ORANGE"};
    private Tela tela;
    private JPanel painelRespostas;
    private JPanel[] vetorRespostas;
    private JLabel lbTentativas;

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
        criaTopBar();
        geraSenha();
        criaLinhas();
        if(modo.equals("Teste")) { criaRespostas(); }
        inicializa();
        criaPainelBotoes();
    }

    public void criaTopBar() {
        topBar = new JPanel();
        JLabel lbSenhas = new JLabel("Senhas: " + senhas);
        lbTentativas = new JLabel("Tentativas: " + tentativas);
        JLabel lbModo = new JLabel("Modo: " + modo);
        topBar.add(lbSenhas);
        topBar.add(lbTentativas);
        topBar.add(lbModo);

        add(topBar);
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
                    lbTentativas.setText("Tentativas: " + (tentativas - tentativa));
                }
            }
        }
    }

    private boolean checaLinha() {
        for(int i = 0; i < senhas; i++) {
            if(!resposta.get(i).equals(linhas[tentativa].getColor(i))) {
                return false;
            }
        }
        return true;
    }

    public void geraSenha() {
        Random random = new Random();

        for(int i = 0; i < senhas; i++) {
            int ran = random.nextInt(0, 6);
            String nomeCor = CORES[ran];
            Color cor = Cores.getInstance().getCor(nomeCor).getCor();
            if(!resposta.contains(cor)) {
                resposta.add(cor);
            } else {
                i--;
            }
        }
        System.out.println(resposta);
    }

    private void verificaAcertos() {
        int cont = 0;

        for(int i = 0; i < senhas; i++) {
            Color selecionada = linhas[tentativa].getColor(i);
            Color corCerta = resposta.get(i);

            if(selecionada.equals(corCerta)) {
                linhas[tentativa].setPino(cont, "black");
                cont++;

            }
            else if(resposta.contains(selecionada)) {
                linhas[tentativa].setPino(cont, "white");
                cont++;
            }
        }
    }

    public void criaRespostas() {
        vetorRespostas = new JPanel[senhas];
        painelRespostas = new JPanel();
        painelRespostas.setLayout(new GridLayout(1, senhas));

        for(int i = 0; i < senhas; i++) {
            vetorRespostas[i] = new JPanel();
            vetorRespostas[i].setBackground(resposta.get(i));
            painelRespostas.add(vetorRespostas[i]);
        }
        add(painelRespostas);
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


