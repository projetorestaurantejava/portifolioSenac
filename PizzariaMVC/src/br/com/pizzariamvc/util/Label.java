package br.com.pizzariamvc.util;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Label {
    
    public static JLabel texto(String titulo, int x, int y, int largura, int altura, Font font, JPanel painel){
        JLabel label = new JLabel(titulo);
        painel.add(label);
        label.setBounds(x, y, largura, altura);
        label.setFont(font);
        label.setForeground(Color.black);        
    
        return label;
    }
    public static JLabel texto(JLabel label,String titulo, int x, int y, int largura, int altura, Font font, JPanel painel){
        label.setText(titulo);
        painel.add(label);
        label.setBounds(x, y, largura, altura);
        label.setFont(font);
        label.setForeground(Color.black);        
    
        return label;
    }
    
    public static JLabel texto(String titulo, int x, int y, int largura, int altura, Color cor, Font font, JPanel painel){
        JLabel label = new JLabel(titulo);
        painel.add(label);
        label.setBounds(x, y, largura, altura);
        label.setFont(font);
        label.setForeground(cor);

        return label;
    }
    
    public static JLabel texto(String titulo, Color cor){
        JLabel label = new JLabel(titulo);
        label.setForeground(cor);
    
        return label;
    }
    
    public static JLabel texto(String titulo, Font font, Color cor, JPanel painel){
        JLabel label = new JLabel(titulo);
        painel.add(label);
        label.setFont(font);
        label.setForeground(cor);
        
        return label;
    }
    
    
    public static JLabel texto(String titulo, int x, int y, int largura, int altura, Font font, ImageIcon imagem, JPanel painel){
        JLabel label = new JLabel(titulo);
        painel.add(label);
        JLabel ft = new JLabel(imagem);
        painel.add(ft);
        ft.setBounds(x,y,30,30);
        label.setBounds(x+40, y-10, largura, altura);
        label.setFont(font);
        label.setForeground(Color.black);        
    
        return label;
    }
    
    public static JLabel texto(int x, int y, int largura, int altura, ImageIcon imagem, JPanel painel){
        JLabel label = new JLabel(imagem, JLabel.CENTER);
        painel.add(label);
        label.setBounds(x, y, largura, altura);
            
        return label;
    }
}
