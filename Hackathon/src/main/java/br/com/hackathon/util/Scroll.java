package br.com.hackathon.util;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Scroll {
    
    public JScrollPane scroll(JPanel painel, JFrame frame, int x, int y, int largura, int altura){
        
        JScrollPane scrollObj =  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollObj);
        scrollObj.setBounds(x, y, largura, altura);
        scrollObj.setViewportView(painel);
        painel.setPreferredSize(new Dimension(largura, altura));
        return scrollObj;
    }
}
