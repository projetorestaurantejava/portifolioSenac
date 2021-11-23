package br.com.hackathon.util;

import java.awt.Color;
import java.awt.Font;
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
    
    public static JButton botao(String titulo, int x, int y, int largura, int altura, Color fundo, Font font, JPanel painel){
        JButton botao = new JButton(titulo);
        botao.setFont(font);
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
    
    public static JButton botao(String titulo, int x, int y, int largura, int altura, Color fundo, ImageIcon imagem, Font font, JPanel painel){
        JButton botao = new JButton(titulo, imagem);
        botao.setFont(font);
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
}
