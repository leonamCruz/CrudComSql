package br.com.crud.view;

import br.com.crud.model.Funcionario;
import br.com.crud.services.FuncionarioService;
import br.com.crud.services.impl.FuncionarioServiceImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class FormularioFuncionario {
    private JTabbedPane abas;
    private JTextField txtId;
    private JTextField txtNome;
    private JFormattedTextField txtRg;
    private JFormattedTextField txtCpf;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JComboBox opcAcesso;
    private JTextField txtCargo;
    private JFormattedTextField txtFixo;
    private JFormattedTextField txtCelular;
    private JFormattedTextField txtCep;
    private JTextField txtEndereco;
    private JButton cadastrarButton;
    private JButton alterarButton;
    private JButton excluirButton;
    private JButton limparButton;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JComboBox opcEstado;
    private JTable showTable;
    private JPanel root;
    private JButton pesquisarButton;
    private JTextField txtPesquisa;
    private Funcionario funcionario = new Funcionario();
    private FuncionarioService service = new FuncionarioServiceImpl();


    public FormularioFuncionario() {

        abas.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                mascaras();
                txtNumero.setText("0");
                opcAcesso.setSelectedItem("User");
                opcEstado.setSelectedItem("PA");
                listar();
            }

            @Override
            public void focusLost(FocusEvent e) {
            }
        });
        cadastrarButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar?",
                    "Você irá cadastrar este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);

            if (opc == 0) {
                funcionario.setNome(txtNome.getText());
                funcionario.setRg(txtRg.getText());
                funcionario.setCpf(txtCpf.getText());
                funcionario.setEmail(txtEmail.getText());
                funcionario.setSenhaFuncionario(txtSenha.getText());
                funcionario.setCargo(txtCargo.getText());
                funcionario.setNivelDeAcesso(opcAcesso.getSelectedIndex() == 0);
                funcionario.setTelefone(txtFixo.getText());
                funcionario.setCelular(txtCelular.getText());
                funcionario.setCep(txtCep.getText());
                funcionario.setEndereco(txtEndereco.getText());
                funcionario.setNumero(Integer.parseInt(txtNumero.getText()));
                funcionario.setComplemento(txtComplemento.getText());
                funcionario.setBairro(txtBairro.getText());
                funcionario.setCidade(txtCidade.getText());
                funcionario.setEstado(Objects.requireNonNull(opcEstado.getSelectedItem()).toString());

                if (funcionario.getNumero() < 0) {
                    JOptionPane.showMessageDialog(null, "Número Negativo...", "Isso é absurdo", JOptionPane.ERROR_MESSAGE);

                } else if (funcionario.getNome().length() < 4) {
                    JOptionPane.showMessageDialog(null, "Nome muito pequeno", "Pequeno de mais", JOptionPane.ERROR_MESSAGE);
                } else {
                    service.cadastrarFuncionario(funcionario);
                }
            }
        });
        pesquisarButton.addActionListener(e -> {
            String nome = "%" + txtPesquisa.getText() + "%";
            listarPesquisaNomeFuncionario(nome);
        });
        limparButton.addActionListener(e -> limpar());

        excluirButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?",
                    "Você irá excluir este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opc == 0) {
                funcionario.setId(Integer.parseInt(txtId.getText()));
                service.excluirFuncionario(funcionario);
            }
        });
        alterarButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar?",
                    "Você irá alterar esse cidadão!", JOptionPane.YES_NO_CANCEL_OPTION);

            if (opc == 0) {
                funcionario.setId(Integer.parseInt(txtId.getText()));
                funcionario.setNome(txtNome.getText());
                funcionario.setRg(txtRg.getText());
                funcionario.setCpf(txtCpf.getText());
                funcionario.setEmail(txtEmail.getText());
                funcionario.setTelefone(txtFixo.getText());
                funcionario.setCelular(txtCelular.getText());
                funcionario.setCep(txtCep.getText());
                funcionario.setEndereco(txtEndereco.getText());
                funcionario.setNumero(Integer.parseInt(txtNumero.getText()));
                funcionario.setComplemento(txtComplemento.getText());
                funcionario.setBairro(txtBairro.getText());
                funcionario.setCidade(txtCidade.getText());
                funcionario.setEstado(Objects.requireNonNull(opcEstado.getSelectedItem()).toString());
                service.alterarFuncionario(funcionario);
            }
        });
        showTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pegaDadosFornecedor();
            }
        });
        root.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                listar();
            }
        });
    }

    public JPanel getRoot() {
        return root;
    }

    public void MainUI() {
        createTable();
    }

    private void createTable() {
        Object[][] data = {
                {}
        };
        showTable.setModel(new DefaultTableModel(data, new String[]{
                "ID",
                "Nome",
                "Rg",
                "Cpf",
                "Email",
                "Senha",
                "Cargo",
                "Nivel de Acesso",
                "Telefone",
                "Celular",
                "Cep",
                "Endereço",
                "Numero",
                "Complemento",
                "Bairro",
                "Cidade",
                "Estado"}));
    }

    private void listar() {
        List<Funcionario> lista = service.listarFuncionarios();
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Funcionario f : lista)
            dados.addRow(new Object[]{
                    f.getId(),
                    f.getNome(),
                    f.getRg(),
                    f.getCpf(),
                    f.getEmail(),
                    f.getSenha(),
                    f.getCargo(),
                    f.getNivelDeAcesso(),
                    f.getTelefone(),
                    f.getCelular(),
                    f.getCep(),
                    f.getEndereco(),
                    f.getNumero(),
                    f.getComplemento(),
                    f.getBairro(),
                    f.getCidade(),
                    f.getEstado()
            });
    }

    private void mascaras() {

        try {
            MaskFormatter maskRg = new MaskFormatter("######-#");
            maskRg.install(txtRg);

            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(txtCpf);

            MaskFormatter maskTelefone = new MaskFormatter("####-####");
            maskTelefone.install(txtFixo);

            MaskFormatter maskCelular = new MaskFormatter("(##) # ####-####");
            maskCelular.install(txtCelular);

            MaskFormatter maskCep = new MaskFormatter("#####-###");
            maskCep.install(txtCep);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void limpar() {
        funcionario = new Funcionario();
        txtId.setText("");
        txtNome.setText("");
        txtRg.setText("");
        txtCpf.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
        txtCargo.setText("");
        opcAcesso.setSelectedItem("User");
        txtFixo.setText("");
        txtCelular.setText("");
        txtCep.setText("");
        txtEndereco.setText("");
        txtNumero.setText("0");
        txtComplemento.setText("");
        txtBairro.setText("");
        txtCidade.setText("");
        opcEstado.setSelectedItem("PA");
    }

    private void pegaDadosFornecedor() {
        abas.setSelectedIndex(0);
        int linhaSelecionada = showTable.getSelectedRow();
        txtId.setText(showTable.getValueAt(linhaSelecionada, 0).toString());
        txtNome.setText(showTable.getValueAt(linhaSelecionada, 1).toString());
        txtRg.setText(showTable.getValueAt(linhaSelecionada, 2).toString());
        txtCpf.setText(showTable.getValueAt(linhaSelecionada, 3).toString());
        txtEmail.setText(showTable.getValueAt(linhaSelecionada, 4).toString());
        txtSenha.setText(showTable.getValueAt(linhaSelecionada, 5).toString());
        txtCargo.setText(showTable.getValueAt(linhaSelecionada, 6).toString());
        opcAcesso.setSelectedItem(showTable.getValueAt(linhaSelecionada, 7).toString());
        txtFixo.setText(showTable.getValueAt(linhaSelecionada, 8).toString());
        txtCelular.setText(showTable.getValueAt(linhaSelecionada, 9).toString());
        txtCep.setText(showTable.getValueAt(linhaSelecionada, 10).toString());
        txtEndereco.setText(showTable.getValueAt(linhaSelecionada, 11).toString());
        txtNumero.setText(showTable.getValueAt(linhaSelecionada, 12).toString());
        txtComplemento.setText(showTable.getValueAt(linhaSelecionada, 13).toString());
        txtBairro.setText(showTable.getValueAt(linhaSelecionada, 14).toString());
        txtCidade.setText(showTable.getValueAt(linhaSelecionada, 15).toString());
        opcEstado.setSelectedItem(showTable.getValueAt(linhaSelecionada, 16));
    }
    private void listarPesquisaNomeFuncionario(String nome) {
        List<Funcionario> lista = service.filtrarPorNomes(nome);
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Funcionario funcionario : lista)
            dados.addRow(new Object[]{
                    funcionario.getId(),
                    funcionario.getNome(),
                    funcionario.getRg(),
                    funcionario.getCpf(),
                    funcionario.getEmail(),
                    funcionario.getSenha(),
                    funcionario.getCargo(),
                    funcionario.getNivelDeAcesso(),
                    funcionario.getTelefone(),
                    funcionario.getCelular(),
                    funcionario.getCep(),
                    funcionario.getEndereco(),
                    funcionario.getNumero(),
                    funcionario.getComplemento(),
                    funcionario.getBairro(),
                    funcionario.getCidade(),
                    funcionario.getEstado()
            });
    }
    void selecionaTelaFun(byte opc) {
        abas.setSelectedIndex(opc);
    }
}