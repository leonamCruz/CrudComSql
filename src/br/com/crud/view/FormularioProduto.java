package br.com.crud.view;

import br.com.crud.dao.FornecedorDao;
import br.com.crud.dao.ProdutoDao;
import br.com.crud.model.Fornecedor;
import br.com.crud.model.Produto;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;

public class FormularioProduto {
    private JTabbedPane tabbedPane1;
    private JButton cadastrarButton;
    private JButton alterarButton;
    private JButton excluirButton;
    private JButton limparButton;
    private JTextField txtPesquisaNome;
    private JButton letSGoButton;
    private JTable showTable;
    private JTextField txtId;
    private JTextField txtDescricao;
    private JFormattedTextField txtPreco;
    private JTextField txtQtd;
    private JComboBox<Fornecedor> opcFornecedor;
    private JPanel root;

    ProdutoDao produtoDao = new ProdutoDao();
    Produto obj = new Produto();
    public FormularioProduto() {
        opcFornecedor.addAncestorListener(new AncestorListener() {
            @Override
            public void ancestorAdded(AncestorEvent event) {
                FornecedorDao fornecedorDao = new FornecedorDao();
                List<Fornecedor> listaDeFornecedores =  fornecedorDao.listarFornecedores();
                opcFornecedor.removeAll();
                for(Fornecedor f : listaDeFornecedores){
                    opcFornecedor.addItem(f);
                }
            }

            @Override
            public void ancestorRemoved(AncestorEvent event) {

            }

            @Override
            public void ancestorMoved(AncestorEvent event) {

            }
        });
        cadastrarButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null,"Deseja realmente cadastrar?",
                    "Deseja Cadastrar?",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE);

            if(opc == 0){

                obj.setDescricao(txtDescricao.getText());
                obj.setPreco(Double.parseDouble(txtPreco.getText()));
                obj.setEstoque(Integer.parseInt(txtQtd.getText()));

                Fornecedor fornecedor = (Fornecedor)opcFornecedor.getSelectedItem();

                obj.setFornecedores(fornecedor);

                produtoDao.cadastrarProdutos(obj);
            }
        });
        tabbedPane1.addContainerListener(new ContainerAdapter() {
            @Override
            public void componentAdded(ContainerEvent e) {
                super.componentAdded(e);
                listarProdutos();
            }
        });
        tabbedPane1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                listarProdutos();
            }
        });
        tabbedPane1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                listarProdutos();
                FornecedorDao fornecedorDao = new FornecedorDao();
                List<Fornecedor> listaDeFornecedores = fornecedorDao.listarFornecedores();
                opcFornecedor.removeAllItems();
                for (Fornecedor f : listaDeFornecedores){
                    opcFornecedor.addItem(f);
                }
            }
        });
        alterarButton.addActionListener(e->{
            int opc = JOptionPane.showConfirmDialog(null, "Deseja Alterar?",
                    "Realmente deseja isso????", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);
            if (opc == 0) {
                obj.setId(Integer.parseInt(txtId.getText()));
                obj.setDescricao(txtDescricao.getText());

                double preco = Double.parseDouble(txtPreco.getText());
                if(preco <= 0){
                    JOptionPane.showMessageDialog(null,"Está dando de graça?","Preço Menor que 0",
                            JOptionPane.ERROR_MESSAGE);
                }
                else {
                    obj.setPreco(Double.parseDouble(txtPreco.getText()));
                }
                int estoque = Integer.parseInt(txtQtd.getText());
                if (estoque <= 0){
                    JOptionPane.showMessageDialog(null,"Tu vai vender o que não tem?","Estoque nulo ou negativo",
                            JOptionPane.ERROR_MESSAGE);
                }
                obj.setEstoque(Integer.parseInt(txtQtd.getText()));

                Fornecedor fornecedor = (Fornecedor)opcFornecedor.getSelectedItem();

                obj.setFornecedores(fornecedor);
                produtoDao.alterarProdutos(obj);
            }
        });
        excluirButton.addActionListener(e->{
            int opc = JOptionPane.showConfirmDialog(null,"Realmente deseja fazer isso?",
                    "Você irá excluir esse produto!!!",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE);

            if (opc == 0){
                obj.setId(Integer.parseInt(txtId.getText()));
                produtoDao.excluirProdutos(obj);
            }
        });
        limparButton.addActionListener(e->{
            txtId.setText("");
            obj.setId(-1);

            txtDescricao.setText("");
            obj.setDescricao("");

            txtPreco.setText("");
            obj.setPreco(0);

            txtQtd.setText("");
            obj.setEstoque(0);

            opcFornecedor.setSelectedItem("Irineu");
        });
        letSGoButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                String nome = "%" + txtPesquisaNome.getText() + "%";
                listarProdutosPorNome(nome);
            }
        });
        showTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pegaDados();
            }
        });
    }

    private void listarProdutos() {
        List<Produto> lista = produtoDao.listarProdutos();
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Produto c : lista)
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getDescricao(),
                    c.getPreco(),
                    c.getEstoque(),
                    c.getFornecedores()
            });
    }
    private void listarProdutosPorNome(String nome){
        List<Produto> lista = produtoDao.filtrarPorNomesProdutos(nome);
        DefaultTableModel dados  = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Produto c : lista) {
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getDescricao(),
                    c.getPreco(),
                    c.getEstoque(),
                    c.getFornecedores()
            });
        }
    }
    public void selecionaTelaProdutos(int opc) {
        tabbedPane1.setSelectedIndex(opc);
    }

    public JPanel getRoot() {
    return root;
    }

    public void MainUiProdutos() {
        createTableProdutos();
    }

    private void createTableProdutos() {
        Object[][] data = {
                {}
        };
        showTable.setModel(new DefaultTableModel(data, new String[]{
        "ID",
        "Descrição",
        "Preço",
        "Estoque",
        "Fornecedor"
        }));
    }
    private void pegaDados(){
        tabbedPane1.setSelectedIndex(1);
        int linhaSelecionada = showTable.getSelectedRow();
        txtId.setText(showTable.getValueAt(linhaSelecionada,0).toString());
        txtDescricao.setText(showTable.getValueAt(linhaSelecionada,1).toString());
        txtPreco.setText(showTable.getValueAt(linhaSelecionada,2).toString());
        txtQtd.setText(showTable.getValueAt(linhaSelecionada,3).toString());
        opcFornecedor.setSelectedItem(showTable.getValueAt(linhaSelecionada,4));
    }
}