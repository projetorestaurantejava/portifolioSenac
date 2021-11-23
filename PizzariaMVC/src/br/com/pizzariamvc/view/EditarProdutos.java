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
import java.text.DecimalFormat;
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
public class EditarProdutos extends JFrame{
    JPanel painel;
    
    public EditarProdutos() throws SQLException{
        Pizza p = new Pizza();
        PizzaDAO pdao = new PizzaDAO();
        
        ImageIcon foto_lupa = new ImageIcon(getClass().getResource("lupa.png"));
        setSize(1366,500);
        setVisible(true);
        setResizable(false);
        setLayout(null);
        setLocationRelativeTo(null);

        painel = new JPanel();
        add(painel);
        painel.setBounds(0,0,1366,500);
        painel.setBackground(Color.DARK_GRAY);
        painel.setLayout(null);

        JTextField texto = new JTextField();
        painel.add(texto);
        texto.setBounds(325,50,350,30);
        texto.setFont(new Font("Georgia", Font.BOLD, 20));

        JLabel label = criarLabel2(45, "Digite o produto: ");
        label.setVisible(true);
        label.setBounds(50,label.getY(),label.getWidth(), label.getHeight());
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
        descricao2.setBounds(195,descricao2.getY()+2,400,descricao2.getHeight());
        JLabel preco = criarLabel(350);
        JLabel tipo = criarLabel(400);

        JLabel NovoNome = criarLabel2(250, "Novo Nome: ");
        JLabel NovoDescricao = criarLabel2(300, "Nova Descrição: ");
        JLabel NovoPreco = criarLabel2(350, "Novo Preço: ");

        JTextField textNome = criarTextField(NovoNome.getX()+135, 253, 200);
        JTextField textDescricao = criarTextField(NovoDescricao.getX()+175, 303, 400);
        JTextField textPreco = criarTextField(NovoPreco.getX()+135, 353,70);
        
        JButton botaoEditar = criarBotao(1000,400,150,"Editar produto");
        botao.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                try {
                    DecimalFormat dc = new DecimalFormat("#,###.00");
                    pdao.pesquisar(p, texto.getText());
                    id.setVisible(true);
                    id.setText("Id: "+p.getIdPizza());
                    nome.setVisible(true);
                    nome.setText("Nome atual: "+p.getName());
                    descricao.setVisible(true);
                    descricao2.setVisible(true);
                    descricao2.setText(p.getDescricao());
                    preco.setVisible(true);
                    preco.setText("Preço atual: R$ "+dc.format(p.getPrice()));
                    tipo.setVisible(true);
                    tipo.setText("Tipo atual: "+p.getTipo());
                    NovoNome.setVisible(true);
                    NovoDescricao.setVisible(true);
                    NovoPreco.setVisible(true);
                    textNome.setVisible(true);
                    textNome.setText(p.getName());
                    textDescricao.setVisible(true);
                    textDescricao.setText(p.getDescricao());
                    textPreco.setVisible(true);
                    textPreco.setText(p.getPrice().toString());
                    botaoEditar.setVisible(true);
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        });
        botaoEditar.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                if(textNome.getText().equals("") || textPreco.getText().equals("") || textDescricao.getText().equals("")){
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos");
                }else{
                    
                p.setName(textNome.getText());
                p.setPrice(Double.valueOf(textPreco.getText().replaceAll(",", ".")));
                p.setDescricao(textDescricao.getText());
                
                try {
                    pdao.alterar(p);
                    JOptionPane.showMessageDialog(null, "Nome alterado com sucessso!");
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
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
        label.setBounds(600,y,700,40); 
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
