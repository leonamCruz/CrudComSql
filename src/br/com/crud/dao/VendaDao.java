package br.com.crud.dao;

import br.com.crud.jdbc.ConnectFactory;
import br.com.crud.model.Venda;

import java.sql.ResultSet;

public class VendaDao {
    private static final String CADASTRAR_VENDA = "insert into tb_vendas (cliente_id, data_venda, total_venda,observacoes) values (?,?,?,?)";
    private static final String RETORNA_ULTIMA_VENDA = "select max(id) id from tb_vendas";

    public void cadastrarVenda(Venda venda) {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(CADASTRAR_VENDA)) {
            stmt.setInt(1, venda.getClientes().getId());
            stmt.setString(2, venda.getData());
            stmt.setDouble(3, venda.getTotalDaVenda());
            stmt.setString(4, venda.getObs());
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int retornaUltimaVenda() {
        try (var conn = ConnectFactory.getConnection(); var stmt = conn.prepareStatement(RETORNA_ULTIMA_VENDA)) {
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                var venda = new Venda();
                venda.setId(rs.getInt("id"));
                int idVenda = venda.getId();
                return idVenda;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}