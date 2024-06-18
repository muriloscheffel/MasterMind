import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PainelOpcoes extends JPanel {
    private JComboBox<Integer> cbQuantSenha;
    private JComboBox<Integer> cbquantTentativa;
    private JComboBox<String> cbMode;
    private Integer[] qntSenha;
    private Integer[] qntTentativa;
    private String[] modes;
    private Tela tela;


    public PainelOpcoes(Tela tela) {
        qntSenha = new Integer[]{4, 5, 6};
        qntTentativa = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        modes = new String[]{"Normal", "Teste"};
        this.tela = tela;

        inicializa();
    }

    public void inicializa() {
        cbQuantSenha = new JComboBox<>(qntSenha);
        cbquantTentativa = new JComboBox<>(qntTentativa);
        cbMode = new JComboBox<>(modes);
        JButton btnComecar = new JButton("Comecar");

        JLabel lblQuantSenha = new JLabel("Quantas senhas?");
        JLabel lblQuantTentativa = new JLabel("Quantas tentativas?");
        JLabel lblMode = new JLabel("Modo de jogo:");


        JPanel rowSenha = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rowTentativas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rowModo = new JPanel(new FlowLayout(FlowLayout.CENTER));

        rowSenha.add(lblQuantSenha);
        rowSenha.add(cbQuantSenha);

        rowTentativas.add(lblQuantTentativa);
        rowTentativas.add(cbquantTentativa);

        rowModo.add(lblMode);
        rowModo.add(cbMode);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(rowSenha);
        add(rowTentativas);
        add(rowModo);
        add(btnComecar);

        btnComecar.addActionListener(this::botaoStart);

        setVisible(true);
    }

    public void botaoStart(ActionEvent ev) {
        int senhas = cbQuantSenha.getItemAt(cbQuantSenha.getSelectedIndex());
        int tentativas = cbquantTentativa.getItemAt(cbquantTentativa.getSelectedIndex());
        String modo = cbMode.getItemAt(cbMode.getSelectedIndex());

        tela.iniciarJogo(senhas, tentativas, modo);
    }
}
