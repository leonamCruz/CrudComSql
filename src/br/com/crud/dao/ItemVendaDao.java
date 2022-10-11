package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.ItemVenda;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ItemVendaDao {
    private Connection con;

    public void ItensVendaDao() {
        this.con = new ConnectFactory().getConnection();
    }

    public void cadastraItem(ItemVenda obj){
        try{
            ItensVendaDao();
            String sql= "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal) values(?,?,?,?)";
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1,obj.getVendas().getId());
            stmt.setInt(2,obj.getProdutos().getId());
            stmt.setInt(3,obj.getQtd());
            stmt.setDouble(4,obj.getSubTotal());

            stmt.execute();
            stmt.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}