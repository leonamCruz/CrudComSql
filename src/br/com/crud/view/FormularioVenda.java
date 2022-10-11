package br.com.crud.view;

import br.com.crud.dao.ClienteDao;
import br.com.crud.dao.ProdutoDao;
import br.com.crud.model.Cliente;
import br.com.crud.model.Produto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class FormularioVenda {
    private JFormattedTextField txtCpf;
    private JFormattedTextField txtData;
    private JTextField txtNome;
    private JButton consultaPorCPFButton;
    private JTextField txtProduto;
    private JTextField txtPreco;
    private JTextField txtQtd;
    private JTextField txtCodigo;
    private JButton pesquisaCodigo;
    private JPanel root;
    private JTextField txtTotal;
    private JButton botaoPagar;
    private JButton botaoCancelarVenda;
    private JTable showTable;
    private JButton adicionarItemButton;
    private JButton trocaProduto;
    private Cliente cliente = new Cliente();
    private ClienteDao clienteDao = new ClienteDao();
    private Produto produto = new Produto();
    private ProdutoDao produtoDao = new ProdutoDao();

    double total, preco, subTotal, qtd;
    DefaultTableModel carrinho;

    public FormularioVenda() {
        root.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                carrinho = (DefaultTableModel) showTable.getModel();
                carrinho.setRowCount(0);
                mascaras();
                pullDateSystem();
                limparESetarCampos();
            }
        });
        consultaPorCPFButton.addActionListener(e -> {
            try {
                String cpf = txtCpf.getText();

                cliente = clienteDao.filtrarPorCpf(cpf);
                txtNome.setText(cliente.getNome());
            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "CPF: " + txtCpf.getText() + ", não encontrado",
                        "Não encontrei esse CPF", JOptionPane.ERROR_MESSAGE);
            }
        });
        pesquisaCodigo.addActionListener(e -> {
            if (txtCodigo.getText() == null || Objects.equals(txtCodigo.getText(), "") || txtCodigo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Campo de código vazio",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            } else {

                int codigo = Integer.parseInt(txtCodigo.getText());
                produto = produtoDao.filtrarPorNomesCodigo(codigo);

                if (produto != null) {
                    txtCodigo.setEnabled(false);
                    txtProduto.setEnabled(false);
                    txtPreco.setEnabled(false);
                    txtQtd.setEnabled(true);
                    txtProduto.setText(produto.getDescricao());
                    txtPreco.setText(String.valueOf(produto.getPreco()));
                    txtQtd.setText(String.valueOf(produto.getEstoque()));
                } else {
                    JOptionPane.showMessageDialog(null, "Inexistente", "Não Existe", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        adicionarItemButton.addActionListener(e -> {
            try {
                int qtdQueJatem = produtoDao.estoqueAtual(Integer.parseInt(txtCodigo.getText()));
                qtd = Double.parseDouble(txtQtd.getText());

                if (qtdQueJatem < qtd) {
                    JOptionPane.showMessageDialog(null, "Não há essa quantidade em estoque", "Reveja seus atos", JOptionPane.ERROR_MESSAGE);
                } else {

                    preco = Double.parseDouble(txtPreco.getText());
                    subTotal = qtd * preco;
                    total = total + subTotal;
                    txtTotal.setText(String.valueOf(total));

                    carrinho = (DefaultTableModel) showTable.getModel();

                    carrinho.addRow(new Object[]{
                            txtCodigo.getText(),
                            txtProduto.getText(),
                            txtQtd.getText(),
                            txtPreco.getText(),
                            subTotal

                    });
                    txtCodigo.setEnabled(true);
                    txtProduto.setEnabled(false);
                    txtPreco.setEnabled(false);
                    txtQtd.setEnabled(false);
                }
            } catch (Exception erro) {
                erro.printStackTrace();
            }
        });
        botaoCancelarVenda.addActionListener(e -> {
            subTotal = 0;
            total = 0;
            preco = 0;
            qtd = 0;
            limparESetarCampos();
            carrinho = (DefaultTableModel) showTable.getModel();
            carrinho.setRowCount(0);
        });
        trocaProduto.addActionListener(e -> limparESetarCampos());
        botaoPagar.addActionListener(e -> {
            if (txtCpf.getText().isEmpty() || txtNome.getText().isEmpty() ||
                    txtCodigo.getText().isEmpty() || txtProduto.getText().isEmpty() ||
                    txtPreco.getText().isEmpty() || txtQtd.getText().isEmpty() ||
                    txtTotal.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Algum Campo está vazio...",
                        "Vazio igual o sentido da vida", JOptionPane.INFORMATION_MESSAGE);

            } else {
                RodaTela.createGuiPagamentos(Double.parseDouble(txtTotal.getText()), cliente, carrinho);
            }
        });
    }

    public void limparESetarCampos() {
        txtCodigo.setText("");
        txtProduto.setText("");
        txtPreco.setText("");
        txtQtd.setText("");
        txtCodigo.setEnabled(true);
        txtProduto.setEnabled(false);
        txtPreco.setEnabled(false);
        txtQtd.setEnabled(false);
    }

    public void mascaras() {
        try {
            MaskFormatter maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.install(txtCpf);

            MaskFormatter maskData = new MaskFormatter("##/##/####");
            maskData.install(txtData);

        } catch (Exception e) {
            System.out.println("Erro");
        }
    }

    public JPanel getRoot() {
        return root;
    }

    public void showTable() {
        createTable();
    }

    private void createTable() {
        Object[][] data = {
                {}
        };
        showTable.setModel(new DefaultTableModel(data, new String[]{
                "Código",
                "Produto",
                "Qtd",
                "Preço",
                "SubTotal"}));
    }

    private void pullDateSystem() {
        Date data = new Date();
        SimpleDateFormat dataBr = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatada = dataBr.format(data);
        txtData.setText(dataFormatada);
    }
}