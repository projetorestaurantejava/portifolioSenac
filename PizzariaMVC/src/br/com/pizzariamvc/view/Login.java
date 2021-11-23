package br.com.pizzariamvc.view;

import br.com.pizzariamvc.dao.ClienteDAO;
import br.com.pizzariamvc.dao.PizzariaDAO;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{

    public Login() throws SQLException{
        
        setLayout(null);
        setSize(380, 500);
        setTitle("Login");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        Pizzaria p = new Pizzaria();
        PizzariaDAO pdao = new PizzariaDAO();
        pdao.pizzaria(p);
        
        JPanel campos = Paineis.painel(0, 0, 380, 332, this);
        add(campos);
        campos.setBackground(Color.darkGray);
       
        JTextField email = Campos.campos("E-mail:", 35, 95, 150, campos);
        email.setBounds(email.getX()-20,email.getY()-5,email.getWidth(),email.getHeight());
        JPasswordField senha = Campos.camposSenha("Senha:", 39, email.getLocation().y + 60, 130, campos);
        senha.setBounds(senha.getX()-20,senha.getY()-5,senha.getWidth(),senha.getHeight());
        
        JButton entrar = Botoes.botao("Entrar", senha.getLocation().x, senha.getLocation().y + 60, 80, 30, Color.GREEN, campos);
        JButton limpar = Botoes.botao("Limpar", (senha.getLocation().x + senha.getWidth() - 80), entrar.getLocation().y, 80, 30, Color.RED, campos);
        JButton register = Botoes.botao("Registre-se", senha.getLocation().x, entrar.getY() + 40, senha.getWidth(), 30, Color.LIGHT_GRAY, campos);
        
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                try {
                    new Register(p);
                } catch (ParseException ex) {
                    System.err.println("Erro: " + ex);
                }
            }
        });
        
        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Botoes.limpar(email, senha);
            }
        });
        
        entrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ClienteDAO cd = new ClienteDAO();
                try {
                    if(email.getText().equals("admin")&& senha.getText().equals("senac")){
                        JOptionPane.showMessageDialog(null, "Bem-vindo admin");
                        setVisible(false);
                        new CentralAdmin();
                    }else if (Validacao.login(cd, email, senha) == true) {
                        Cliente c = Validacao.login(cd, senha, new Cliente());
                        JOptionPane.showMessageDialog(null, "Bem-vindo "+c.getNome());
                        setVisible(false);
                        new Principal(c,p);
                    } else {
                        JOptionPane.showMessageDialog(null, "E-mail e/ou senha incorretos");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Login : " + ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        JPanel rodape = Paineis.painel(campos.getX(), campos.getY() + campos.getHeight(), campos.getWidth(), 500 - campos.getHeight(), Color.BLACK, this);
        add(rodape);
        Rodape rd = new Rodape();
        rd.rodapeMethod(rodape,p);
        setVisible(true);
    }
}
