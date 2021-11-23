package br.com.pizzariamvc.dao;

import br.com.pizzariamvc.entities.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class PagamentoDAO {

    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void setPagamento(Pagamento pagamento) throws SQLException{
        String sql = "insert into Pagamento (idPedidoPagamento, formaPagamento, total, nomePedido, enderecoPedido, telefonePedido, emailPedido) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            conexao = new Conexao().openConnection();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, pagamento.getIdPedidoPagamento());
            preparando.setString(2, pagamento.getFormaPagamento());
            preparando.setDouble(3, pagamento.getTotal());
            preparando.setString(4, pagamento.getNome());
            preparando.setString(5, pagamento.getLocalizacao());
            preparando.setString(6, pagamento.getTelefone());
            preparando.setString(7, pagamento.getEmail());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            pagamento.setIdPagamento(resultSet.getInt(1));
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao no banco de dados " + ex);
        }finally{
            Conexao.fecharConexao(conexao, preparando, resultSet);
        }
        
    }
}
