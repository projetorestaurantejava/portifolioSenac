package br.com.pizzariamvc.dao;

import br.com.pizzariamvc.entities.Cliente;
import br.com.pizzariamvc.entities.Historico;
import br.com.pizzariamvc.view.Login;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ClienteDAO {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    protected ArrayList<Cliente> list = new ArrayList<>();
    protected ArrayList<Historico> listHistorico = new ArrayList<>();
    
    public void cadastrarCliente(Cliente cliente, JFrame a) throws SQLException{
        String sql = "insert into cliente (nomeCliente, telefone, email, cpf, endereco, senha) values (?, ?, ?, ?, ?, ?)";
        conexao = new Conexao().openConnection();
        
        try {
            preparando = conexao.prepareCall(sql);
            preparando.setString(1, cliente.getNome());
            preparando.setString(2, cliente.getTelefone());
            preparando.setString(3, cliente.getEmail());
            preparando.setString(4, cliente.getCpf());
            preparando.setString(5, cliente.getEndereco());
            preparando.setString(6, cliente.getSenha());
            preparando.execute();
            preparando.close();
            
            JOptionPane.showMessageDialog(null, cliente.getNome() + " sua conta foi criada com sucesso");
            a.setVisible(false);
            new Login();
        } catch(SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Uma conta com o CPF inserido já existe");
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    
    public ArrayList<Cliente> getCliente() throws SQLException{
        String sql = "select * from cliente";
        conexao = new Conexao().openConnection();
        
        try {
            preparando = conexao.prepareStatement(sql);
            resultSet = preparando.executeQuery();
            
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setId(resultSet.getInt("idCliente"));
                cliente.setNome(resultSet.getString("nomeCliente"));
                cliente.setTelefone(resultSet.getString("telefone"));
                cliente.setEmail(resultSet.getString("email"));
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setEndereco(resultSet.getString("endereco"));
                cliente.setSenha(resultSet.getString("senha"));
                list.add(cliente);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Obter cliente: " + e);
        }
        return list;
    }
    
    public ArrayList<Historico> getHistorico(Cliente c) throws SQLException{
        String sql = "select senha, idPedido, total from cliente c \n" +
        "inner join pedido p on c.idCliente = p.idClientePedido\n" +
        "inner join pagamento pg on p.idPedido = pg.idPedidoPagamento where senha = " +  c.getSenha() + ";";
        conexao = new Conexao().openConnection();
        
        try {
            preparando = conexao.prepareStatement(sql);
            resultSet = preparando.executeQuery();
            
            
            while (resultSet.next()) {
                Historico historico = new Historico();
                historico.setSenha(resultSet.getString("senha"));
                historico.setIdPedido(resultSet.getInt("idPedido"));
                historico.setTotal(resultSet.getDouble("total"));
                listHistorico.add(historico);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Obter historico: " + e);
        }
        return listHistorico;
    }
}
