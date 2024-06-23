import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

// Painel do jogo
// Iniciado ao clicar no botão 'Começar'

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
    private JLabel lbDemo;
    ArrayList<Color> coresTentadas;

    
    public PainelJogo(int senhas, int tentativas, String modo, Tela tela) {
        this.tentativas = tentativas;
        this.senhas = senhas;
        this.modo = modo;
        this.tela = tela;
        linhas = new Linha[tentativas];
        tentativa = 0;
        senha = 0;
        resposta = new ArrayList<>();
        lbDemo = new JLabel("Você está no modo teste, aqui está a resposta:");
        coresTentadas = new ArrayList<>(senhas);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Métodos que criam os elementos na tela
        geraSenha();
        criaTopBar();
        if(modo.equals("Teste")) {
            add(lbDemo);
            criaRespostas();
        }
        criaLinhas();
        // if caso o usuário opte por modo de teste
//        if(modo.equals("Teste")) { criaRespostas(); }
        criaBotoesSubClear();
        criaBotoesCores();
    }

    // Cria uma barra em cima com as informações do jogador,
    // contendo senha, tentativas e modo.
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

    // Cria os paineis a serem preenchidos pelo jogador
    // Utilizado um vetor de 'Linha' com o número de senhas e tentativas
    public void criaLinhas() {
        for(int i = 0; i < linhas.length; i++) {
            Linha linha = new Linha(senhas);
            linhas[i] = linha;
            add(linha);
        }
    }

    // Painel com botões clicáveis
    public void criaBotoesCores() {
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

    // Cria os botões de enviar e limpar
    public void criaBotoesSubClear() {
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

    // Ação ao clicar nas cores
    private void clickColor(ActionEvent event) {
        // Pega a cor
        Cor selectedColor = Cores.getInstance().getCor(event.getActionCommand());

        if(senha < senhas) {
            // Seta o painel com a cor clicada
            if(!coresTentadas.contains(selectedColor.getCor())) {   // Verifica se não é cor repetida
                linhas[tentativa].setColor(senha, selectedColor);
                coresTentadas.add(selectedColor.getCor());
                senha++;
                System.out.println(coresTentadas.size());
            }
            else {
                JOptionPane.showMessageDialog(this, "Você já escolheu essa cor! Não tem cores repitidas na resposta.");
            }
        }

    }

    // Reseta linha para escolher novas cores
    private void limparLinha(ActionEvent ev) {
        linhas[tentativa].limpar();
        coresTentadas.clear();
        senha = 0;
    }

    // Método chamado ao clicar em enviar
    private void enviarLinha(ActionEvent ev) {

        if(senha != senhas) { // Verifica se todos campos foram preenchidos
            JOptionPane.showMessageDialog(this, "Preencha todos os campos.");
        }
        else {  // Se todos campos foram preenchidos...
            if(checaLinha()) {  // Caso o jogador tenha acertado todos as cores
                JOptionPane.showMessageDialog(this, "Parabéns, você ganhou!");
                tela.fecharTela();
            } else {  // Caso não tenha acertado todas as cores...
                if(tentativa == tentativas - 1) {  // Se acabou as tentativas
                    JOptionPane.showMessageDialog(this, "Fim, você perdeu.");
                    tela.fecharTela();
                } else {  // Se errou mas ainda tem tentativas a serem feitas
                    verificaAcertos();
                    tentativa++;
                    senha = 0;
                    lbTentativas.setText("Tentativas: " + (tentativas - tentativa));
                    coresTentadas.clear();
                }
            }
        }
    }

    // Verifica se a senha bate com as respostas do jogador
    private boolean checaLinha() {
        for(int i = 0; i < senhas; i++) {
            if(!resposta.get(i).equals(linhas[tentativa].getColor(i))) {
                return false;
            }
        }
        return true;
    }

    // Usa o método random para gerar um Int de 0 até 6
    // Para cada um desses valores existe uma cor correspondente
    private void geraSenha() {
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

    // Lógica para criar os pinos pretos e brancos em caso de algum acerto
    private void verificaAcertos() {
        // Contador para pintar sempre o próximo espaço livre
        int cont = 0;
        ArrayList<Color> posicaoCerta = new ArrayList<>();
        ArrayList<Color> posicaoErrada = new ArrayList<>();

        // Verifica se está na posição certa e pinta de preto
        for(int i = 0; i < senhas; i++) {
            Color selecionada = linhas[tentativa].getColor(i);
            Color corCerta = resposta.get(i);

            if (selecionada.equals(corCerta)) {
                linhas[tentativa].setPino(cont, "black");
                posicaoCerta.add(selecionada);
                cont++;
            }
        }

        // Verifica se não está na posição certa mas contém na resposta
        for(int i = 0; i < senhas; i++) {
            Color selecionada = linhas[tentativa].getColor(i);

            if(
                !posicaoCerta.contains(selecionada) && 
                resposta.contains(selecionada) && 
                !posicaoErrada.contains(selecionada)
            ) {
                linhas[tentativa].setPino(cont, "white");
                posicaoErrada.add(selecionada);
                cont++;
            }
        }
    }

    // Mostra a resposta gerada
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
}
