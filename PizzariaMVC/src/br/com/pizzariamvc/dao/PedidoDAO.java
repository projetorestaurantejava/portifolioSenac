package br.com.pizzariamvc.dao;

import br.com.pizzariamvc.entities.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class PedidoDAO {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void setPedido(Pedido pedido) throws SQLException{
        String sql = "insert into Pedido (idClientePedido) values (?)";
        try {
            conexao = new Conexao().openConnection();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, pedido.getIdClientePedido());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            pedido.setIdPedido(resultSet.getInt(1));
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao no banco de dados " + ex);
        }finally{
            Conexao.fecharConexao(conexao, preparando, resultSet);
        }
        
    }
}
