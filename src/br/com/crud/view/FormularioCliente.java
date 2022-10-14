package br.com.crud.view;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

import br.com.crud.model.Cliente;
import br.com.crud.services.ClienteService;
import br.com.crud.services.impl.ClienteServiceImpl;

public class FormularioCliente {
    private JTabbedPane abas;
    private JTextField txtNome;
    private JTextField txtEmail;
    private JFormattedTextField txtFixo;
    private JFormattedTextField txtCpf;
    private JFormattedTextField txtRg;
    private JFormattedTextField txtCelular;
    private JFormattedTextField txtCep;
    private JTextField txtEndereco;
    private JTextField txtNumero;
    private JTextField txtComplemento;
    private JTextField txtBairro;
    private JTextField txtCidade;
    private JComboBox<?> opcEstado;
    private JButton limparButton;
    private JButton cadastrarButton;
    private JButton pesquisarButton;
    private JTable showTable;
    private JPanel root;
    private JTextField txtId;
    private JButton excluirButton1;
    private JButton alterarButton1;
    private JTextField txtNomePesquisa;
    private JButton botaoPesquisaNome;
    private Cliente cliente = new Cliente();
    private ClienteService service = new ClienteServiceImpl();

    private void listar() {
        List<Cliente> lista = service.listarClientes();
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Cliente c : lista)
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getRg(),
                    c.getCpf(),
                    c.getEmail(),
                    c.getTelefone(),
                    c.getCelular(),
                    c.getCep(),
                    c.getEndereco(),
                    c.getNumero(),
                    c.getComplemento(),
                    c.getBairro(),
                    c.getCidade(),
                    c.getEstado()
            });
    }

    private void listarPesquisaNome(String nome) {
        List<Cliente> lista = service.filtrarPorNomes(nome);
        DefaultTableModel dados = (DefaultTableModel) showTable.getModel();
        dados.setRowCount(0);
        for (Cliente c : lista)
            dados.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getRg(),
                    c.getCpf(),
                    c.getEmail(),
                    c.getTelefone(),
                    c.getCelular(),
                    c.getCep(),
                    c.getEndereco(),
                    c.getNumero(),
                    c.getComplemento(),
                    c.getBairro(),
                    c.getCidade(),
                    c.getEstado()
            });
    }

    private void pegaDados() {
        abas.setSelectedIndex(1);
        int linhaSelecionada = showTable.getSelectedRow();
        txtId.setText(showTable.getValueAt(linhaSelecionada, 0).toString());
        txtNome.setText(showTable.getValueAt(linhaSelecionada, 1).toString());
        txtRg.setText(showTable.getValueAt(linhaSelecionada, 2).toString());
        txtCpf.setText(showTable.getValueAt(linhaSelecionada, 3).toString());
        txtEmail.setText(showTable.getValueAt(linhaSelecionada, 4).toString());
        txtFixo.setText(showTable.getValueAt(linhaSelecionada, 5).toString());
        txtCelular.setText(showTable.getValueAt(linhaSelecionada, 6).toString());
        txtCep.setText(showTable.getValueAt(linhaSelecionada, 7).toString());
        txtEndereco.setText(showTable.getValueAt(linhaSelecionada, 8).toString());
        txtNumero.setText(showTable.getValueAt(linhaSelecionada, 9).toString());
        txtComplemento.setText(showTable.getValueAt(linhaSelecionada, 10).toString());
        txtBairro.setText(showTable.getValueAt(linhaSelecionada, 11).toString());
        txtCidade.setText(showTable.getValueAt(linhaSelecionada, 12).toString());
        opcEstado.setSelectedItem(showTable.getValueAt(linhaSelecionada, 13));
    }

    public FormularioCliente() {
        cadastrarButton.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente cadastrar?",
                    "Você irá cadastrar este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);

            if (opc == 0) {
                cliente.setNome(txtNome.getText());
                cliente.setRg(txtRg.getText());
                cliente.setCpf(txtCpf.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setTelefone(txtFixo.getText());
                cliente.setCelular(txtCelular.getText());
                cliente.setCep(txtCep.getText());
                cliente.setEndereco(txtEndereco.getText());
                cliente.setNumero(Integer.parseInt(txtNumero.getText()));
                cliente.setComplemento(txtComplemento.getText());
                cliente.setBairro(txtBairro.getText());
                cliente.setCidade(txtCidade.getText());
                cliente.setEstado(Objects.requireNonNull(opcEstado.getSelectedItem()).toString());

                if (cliente.getNumero() < 0) {
                    JOptionPane.showMessageDialog(null, "Número Negativo...",
                            "Isso é absurdo", JOptionPane.ERROR_MESSAGE);

                } else if (cliente.getNome().length() < 4) {
                    JOptionPane.showMessageDialog(null, "Nome muito pequeno",
                            "Pequeno de mais", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean isCadastred = service.cadastrarCliente(cliente);
                    if(isCadastred){
                        JOptionPane.showMessageDialog(null,"Cadastrado com Sucesso","Sucesso",
                                JOptionPane.DEFAULT_OPTION);
                    }else JOptionPane.showMessageDialog(null,"Houve Algum Problema...","Contate o Suporte",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pesquisarButton.addActionListener(e -> listar());
        showTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                pegaDados();
            }
        });
        excluirButton1.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?",
                    "Você irá excluir este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opc == 0) {
                cliente.setId(Integer.parseInt(txtId.getText()));
                boolean isExcluded = service.excluirClientes(cliente);
                if(isExcluded){
                    JOptionPane.showMessageDialog(null,"Excluido com sucesso","Sucesso",
                            JOptionPane.DEFAULT_OPTION);
                } else JOptionPane.showMessageDialog(null,"Houve Algum Problema...","Contate o Suporte",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        alterarButton1.addActionListener(e -> {
            int opc = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar?",
                    "Você irá alterar este cidadão", JOptionPane.YES_NO_CANCEL_OPTION);
            if (opc == 0) {
                cliente.setId(Integer.parseInt(txtId.getText()));
                cliente.setNome(txtNome.getText());
                cliente.setRg(txtRg.getText());
                cliente.setCpf(txtCpf.getText());
                cliente.setEmail(txtEmail.getText());
                cliente.setTelefone(txtFixo.getText());
                cliente.setCelular(txtCelular.getText());
                cliente.setCep(txtCep.getText());
                cliente.setEndereco(txtEndereco.getText());
                cliente.setNumero(Integer.parseInt(txtNumero.getText()));
                cliente.setComplemento(txtComplemento.getText());
                cliente.setBairro(txtBairro.getText());
                cliente.setCidade(txtCidade.getText());
                cliente.setEstado(Objects.requireNonNull(opcEstado.getSelectedItem()).toString());

                if (cliente.getNumero() < 0) {
                    JOptionPane.showMessageDialog(null, "Número Negativo...",
                            "Isso é absurdo", JOptionPane.ERROR_MESSAGE);

                } else if (cliente.getNome().length() < 4) {
                    JOptionPane.showMessageDialog(null, "Nome muito pequeno",
                            "Pequeno de mais", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean isChanged = service.alterarClientes(cliente);
                    if(isChanged){
                        JOptionPane.showMessageDialog(null,"Alterado com Suceso","Sucesso",
                                JOptionPane.DEFAULT_OPTION);
                    } else JOptionPane.showMessageDialog(null,"Houve algum problema...","Contate o suporte",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        limparButton.addActionListener(e -> {
            cliente = new Cliente();
            txtId.setText("");
            txtNome.setText("");
            txtRg.setText("");
            txtCpf.setText("");
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
        abas.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                listar();
            }
        });
        botaoPesquisaNome.addActionListener(e -> {
            String nome = "%" + txtNomePesquisa.getText() + "%";
            listarPesquisaNome(nome);
        });
        root.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                mascaraCliente();
            }
        });
        root.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                mascaraCliente();
            }
        });
    }

    public void MainUI() {
        createTable();
    }

    private void mascaraCliente() {
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
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public JPanel getRoot() {
        return root;
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

    public void selecionaTela(byte opc) {
        abas.setSelectedIndex(opc);
    }
}