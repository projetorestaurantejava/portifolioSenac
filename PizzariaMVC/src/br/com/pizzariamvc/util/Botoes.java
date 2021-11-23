package br.com.pizzariamvc.util;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Botoes {
    
    public static JButton botao(String titulo, int x, int y, int largura, int altura, Color fundo, JPanel painel){
        JButton botao = new JButton(titulo);
        painel.add(botao);
        botao.setBounds(x, y, largura, altura);
        botao.setBackground(fundo);
        botao.setForeground(Color.BLACK);

        return botao;
    }
    
    public static JButton botao(String titulo, int x, int y, int largura, int altura, Color fundo, ImageIcon imagem, JPanel painel){
        JButton botao = new JButton(titulo, imagem);
        painel.add(botao);
        botao.setBounds(x, y, largura, altura);
        botao.setBackground(fundo);
        botao.setForeground(Color.BLACK);

        return botao;
    }
    
    public static JButton botao(int x, int y, int largura, int altura, Color fundo, ImageIcon imagem, JPanel painel){
        JButton botao = new JButton(imagem);
        painel.add(botao);
        botao.setBounds(x, y, largura, altura);
        botao.setBackground(fundo);

        return botao;
    }
    
    public static void limpar(JTextField email,  JTextField senha){
        email.setText("");
        senha.setText("");
    }
    
    public static void limpar(JTextField email,  JTextField senha, JTextField nome, JTextField sobrenome, JTextField cpf, JTextField telefone, JTextField endereco){
        email.setText("");
        senha.setText("");
        nome.setText("");
        sobrenome.setText("");
        cpf.setText("");
        telefone.setText("");
        endereco.setText("");
    }
    
    public static void limpar(JPanel painelFalse, JPanel painelTrue){
        painelFalse.setVisible(false);
        painelTrue.setVisible(true);
    }
}
