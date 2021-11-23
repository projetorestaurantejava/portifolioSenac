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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Edu_gdl
 */
public class RegisterProdutos extends JFrame{
    
    JPanel painel;
    Pizza p;
    PizzaDAO pdao;
    
    
    public RegisterProdutos(){
        
        setSize(850,600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        
        painel = new JPanel();
        add(painel);
        painel.setLayout(null);
        painel.setBounds(0,0,850,600);
        painel.setBackground(Color.darkGray);
        
        JLabel titulo = criarLabel("Adicionar Produtos Novos", 45);
        titulo.setFont(new Font("Georgia",Font.BOLD,45));
        titulo.setBounds(155,titulo.getY(), titulo.getWidth(), titulo.getHeight());
        
        JLabel nomeProduto = criarLabel("Nome do produto: ", 160);
        JLabel descricao = criarLabel("Descrição: ", 260);
        JLabel valorProduto = criarLabel("Valor do produto: ", 360);
        JLabel tipoProduto = criarLabel("Tipo do produto: ", 460);
        
        JTextField textNomeProduto = criarText(200, 305, nomeProduto.getY()+5);
        JTextField textDescricao = criarText(600, 190, descricao.getY()+5);
        JTextField textValorProduto = criarText(70, 300, valorProduto.getY()+5);
        
        String[] produtos = {"< Insira o tipo do produto >", "Pizza", "Bebida"};
        JComboBox comboTipo = new JComboBox(produtos);
        painel.add(comboTipo);
        comboTipo.setBounds(285,468,200,30);
        
        JButton botao = new JButton("Adicionar novo produto");
        painel.add(botao);
        botao.setBounds(550,470,230,30);
        botao.setForeground(Color.BLACK);
        botao.setBackground(Color.white);
        
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((a(textNomeProduto, "Nome do produto"))== false){     
                }else if(comboTipo.getSelectedIndex() == 0){
                    JOptionPane.showMessageDialog(null,"Tipo do produto deve ser preenchido(a) corretamente");
                }else{
                    try {                                         
                    p = new Pizza();
                    pdao = new PizzaDAO();
        
                    p.setName(textNomeProduto.getText());
                    if(comboTipo.getSelectedIndex() == 1){
                        p.setDescricao(textDescricao.getText());    
                    }
                    try{
                        p.setPrice(Double.valueOf(textValorProduto.getText().replaceAll(",", "."))); 
                        p.setTipo(comboTipo.getSelectedItem().toString());
                        JOptionPane.showMessageDialog(null, p.getName()+" foi cadastrado com sucesso!");
                        try {
                        pdao.salvar(p);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Erro insperado no botão de confirmar " + ex);
                        }
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Preço deve ser um numero");
                    }
                    } catch (Exception m) {
                        System.err.println("Erro no banco de dados " + m);
                        
                    }
                    
                
                }
            }
        });
        
    }
    
    public JLabel criarLabel(String nome, int y){
        JLabel label = new JLabel(nome);
        painel.add(label);
        label.setBounds(20,y,800,40);
        label.setFont(new Font("Georgia",Font.BOLD,30));
        label.setForeground(Color.white);
        
        return label;
    }
    public JTextField criarText(int largura,int x,int y){
        JTextField texto = new JTextField();
        painel.add(texto);
        texto.setBounds(x,y,largura,30);
        texto.setFont(new Font("Georgia",Font.BOLD,20));
        texto.setForeground(Color.black);
        
        return texto;
    }
    public Boolean a(JTextField palavra, String b){
        if(palavra.getText().replaceAll(" ", "").equals("")){
            JOptionPane.showMessageDialog(null,b+" deve ser preenchido(a) corretamente");
            return false;
        }else{
            return true;
        }
    }
    
}

