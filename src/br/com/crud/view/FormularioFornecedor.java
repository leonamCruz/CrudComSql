package br.com.crud.view;

import br.com.crud.dao.FornecedorDao;
import br.com.crud.model.Fornecedor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;

public class FormularioFornecedor {
    private JTabbedPane tabbedPane1;
    private JButton cadastrarButton;
    private JButton alterarButton;
    private JButton excluirButton;
    private JButton limparButton;
    private JTextField txtId;
    private JTextField txtNome;
    private JFormattedTextField txtCnpj;
    private JTextField txtEmail;
    private JFormattedTextField txtFixo;
    private JFormattedTextField txtCelular;
    private JFormattedTextField txtCep;
    private JTextField txtEndereco;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JComboBox opcEstado;
    private JButton pesquisarButton;
    private JTextField txtPesquisaPorNome;
    private JTable showTable;
    private JPanel root;
    FornecedorDao fornecedorDao = new FornecedorDao();
    Fornecedor obj = new Fornecedor();

    public FormularioFornecedor() {
        tabbedPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                listarFornecedores();
                try {
                    MaskFormatter maskCnpj = new MaskFormatter("##.###.###/####-##");
                    maskCnpj.install(txtCnpj);

                    MaskFormatter maskFixo = new MaskFormatter("(##)####-####");
                    maskFixo.install(txtFixo);

                    MaskFormatter maskCelular = new MaskFormatter("(##)# ####-####");
                    maskCelular.install(txtCelular);

                    MaskFormatter maskCep = new MaskFormatter("#####-###");
                    maskCep.install(txtCep);

                    txtNumero.setText("0");

                    opcEstado.setSelectedItem("PA");
                } catch (ParseException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        cadastrarButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar?", "Você irá cadastrar este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);

            if (opc == 0) {
                obj.setNome(txtNome.getText());
                obj.setCpnj(txtCnpj.getText());
                obj.setEmail(txtEmail.getText());
                obj.setTelefone(txtFixo.getText());
                obj.setCelular(txtCelular.getText());
                obj.setCep(txtCep.getText());
                obj.setEndereco(txtEndereco.getText());
                obj.setNumero(Integer.parseInt(txtNumero.getText()));
                obj.setComplemento(txtComplemento.getText());
                obj.setBairro(txtBairro.getText());
                obj.setCidade(txtCidade.getText());
                obj.setEstado(Objects.requireNonNull(opcEstado.getSelectedItem()).toString());
                if (obj.getNumero() < 0) {
                    JOptionPane.showMessageDialog(null, "Número Negativo...", "Isso é absurdo", JOptionPane.ERROR_MESSAGE);

                } else if (obj.getNome().length() < 4) {
                    JOptionPane.showMessageDialog(null, "Nome muito pequeno", "Pequeno de mais", JOptionPane.ERROR_MESSAGE);
                } else {
                    fornecedorDao.cadastrarFornecedores(obj);
                }
            }
        });
        excluirButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Você irá excluir este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opc == 0) {
                obj.setId(Integer.parseInt(txtId.getText()));
                fornecedorDao.excluirFornecedor(obj);
            }
        });
        alterarButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar?", "Você irá alterar esse cidadão!", JOptionPane.YES_NO_CANCEL_OPTION);

            if (opc == 0) {
                fornecedorDao.alterarFornecedor(obj);
                MainUiFornecedor();
            }
        });
        pesquisarButton.addActionListener(e -> {
            String nome = "%" + txtPesquisaPorNome.getText() + "%";
            listarPesquisaNomeFornecedores(nome);

        });
        showTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pegaDadosfornecedor();

            }
        });
        limparButton.addActionListener(e -> {
            txtId.setText("");
            obj.setId(-1); //Coloquei pra poder limpar os dados que ficam salvos na Ram.

            txtNome.setText("");
            obj.setNome("");

            txtCnpj.setText("");
            obj.setCpnj("");

            txtEmail.setText("");
            obj.setEmail("");

            txtFixo.setText("");
            obj.setTelefone("");

            txtCelular.setText("");
            obj.setCelular("");

            txtCep.setText("");
            obj.setCep("");

            txtEndereco.setText("");
            obj.setEndereco("");

            txtNumero.setText("0");
            obj.setNumero(0);

            txtComplemento.setText("");
            obj.setComplemento("");

            txtBairro.setText("");
            obj.setBairro("");

            txtCidade.setText("");
            obj.setBairro("");

            opcEstado.setSelectedItem("PA");
            obj.setEstado("");
        }
    );
}

    public JPanel getRoot() {
        return root;
    }

    public void selecionaTelaFornecedor(int opc) {
        tabbedPane1.setSelectedIndex(opc);
    }

    private void createTable() {
        Object[][] data = {{}};
        showTable.setModel(new DefaultTableModel(data, new String[]{"ID",
                "Nome",
                "Cnpj",
                "Email",
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

    public void MainUiFornecedor() {
        createTable();
    }

    public void listarFornecedores() {
        List<Fornecedor> lista = fornecedorDao.listarFornecedores();
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Fornecedor f : lista)
            dados.addRow(new Object[]{f.getId(),
                    f.getNome(),
                    f.getCpnj(),
                    f.getEmail(),
                    f.getTelefone(),
                    f.getCelular(),
                    f.getCep(),
                    f.getEndereco(),
                    f.getNumero(),
                    f.getComplemento(),
                    f.getBairro(),
                    f.getCidade(),
                    f.getEstado()});
    }

    public void listarPesquisaNomeFornecedores(String nome) {
        List<Fornecedor> lista = fornecedorDao.filtrarPorNomesFornecedores(nome);
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Fornecedor f : lista)
            dados.addRow(new Object[]{f.getId(),
                    f.getNome(),
                    f.getCpnj(),
                    f.getEmail(),
                    f.getTelefone(),
                    f.getCelular(),
                    f.getCep(),
                    f.getEndereco(),
                    f.getNumero(),
                    f.getComplemento(),
                    f.getBairro(),
                    f.getCidade(),
                    f.getEstado()});
    }

    public void pegaDadosfornecedor() {
        tabbedPane1.setSelectedIndex(0);
        int linhaSelecionada = showTable.getSelectedRow();
        txtId.setText(showTable.getValueAt(linhaSelecionada, 0).toString());
        txtNome.setText(showTable.getValueAt(linhaSelecionada, 1).toString());
        txtCnpj.setText(showTable.getValueAt(linhaSelecionada, 2).toString());
        txtEmail.setText(showTable.getValueAt(linhaSelecionada, 3).toString());
        txtFixo.setText(showTable.getValueAt(linhaSelecionada, 4).toString());
        txtCelular.setText(showTable.getValueAt(linhaSelecionada, 5).toString());
        txtCep.setText(showTable.getValueAt(linhaSelecionada, 6).toString());
        txtEndereco.setText(showTable.getValueAt(linhaSelecionada, 7).toString());
        txtNumero.setText(showTable.getValueAt(linhaSelecionada, 8).toString());
        txtComplemento.setText(showTable.getValueAt(linhaSelecionada, 9).toString());
        txtBairro.setText(showTable.getValueAt(linhaSelecionada, 10).toString());
        txtCidade.setText(showTable.getValueAt(linhaSelecionada, 11).toString());
        opcEstado.setSelectedItem(showTable.getValueAt(linhaSelecionada, 12));
    }
}