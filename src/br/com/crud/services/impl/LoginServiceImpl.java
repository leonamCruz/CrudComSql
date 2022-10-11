package br.com.crud.services.impl;

import br.com.crud.dao.LoginDao;
import br.com.crud.services.LoginService;

public class LoginServiceImpl implements LoginService {
    private LoginDao loginDao;

    public LoginServiceImpl() {
        this.loginDao =  new LoginDao();
    }
    @Override
    public boolean logar(String email, String senha) {
        return this.loginDao.logar(email,senha);
    }

    @Override
    public String getNomeDoLogado() {
        return this.loginDao.getNomeDoLogado();
    }
}
