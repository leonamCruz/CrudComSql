package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Venda;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendaDao {
    private Connection con;
    public void VendasDao(){
        this.con = new ConnectFactory().getConnection();
    }

    public void cadastrarVenda(Venda obj){
        try {
            VendasDao();
            String sql = "insert into tb_vendas (cliente_id, data_venda, total_venda,observacoes) values (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, obj.getClientes().getId());
            stmt.setString(2,obj.getData());
            stmt.setDouble(3,obj.getTotalDaVenda());
            stmt.setString(4,obj.getObs());

            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso",
                    "Sucesso total", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (SQLException erro) {
            System.out.println("erro" + erro);
        }
    }
    public int retornaUltimaVenda(){
        try {
        int idVenda = 0;
        String sql = "select max(id) id from tb_vendas";

        PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Venda venda = new Venda();
                venda.setId(rs.getInt("id"));
                idVenda = venda.getId();
                return idVenda;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }


}