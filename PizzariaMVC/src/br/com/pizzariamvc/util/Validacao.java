package br.com.pizzariamvc.util;

import br.com.pizzariamvc.dao.ClienteDAO;
import br.com.pizzariamvc.entities.Cliente;
import java.sql.SQLException;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Validacao {
    
    public static Boolean validaRegistro(JTextField nome, JTextField sobrenome, JTextField cpf, JTextField email, JFormattedTextField telefone, JTextField endereco, JPasswordField senha){
        
        if(nome.getText().replaceAll(" ","").equals("")|| nome.getText().replaceAll(" ", "").length()<6){
            JOptionPane.showMessageDialog(null, "Nome deve ser digitado corretamente");
            return false;
        }else if (sobrenome.getText().replaceAll(" ","").equals("")|| sobrenome.getText().replaceAll(" ", "").length()<6) {
            JOptionPane.showMessageDialog(null, "Sobrenome deve ser digitado corretamente");
            return false;
        }else if(cpf.getText().replaceAll(" ", "").length() != 14){
            JOptionPane.showMessageDialog(null, "CPF inválido");
            System.out.println("CPF");
            return false;
        }else if (!(email.getText().contains("@") && email.getText().contains("."))) {
            JOptionPane.showMessageDialog(null, "E-mail deve ser digitado corretamente");
            return false;
        }else if(email.getText().replaceAll(" ","").equals("")|| email.getText().replaceAll(" ", "").length()<6){
            JOptionPane.showMessageDialog(null, "E-mail deve ser digitado corretamente");
            return false;
        }else if (telefone.getText().replaceAll(" ", "").length() != 14) {
            JOptionPane.showMessageDialog(null, "Telefone inválido");
            return false;
        }else if (endereco.getText().replaceAll(" ","").equals("")|| endereco.getText().replaceAll(" ", "").length()<6) {
            JOptionPane.showMessageDialog(null, "Endereço deve ser digitado corretamente");
            return false;
        }else if (senha.getText().replaceAll(" ","").equals("")|| senha.getText().replaceAll(" ", "").length()<6) {
            JOptionPane.showMessageDialog(null, "Senha deve conter no mínimo 6 digitos");
            return false;
        }else{
            return true;
        } 
    }
    
    public static Boolean labelExist(JLabel label){
        if(label.isVisible()){
            return false;
        }
        return true;
    }
    
    public static Boolean login(ClienteDAO clienteDAO, JTextField email, JPasswordField senha) throws SQLException{
        int temp = clienteDAO.getCliente().size();
        for (int i = 0; i < temp; i++) {
            if (String.valueOf(senha.getText().hashCode()).equals(clienteDAO.getCliente().get(i).getSenha())) {
                if (email.getText().equals(clienteDAO.getCliente().get(i).getEmail())) {
                    return true;
                }
            }
            System.out.println(i);
        }
        System.out.println("login reprovado");
        return false;
    }
    
    public static Cliente login(ClienteDAO clienteDAO, JPasswordField senha, Cliente cliente) throws SQLException{
        
        int temp = clienteDAO.getCliente().size();
        for (int i = 0; i < temp; i++) {
            if (String.valueOf(senha.getText().hashCode()).equals(clienteDAO.getCliente().get(i).getSenha())) {
                cliente.setCpf(clienteDAO.getCliente().get(i).getCpf());
                cliente.setNome(clienteDAO.getCliente().get(i).getNome());;
                cliente.setEmail(clienteDAO.getCliente().get(i).getEmail());
                cliente.setSenha(clienteDAO.getCliente().get(i).getSenha());
                cliente.setTelefone(clienteDAO.getCliente().get(i).getTelefone());
                cliente.setEndereco(clienteDAO.getCliente().get(i).getEndereco());
                cliente.setId(clienteDAO.getCliente().get(i).getId());
            }
        }
        return cliente;
    }
}
