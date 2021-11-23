package br.com.pizzariamvc.dao;
import br.com.pizzariamvc.entities.SaborPizza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class SaborPizzaDAO {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void setPedido(SaborPizza sb) throws SQLException{
        String sql = "insert into SaborPizza (idSabor, quantidade, idPedidoPizza) values (?, ?, ?)";
        try {
            conexao = new Conexao().openConnection();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setInt(1, sb.getIdSabor());
            preparando.setInt(2, sb.getQuantidade());
            preparando.setInt(3, sb.getIdPedidoPizza());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            sb.setIdSaborPizza(resultSet.getInt(1));
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro mo banco de dados SaborPizza " + ex);
        }finally{
            Conexao.fecharConexao(conexao, preparando, resultSet);
        }
        
    }
}
