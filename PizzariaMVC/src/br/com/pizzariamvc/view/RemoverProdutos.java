/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzariamvc.view;

import br.com.pizzariamvc.dao.PizzaDAO;
import br.com.pizzariamvc.entities.Pizza;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Edu_gdl
 */
public class RemoverProdutos extends JFrame{
    JPanel painel;
    
    public RemoverProdutos() throws SQLException{
        Pizza p = new Pizza();
        PizzaDAO pdao = new PizzaDAO();
        
        ImageIcon foto_lupa = new ImageIcon(getClass().getResource("lupa.png"));
        setSize(755,600);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        painel = new JPanel();
        add(painel);
        painel.setBounds(0,0,755,600);
        painel.setBackground(Color.DARK_GRAY);
        painel.setLayout(null);

        JTextField texto = new JTextField();
        painel.add(texto);
        texto.setBounds(295,50,350,30);
        texto.setFont(new Font("Georgia", Font.BOLD, 20));

        JLabel label = criarLabel2(45, "Digite o produto: ");
        label.setVisible(true);
        label.setBounds(20,label.getY(),label.getWidth(), label.getHeight());
        label.setFont(new Font("Georgia", Font.BOLD, 30));
        
        JButton botao = new JButton(foto_lupa);
        painel.add(botao);
        botao.setBounds(texto.getX()+370,45,40,40);

        JLabel id = criarLabel(200); 
        JLabel nome = criarLabel(250);
        JLabel descricao = criarLabel(300);
        descricao.setText("Descrição atual: ");
        JLabel descricao2 = criarLabel(300);
        descricao2.setFont(new Font("Georgia",Font.BOLD,12));
        descricao2.setBounds(200,descricao2.getY()+2,440,descricao2.getHeight());
        JLabel preco = criarLabel(350);
        JLabel tipo = criarLabel(400);
        
        JButton botaoRemove = new JButton("Remover produto");
        painel.add(botaoRemove);
        botaoRemove.setBounds(425/2,500,330,40);
        botaoRemove.setBackground(Color.red);
        botaoRemove.setForeground(Color.BLACK);
        botaoRemove.setFont(new Font("Georgia", Font.BOLD, 30));
        botaoRemove.setVisible(false);
        
        botao.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                try {
                    pdao.pesquisar(p, texto.getText());
                    id.setVisible(true);
                    id.setText("Id: "+p.getIdPizza());
                    nome.setVisible(true);
                    nome.setText("Nome atual: "+p.getName());
                    descricao.setVisible(true);
                    descricao2.setVisible(true);
                    descricao2.setText(p.getDescricao());
                    preco.setVisible(true);
                    preco.setText("Preço atual: "+p.getPrice());
                    tipo.setVisible(true);
                    tipo.setText("Tipo atual: "+p.getTipo());
                    botaoRemove.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro inesperado remover produto");
                }
            }
        });
        
        botaoRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    pdao.remover(p);
                    JOptionPane.showMessageDialog(null, p.getName()+" removido(a) com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao remover produto");
                }
            }
        });
    }    
    public JLabel criarLabel(int y){
        JLabel label = new JLabel();
        painel.add(label);
        label.setBounds(20,y,700,40); 
        label.setFont(new Font("Georgia", Font.BOLD, 20));
        label.setForeground(Color.white);
        label.setVisible(false);
        
        return label;
}
    public JLabel criarLabel2(int y, String texto){
        JLabel label = new JLabel(texto);
        painel.add(label);
        label.setBounds(635,y,700,40); 
        label.setFont(new Font("Georgia", Font.BOLD, 20));
        label.setForeground(Color.white);
        label.setVisible(false);
        
        return label;
}
    public JTextField criarTextField(int x, int y, int largura){
        JTextField texto =  new JTextField();
        painel.add(texto);
        texto.setBounds(x,y,largura,30);
        texto.setFont(new Font("Georgia", Font.BOLD, 20));
        texto.setForeground(Color.black);
        texto.setVisible(false);
        
        return texto;
    }
    public JButton criarBotao(int x, int y, int largura, String nome){
        JButton botao =  new JButton(nome);
        painel.add(botao);
        botao.setBounds(x,y,largura,30);
        botao.setVisible(false);
        botao.setForeground(Color.black);
        botao.setBackground(Color.lightGray);
        
        return botao;
    }
}
