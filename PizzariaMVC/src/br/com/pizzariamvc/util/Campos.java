package br.com.pizzariamvc.util;

import java.awt.Color;
import java.awt.Font;
import java.text.ParseException;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class Campos {
    
    public static JTextField campos(String nome, int x, int y, int larguraLabel, JPanel painel){ 
        
        JLabel label = new JLabel(nome);
        painel.add(label);
        label.setBounds(x, y, larguraLabel, 30);
        label.setFont(new Font("Georgia", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        
        JTextField text = new JTextField();
        painel.add(text);
        text.setBounds(140, y, 180, 30);
        
        return text;
    }
    
     public static JTextField campos(String nome, int x, int y, int larguraLabel, int altura, Color cor, Font font, JPanel painel){ 
        
        JLabel label = new JLabel(nome);
        painel.add(label);
        label.setBounds(x, y, larguraLabel, 30);
        label.setFont(font);
        label.setForeground(cor);
        
        JTextField text = new JTextField();
        painel.add(text);
        text.setBounds(label.getX(), label.getY() + label.getHeight() + 20, 250, 30);
        
        return text;
    }
    
    public static JPasswordField camposSenha(String nome, int x, int y, int larguraLabel, JPanel painel){ 
        
        JLabel label = new JLabel(nome);
        painel.add(label);
        label.setBounds(x, y, larguraLabel, 30);
        label.setFont(new Font("Georgia", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        
        JPasswordField text = new JPasswordField();
        painel.add(text);
        text.setBounds(140, y, 180, 30);
        
        return text;
    }
    
    public static JFormattedTextField camposFormatados(String nome, String formato, int x, int y, int larguraLabel, JPanel painel) throws ParseException{ 
        
        JLabel label = new JLabel(nome);
        painel.add(label);
        label.setBounds(x, y, larguraLabel, 30);
        label.setFont(new Font("Georgia", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        
        MaskFormatter mask = new MaskFormatter(formato);
        JFormattedTextField text = new JFormattedTextField(mask);
        painel.add(text);
        text.setBounds(140, y, 180, 30);
        
        return text;
    }
    public static JFormattedTextField camposFormatados(String nome, int x, int y, int larguraLabel, int altura, Color cor, Font font, JPanel painel, String formato) throws ParseException{ 
        
        JLabel label = new JLabel(nome);
        painel.add(label);
        label.setBounds(x, y, larguraLabel, 30);
        label.setFont(font);
        label.setForeground(cor);
        
        MaskFormatter mask = new MaskFormatter(formato);        
        JFormattedTextField text = new JFormattedTextField(mask);
        painel.add(text);
        text.setBounds(label.getX(), label.getY() + label.getHeight() + 20, 250, 30);
        
        return text;
    }
    
    
}

