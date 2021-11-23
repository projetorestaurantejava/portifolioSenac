/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pizzariamvc.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Edu_gdl
 */
public class CentralAdmin extends JFrame{
    JPanel painel;
    
    
    public CentralAdmin(){
        
        setSize(700,200);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        painel = new JPanel();
        add(painel);
        painel.setLayout(null);
        painel.setBounds(0,0,850,600);
        painel.setBackground(Color.darkGray);
        
        JButton botao;
        botao = criarBotao(50, "ADICIONAR", Color.green);
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterProdutos();
            }
        });
        botao = criarBotao(260, "EDITAR", Color.blue);
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new EditarProdutos();
                } catch (SQLException ex) {
                    System.err.println(ex);
                }
            }
        });
        botao = criarBotao(470, "REMOVER", Color.red);
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    new RemoverProdutos();
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
        });
    }
    public JButton criarBotao(int x, String texto, Color a){
        JButton botao = new JButton(texto);
        painel.add(botao);
        botao.setBackground(a);
        botao.setBounds(x,60,180,50);
        botao.setFont(new Font("Georgia", Font.BOLD, 22));
        botao.setForeground(Color.black);
        return botao;
    }
}
