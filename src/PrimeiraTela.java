import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class PrimeiraTela extends JFrame {

    private JComboBox<Integer> cbQuantSenha;
    private JComboBox<Integer> cbquantTentativa;
    private JComboBox<String> cbMode;

    public PrimeiraTela() {
        Integer[] qntSenha = new Integer[]{4, 5, 6};
        Integer[] qntTentativa = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        String[] modes = new String[]{"Normal", "Teste"};

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cbQuantSenha = new JComboBox<>(qntSenha);
        cbquantTentativa = new JComboBox<>(qntTentativa);
        cbMode = new JComboBox<>(modes);
        JButton btnComecar = new JButton("Comecar");

        JLabel lblQuantSenha = new JLabel("Quantas senhas?");
        JLabel lblQuantTentativa = new JLabel("Quantas tentativas?");
        JLabel lblMode = new JLabel("Modo de jogo:");

        Container contentPane = getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel rowSenha = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rowTentativas = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel rowModo = new JPanel(new FlowLayout(FlowLayout.CENTER));

        rowSenha.add(lblQuantSenha);
        rowSenha.add(cbQuantSenha);

        rowTentativas.add(lblQuantTentativa);
        rowTentativas.add(cbquantTentativa);

        rowModo.add(lblMode);
        rowModo.add(cbMode);

        contentPane.add(rowSenha);
        contentPane.add(rowTentativas);
        contentPane.add(rowModo);
        contentPane.add(btnComecar);

        btnComecar.addActionListener(this::botaoStart);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void botaoStart(ActionEvent ev) {
        int senhas = cbQuantSenha.getItemAt(cbQuantSenha.getSelectedIndex());
        int tentativas = cbquantTentativa.getItemAt(cbquantTentativa.getSelectedIndex());
        String modo = cbMode.getItemAt(cbMode.getSelectedIndex());

        SegundaTela segundaTela = new SegundaTela(senhas, tentativas, modo);
        segundaTela.setVisible(true);
        setVisible(false);
    }
}
