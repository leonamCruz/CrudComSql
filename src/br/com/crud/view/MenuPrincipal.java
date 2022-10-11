package br.com.crud.view;

import javax.swing.*;

public class MenuPrincipal {
    private JPanel root;
    private JMenuItem menuConsultaCli;
    private JMenuItem menuCadastrarCli;
    private JMenuItem cadastrarFuncionariosMenuItem;
    private JMenuItem consultarFuncionariosMenuItem;
    private JMenuItem consultaFornecedor;
    private JMenuItem cadastraFornecedor;
    private JMenuItem consultarProdutos;
    private JMenuItem cadastrarProdutos;
    private JMenu menuVendas;
    private JMenuItem itemVenda;
    private JMenu produtosMenu;
    private JMenu fornecedoresMenu;
    private JMenu Clientes;
    private JMenu funcionariosMenu;
    private JMenuBar menuRoot;


    public JPanel getRoot() {
        return root;
    }

    public MenuPrincipal() {
        menuConsultaCli.addActionListener(e -> {
            RodaTela.opc = 0;
            RodaTela.createGuiFormularioClientes();
        });
        menuCadastrarCli.addActionListener(e -> {
            RodaTela.opc = 1;
            RodaTela.createGuiFormularioClientes();
        });

        cadastrarFuncionariosMenuItem.addActionListener(e -> {
            RodaTela.opc = 0;
            RodaTela.createGuiFormulariosFuncionarios();
        });
        consultarFuncionariosMenuItem.addActionListener(e -> {
            RodaTela.opc = 1;
            RodaTela.createGuiFormulariosFuncionarios();
        });
        cadastraFornecedor.addActionListener(e ->{
            RodaTela.opc = 0;
            RodaTela.createGuiFormularioFornecedores();
        });
        consultaFornecedor.addActionListener(e ->{
            RodaTela.opc = 1;
            RodaTela.createGuiFormularioFornecedores();
        });
        consultarProdutos.addActionListener(e->{
            RodaTela.opc = 0;
            RodaTela.createGuiFormularioProdutos();
        });
        cadastrarProdutos.addActionListener(e->{
            RodaTela.opc = 1;
            RodaTela.createGuiFormularioProdutos();
        });
        itemVenda.addActionListener(e->{
            RodaTela.createGuiVendas();
        });
    }
}