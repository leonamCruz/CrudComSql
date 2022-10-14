package br.com.crud.view;

import br.com.crud.dao.FornecedorDao;
import br.com.crud.model.Fornecedor;
import br.com.crud.services.impl.FornecedorServiceImpl;

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
    private Fornecedor fornecedor = new Fornecedor();
    private FornecedorServiceImpl service = new FornecedorServiceImpl();

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
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        cadastrarButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar?", "Você irá cadastrar este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);

            if (opc == 0) {
                fornecedor.setNome(txtNome.getText());
                fornecedor.setCpnj(txtCnpj.getText());
                fornecedor.setEmail(txtEmail.getText());
                fornecedor.setTelefone(txtFixo.getText());
                fornecedor.setCelular(txtCelular.getText());
                fornecedor.setCep(txtCep.getText());
                fornecedor.setEndereco(txtEndereco.getText());
                fornecedor.setNumero(Integer.parseInt(txtNumero.getText()));
                fornecedor.setComplemento(txtComplemento.getText());
                fornecedor.setBairro(txtBairro.getText());
                fornecedor.setCidade(txtCidade.getText());
                fornecedor.setEstado(Objects.requireNonNull(opcEstado.getSelectedItem()).toString());
                if (fornecedor.getNumero() < 0) {
                    JOptionPane.showMessageDialog(null, "Número Negativo...", "Isso é absurdo", JOptionPane.ERROR_MESSAGE);

                } else if (fornecedor.getNome().length() < 4) {
                    JOptionPane.showMessageDialog(null, "Nome muito pequeno", "Pequeno de mais", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean isRegistered = service.cadastrarFornecedores(fornecedor);
                    if (isRegistered){
                        JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso","Sucesso",
                                JOptionPane.DEFAULT_OPTION);
                    } else JOptionPane.showMessageDialog(null,"Houve algum problema...","Contate o suporte",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        excluirButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Você irá excluir este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opc == 0) {
                fornecedor.setId(Integer.parseInt(txtId.getText()));
                service.excluirFornecedor(fornecedor);
            }
        });
        alterarButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar?", "Você irá alterar esse cidadão!", JOptionPane.YES_NO_CANCEL_OPTION);

            if (opc == 0) {
                service.alterarFornecedor(fornecedor);
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
            fornecedor = new Fornecedor();
            txtId.setText("");
            txtNome.setText("");
            txtCnpj.setText("");
            txtEmail.setText("");
            txtFixo.setText("");
            txtCelular.setText("");
            txtCep.setText("");
            txtEndereco.setText("");
            txtNumero.setText("0");
            txtComplemento.setText("");
            txtBairro.setText("");
            txtCidade.setText("");
            opcEstado.setSelectedItem("PA");
        });
    }

    public JPanel getRoot() {
        return root;
    }

    public void selecionaTelaFornecedor(int opc) {
        tabbedPane1.setSelectedIndex(opc);
    }

    private void createTable() {
        Object[][] data = {{}};
        showTable.setModel(new DefaultTableModel(data, new String[]{"ID", "Nome", "Cnpj", "Email", "Telefone", "Celular", "Cep", "Endereço", "Numero", "Complemento", "Bairro", "Cidade", "Estado"}));
    }

    public void MainUiFornecedor() {
        createTable();
    }

    public void listarFornecedores() {
        List<Fornecedor> lista = service.listarFornecedor();
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Fornecedor fornecedor : lista)
            dados.addRow(new Object[]{fornecedor.getId(), fornecedor.getNome(), fornecedor.getCpnj(), fornecedor.getEmail(), fornecedor.getTelefone(), fornecedor.getCelular(), fornecedor.getCep(), fornecedor.getEndereco(), fornecedor.getNumero(), fornecedor.getComplemento(), fornecedor.getBairro(), fornecedor.getCidade(), fornecedor.getEstado()});
    }

    public void listarPesquisaNomeFornecedores(String nome) {
        List<Fornecedor> lista = service.filtrarPorNomesFornecedores(nome);
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Fornecedor fornecedor : lista)
            dados.addRow(new Object[]{fornecedor.getId(), fornecedor.getNome(), fornecedor.getCpnj(), fornecedor.getEmail(), fornecedor.getTelefone(), fornecedor.getCelular(), fornecedor.getCep(), fornecedor.getEndereco(), fornecedor.getNumero(), fornecedor.getComplemento(), fornecedor.getBairro(), fornecedor.getCidade(), fornecedor.getEstado()});
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