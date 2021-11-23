package br.com.pizzariamvc.dao;

import br.com.pizzariamvc.entities.Pizza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Statement;

public class PizzaDAO {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    protected ArrayList<Pizza> list = new ArrayList<>();
    
    public ArrayList<Pizza> getPizza() throws SQLException{
        String sql = "select * from pizza";
        conexao = Conexao.openConnection();
        try {
            preparando = conexao.prepareStatement(sql);
            resultSet = preparando.executeQuery();
            
            while (resultSet.next()) {
                Pizza pizza = new Pizza();
                pizza.setIdPizza(resultSet.getInt("idPizza"));
                pizza.setName(resultSet.getString("saborPizza"));
                pizza.setDescricao(resultSet.getString("descricao"));
                pizza.setPrice(resultSet.getDouble("valorPizza"));
                pizza.setTipo(resultSet.getString("tipo"));
                list.add(pizza);
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "PizzaDAO: " + e);
        }
        return list;
    }
    public void salvar(Pizza p) throws SQLException{
        String sql = "insert into Pizza(saborPizza, descricao, valorPizza, tipo) values (?,?,?,?)";
        try {
            conexao = new Conexao().openConnection();
            preparando = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparando.setString(1, p.getName());
            preparando.setString(2, p.getDescricao());
            preparando.setDouble(3, p.getPrice());
            preparando.setString(4, p.getTipo());
            preparando.executeUpdate();
            resultSet = preparando.getGeneratedKeys();
            resultSet.next();
            p.setIdPizza(resultSet.getInt(1));
        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao no banco de dados " + ex);
        }finally{
            Conexao.fecharConexao(conexao, preparando, resultSet);
        }
        
    }

    public void alterar(Pizza p) throws SQLException{
        String sql = "update Pizza set saborPizza = ?, descricao = ?, valorPizza = ? where idPizza = ?";
        try {
            conexao = new Conexao().openConnection();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, p.getName());
            preparando.setString(2, p.getDescricao());
            preparando.setDouble(3, p.getPrice());
            preparando.setInt(4, p.getIdPizza());
            preparando.executeUpdate();
            
        } catch (Exception e) {
        }finally{
            Conexao.fecharConexao(conexao, preparando);
        }
    }
    
    public void pesquisar(Pizza p, String a) throws SQLException{
        String sql = "select * from Pizza where saborPizza like '"+a+"%'";
        try {
            conexao = new Conexao().openConnection();
            preparando = conexao.prepareStatement(sql);
            resultSet = preparando.executeQuery();
            resultSet.next();
            p.setIdPizza(resultSet.getInt("idPizza"));
            p.setName(resultSet.getString("saborPizza"));
            p.setPrice(resultSet.getDouble("valorPizza"));
            p.setDescricao(resultSet.getString("descricao"));
            p.setTipo(resultSet.getString("tipo"));
            
            
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "O produto pesquisado não existe");
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "O produto pesquisado não existe");
        }finally{
            Conexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    public void remover(Pizza p) throws SQLException{
        String sql = "delete from Pizza where idPizza = ?";
        try {
            conexao = new Conexao().openConnection();
            preparando = conexao.prepareStatement(sql);
            preparando.setInt(1, p.getIdPizza());
            preparando.executeUpdate();
               
        } catch (Exception e) {
        }finally{
            Conexao.fecharConexao(conexao, preparando);
        }
    }
}
