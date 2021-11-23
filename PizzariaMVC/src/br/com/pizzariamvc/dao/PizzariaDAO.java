/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pizzariamvc.dao;


import br.com.pizzariamvc.entities.Pizzaria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Edu_gdl
 */
public class PizzariaDAO {
    
    protected Connection conexao;
    protected PreparedStatement preparando;
    protected ResultSet resultSet;
    
    public void pizzaria(Pizzaria p) throws SQLException{
        String sql = "select * from Pizzaria where idPizzaria = 1";
        try {
            conexao = new Conexao().openConnection();
            preparando = conexao.prepareStatement(sql);
            resultSet = preparando.executeQuery();
            resultSet.next();
            p.setNome(resultSet.getString("nomePizzaria"));
            p.setEndereco(resultSet.getString("enderecoPizzaria"));
            p.setTelefone(resultSet.getString("telefonePizzaria"));
            p.setWhatsapp(resultSet.getString("whatsappPizzaria"));
            
        }catch(SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro no banco de dadso " + ex);
        }finally{
            Conexao.fecharConexao(conexao, preparando, resultSet);
        }
    }
    
    public void alterarNome(Pizzaria p) throws SQLException{
        String sql = "update Pizzaria set nomePizzaria = ? where idPizzaria = 1";
        try {
            conexao = Conexao.openConnection();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, p.getNome());
            preparando.executeUpdate();
            
        } catch (Exception e) {
        }finally{
            Conexao.fecharConexao(conexao, preparando);
        }
    }
    public void alterarEndereco(Pizzaria p) throws SQLException{
        String sql = "update Pizzaria set enderecoPizzaria = ? where idPizzaria = 1";
        try {
            conexao = Conexao.openConnection();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, p.getEndereco());
            preparando.executeUpdate();
            
        } catch (Exception e) {
        }finally{
            Conexao.fecharConexao(conexao, preparando);
        }
    }
    public void alterarTelefone(Pizzaria p) throws SQLException{
        String sql = "update Pizzaria set telefonePizzaria = ? where idPizzaria = 1";
        try {
            conexao = Conexao.openConnection();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, p.getTelefone());
            preparando.executeUpdate();
            
        } catch (Exception e) {
        }finally{
            Conexao.fecharConexao(conexao, preparando);
        }
    }
    public void alterarWhatsapp(Pizzaria p) throws SQLException{
        String sql = "update Pizzaria set whatsappPizzaria = ? where idPizzaria = 1";
        try {
            conexao = Conexao.openConnection();
            preparando = conexao.prepareStatement(sql);
            preparando.setString(1, p.getWhatsapp());
            preparando.executeUpdate();
            
        } catch (Exception e) {
        }finally{
            Conexao.fecharConexao(conexao, preparando);
        }
    }

}
