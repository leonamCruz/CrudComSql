package br.com.crud.view;

import br.com.crud.services.impl.LoginServiceImpl;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Login {
    private JPanel root;
    private JPasswordField senha;
    private JTextField login;
    private JButton logarButton;
    private LoginServiceImpl loginService = new LoginServiceImpl();

    public Login() {
        logarButton.addActionListener(e -> logar());

        senha.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    logar();
                }
            }
        });
    }

    private boolean logar(){
        boolean isLogged = loginService.logar(login.getText(),senha.getText());
        if(isLogged){
            JOptionPane.showMessageDialog(null,"Sucesso");
            RodaTela.userLogado = loginService.getNomeDoLogado();
            RodaTela.createGuiMenuPrincipal();
            return true;
        }else {
            JOptionPane.showMessageDialog(null,"Falhou");
            return false;
        }
    }
    public JPanel getRoot() {
        return root;
    }
}