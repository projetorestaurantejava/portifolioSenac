package br.com.pizzariamvc.util;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Paineis {
    
    public static JPanel painel(int x, int y, int largura, int altura, Color cor, JFrame frame){
        JPanel painel = new JPanel();
        frame.add(painel);
        painel.setLayout(null);
        painel.setBounds(x, y, largura, altura);
        painel.setBackground(cor);

        return painel;
    }
    public static JPanel painel(int x, int y, int largura, int altura, Color cor, JFrame frame, String a){
        JPanel painel = new JPanel();
        frame.add(painel);
        painel.setLayout(new BorderLayout());
        painel.setBounds(x, y, largura, altura);
        painel.setBackground(cor);

        return painel;
    }
    
    public static JPanel painel(int x, int y, int largura, int altura, Color cor){
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBounds(x, y, largura, altura);
        painel.setBackground(cor);
        
        return painel;
    }
    
    public static JPanel painel(int x, int y, int largura, int altura, JFrame frame){
        JPanel painel = new JPanel();
        frame.add(painel);
        painel.setLayout(null);
        painel.setBounds(x, y, largura, altura);
        
        return painel;
    }
    
    public static JPanel painel(int x, int y, int largura, int altura, Color cor, JPanel painelFundo){
        JPanel painel = new JPanel();
        painelFundo.add(painel);
        painel.setLayout(null);
        painel.setBounds(x, y, largura, altura);
        painel.setBackground(cor);
        return painel;
    }
    
    
    public static JPanel painel(int x, int y, int largura, int altura, JPanel painelFundo){
        JPanel painel = new JPanel();
        painelFundo.add(painel);
        painel.setLayout(null);
        painel.setBounds(x, y, largura, altura);
        return painel;
    }
    
}
