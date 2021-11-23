package br.com.pizzariamvc.view;

import br.com.pizzariamvc.dao.ClienteDAO;
import br.com.pizzariamvc.entities.Cliente;
import br.com.pizzariamvc.entities.Pizzaria;
import br.com.pizzariamvc.util.Botoes;
import br.com.pizzariamvc.util.Campos;
import br.com.pizzariamvc.util.Paineis;
import br.com.pizzariamvc.util.Rodape;
import br.com.pizzariamvc.util.Validacao;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Register{
    
    public Register(Pizzaria p) throws ParseException{
        JFrame a = new JFrame();
        a.setLayout(null);
        a.setSize(380, 700);
        a.setTitle("Registre-se");
        a.setResizable(false);
        a.setVisible(true);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        a.setLocationRelativeTo(null);
        
        JPanel campos = Paineis.painel(0, 0, 380, 532, a);
        campos.setBackground(Color.darkGray);
        
        JTextField nome = Campos.campos("Nome:", 65, 50, 100, campos);
        JTextField sobrenome = Campos.campos("Sobrenome:", 8, nome.getY() + 50, 200, campos);
        JFormattedTextField cpf = Campos.camposFormatados("CPF:", "###.###.###-##",85, sobrenome.getY() + 50, 80, campos);
        JTextField email = Campos.campos("E-mail:", 57, cpf.getY() + 50, 80, campos);
        JFormattedTextField telefone = Campos.camposFormatados("Telefone:", "(##) #####-####",35, email.getY() + 50, 120, campos);
        JTextField endereco = Campos.campos("Endereço:", 27, telefone.getY() + 50, 130, campos);
        JPasswordField senha = Campos.camposSenha("Senha:", 60, endereco.getY() + 50, 90, campos);
        
        JButton register = Botoes.botao("Registrar", 20, senha.getY() + 60, 130, 30, Color.GREEN, campos);
        JButton limpar = Botoes.botao("Limpar", (senha.getX() + senha.getWidth() - register.getWidth()), register.getY(), register.getWidth(), register.getHeight(), Color.RED, campos);
        JButton login = Botoes.botao("Retornar ao login", register.getX(), register.getY() + 40, 300, 30, Color.LIGHT_GRAY, campos);
     
        
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                a.setVisible(false);
                try {
                    new Login();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        });
        
        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Botoes.limpar(email, senha, nome, sobrenome, cpf, telefone, endereco);
            }
        });
        
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                if (Validacao.validaRegistro(nome, sobrenome, cpf, email, telefone, endereco, senha) == true) {
                    Cliente cliente = new Cliente();
                    cliente.setNome(nome.getText()+" "+sobrenome.getText());
                    cliente.setCpf(cpf.getText());
                    cliente.setEmail(email.getText());
                    cliente.setTelefone(telefone.getText());
                    cliente.setEndereco(endereco.getText());
                    cliente.setSenha(String.valueOf(senha.getText().hashCode()));
                    ClienteDAO cd  = new ClienteDAO();
                    try {
                        cd.cadastrarCliente(cliente, a);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Register: " + ex);
                    }
                }
            }           
        });

        JPanel rodape = Paineis.painel(campos.getX(), campos.getY() + campos.getHeight(), campos.getWidth(), 700 - campos.getHeight(), Color.BLACK, a);
        
        Rodape rd = new Rodape();
        rd.rodapeMethod(rodape,p);

    }

}
