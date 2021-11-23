package br.com.hackathon.util;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class OptionPane {
 
    public void optionPane(String texto){
        JOptionPane.showMessageDialog(null, texto);
    }
    
    public void optionPane(ImageIcon imagem){
        JOptionPane.showMessageDialog(null, imagem);
    }
    
    public void optionPane(String texto, String titulo, ImageIcon imagem){
        JOptionPane.showInternalMessageDialog(null, texto, titulo, JOptionPane.INFORMATION_MESSAGE, imagem);
    }
}
