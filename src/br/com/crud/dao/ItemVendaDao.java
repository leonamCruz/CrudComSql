package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.ItemVenda;

public class ItemVendaDao {
    private static final String CADASTRA_ITEM = "insert into tb_itensvendas(venda_id,produto_id,qtd,subtotal) values(?,?,?,?)";
    public void cadastraItem(ItemVenda itemVenda){
        try(var conn = ConnectFactory.getConnection();var stmt = conn.prepareStatement(CADASTRA_ITEM)){
            stmt.setInt(1,itemVenda.getVendas().getId());
            stmt.setInt(2,itemVenda.getProdutos().getId());
            stmt.setInt(3,itemVenda.getQtd());
            stmt.setDouble(4,itemVenda.getSubTotal());
            stmt.execute();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}