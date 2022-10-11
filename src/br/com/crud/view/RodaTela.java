package br.com.crud.view;
import br.com.crud.model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RodaTela {
    static byte opc;
    static String userLogado;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RodaTela::createGuiLogin);
    }

    public static void createGuiLogin(){
        Login tela = new Login();
        JPanel root = tela.getRoot();
        JFrame frame = new JFrame("Login ");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(1000,200 );
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public static void createGuiFormularioClientes(){
        FormularioCliente formularioCliente = new FormularioCliente();
        formularioCliente.selecionaTela(opc);
        JPanel root = formularioCliente.getRoot();
        JFrame frame = new JFrame("Use com sabedoria, " + userLogado);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(720,1280);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        formularioCliente.MainUI();
    }
    public static void createGuiMenuPrincipal(){
        MenuPrincipal mp = new MenuPrincipal();
        JPanel root = mp.getRoot();
        JFrame frame = new JFrame("Seja bem-vindo: " + userLogado);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setResizable(false);
        frame.setSize(1280,720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void createGuiFormulariosFuncionarios() {
        FormularioFuncionario ff = new FormularioFuncionario();
        ff.selecionaTelaFun(opc);
        JPanel root = ff.getRoot();
        JFrame frame = new JFrame("Nem só de pão viverá o homem, " + userLogado);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(720,1280);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ff.MainUI();
    }
    public static void createGuiFormularioFornecedores(){
        FormularioFornecedor fFornecedores = new FormularioFornecedor();
        fFornecedores.selecionaTelaFornecedor(opc);
        JPanel root = fFornecedores.getRoot();
        JFrame frame = new JFrame("Quando o mundo vira as costas pro " + userLogado + ", você vira as costas" +
                " para o mundo");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(720,1280);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        fFornecedores.MainUiFornecedor();
    }
    public static void createGuiFormularioProdutos(){
        FormularioProduto fp = new FormularioProduto();
        fp.selecionaTelaProdutos(opc);
        JPanel root = fp.getRoot();
        JFrame frame = new JFrame(userLogado + " , a verdade sempre vem a tona.");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(720,1280);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        fp.MainUiProdutos();
    }
    public static void createGuiVendas(){
        FormularioVenda formularioVenda = new FormularioVenda();
        JPanel root = formularioVenda.getRoot();
        JFrame frame = new JFrame(userLogado + ", o trabalho da dignidade ao homem.");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(1280,720);
        frame.setLocationRelativeTo(null);
        formularioVenda.showTable();
        frame.setVisible(true);
    }
    public static void createGuiPagamentos(double valorTotal, Cliente obj, DefaultTableModel carrinho){
        FormularioPagamento formularioPagamento = new FormularioPagamento(valorTotal, obj,carrinho);
        JPanel root = formularioPagamento.getRoot();
        JFrame frame = new JFrame(userLogado + " , não devo, não temo!");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.pack();
        frame.setSize(500,200);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
}