package br.com.crud.view;

import br.com.crud.dao.ItemVendaDao;
import br.com.crud.dao.ProdutoDao;
import br.com.crud.dao.VendaDao;
import br.com.crud.model.Cliente;
import br.com.crud.model.ItemVenda;
import br.com.crud.model.Produto;
import br.com.crud.model.Venda;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormularioPagamento {
    Cliente clientes;
    DefaultTableModel carrinho;
    private JPanel root;
    private JLabel valorVenda;
    private JTextField txtValorPago;
    private JLabel troco;
    private JButton finalizarVendaButton;
    private JTextArea txtObs;

    public FormularioPagamento(double valorTotal, Cliente obj, DefaultTableModel carrinho) {
        this.clientes = obj;
        this.carrinho = carrinho;

        valorVenda.setText(String.valueOf(valorTotal));
        txtValorPago.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    double valorTotal = Double.parseDouble(valorVenda.getText());
                    double valorPago = Double.parseDouble(txtValorPago.getText());
                    double diferenca = valorPago - valorTotal;
                    String trocoFormatado = String.format("%.2f", diferenca);

                    if (valorPago < valorTotal) {
                        JOptionPane.showMessageDialog(null, "Pagou menos que o valor do produto",
                                "Você está sendo engalobado", JOptionPane.ERROR_MESSAGE);
                    } else {
                        troco.setText(trocoFormatado);
                    }
                }
            }
        });
        finalizarVendaButton.addActionListener(e -> {
            Venda venda = new Venda();

            venda.setClientes(clientes);

            Date data = new Date();
            SimpleDateFormat dataBr = new SimpleDateFormat("yyyy-MM-dd");
            String dataMySql = dataBr.format(data);

            venda.setData(dataMySql);
            venda.setTotalDaVenda(valorTotal);
            venda.setObs(txtObs.getText());

            VendaDao vendaDao = new VendaDao();
            vendaDao.cadastrarVenda(venda);

            venda.setId(vendaDao.retornaUltimaVenda());

            for (int i = 0; i < carrinho.getRowCount(); i++) {
                int qtdQueJaTem, qtdComprada, qtdAtualizada;
                Produto produto = new Produto();
                ItemVenda item = new ItemVenda();
                ProdutoDao produtoDao = new ProdutoDao();
                item.setVendas(venda);

                produto.setId(Integer.parseInt(carrinho.getValueAt(i, 0).toString()));

                item.setProdutos(produto);

                item.setQtd(Integer.parseInt(carrinho.getValueAt(i, 2).toString()));
                item.setSubTotal(Double.parseDouble(carrinho.getValueAt(i, 4).toString()));

                qtdQueJaTem = produtoDao.estoqueAtual(produto.getId());
                qtdComprada = item.getQtd();
                if (qtdQueJaTem < qtdComprada) {
                    JOptionPane.showMessageDialog(null, "Infelizmente não temos essa quantidade",
                            "Reveja o Estoque", JOptionPane.ERROR_MESSAGE);
                } else {
                    qtdAtualizada = qtdQueJaTem - qtdComprada;

                    produtoDao.atualizaEstoque(produto.getId(), qtdAtualizada);

                    ItemVendaDao itemVendaDao = new ItemVendaDao();
                    itemVendaDao.cadastraItem(item);
                }
            }
        });
    }

    public JPanel getRoot() {
        return root;
    }
}
