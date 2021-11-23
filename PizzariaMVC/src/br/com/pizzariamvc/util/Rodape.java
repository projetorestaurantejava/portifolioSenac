package br.com.pizzariamvc.util;

import br.com.pizzariamvc.entities.Pizzaria;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Rodape {
    
    ImageIcon whatsapp = new ImageIcon(getClass().getResource("whatsapp.png"));
    ImageIcon localizacao = new ImageIcon(getClass().getResource("localizacao.png"));
    
    public void rodapeMethod(JPanel painel, Pizzaria p){

       JLabel enderecoImagem = new JLabel(localizacao);
        enderecoImagem.setBounds(10, 20, 30, 30);
        JLabel telefoneImagem = new JLabel(whatsapp);
        telefoneImagem.setBounds(enderecoImagem.getX(), enderecoImagem.getY() + 40, enderecoImagem.getWidth(), enderecoImagem.getHeight());
        

        JLabel telefone = Label.texto(p.getTelefone(), telefoneImagem.getX() + 40, telefoneImagem.getY(), 200, 30, new Font("Georgia", Font.BOLD, 20), painel);     
        telefone.setForeground(Color.WHITE);
        JLabel endereco = Label.texto(p.getEndereco(), telefone.getX(), enderecoImagem.getY(), 300, 30, new Font("Georgia", Font.BOLD, 15), painel);
        endereco.setForeground(Color.WHITE);
        
        painel.add(telefoneImagem);
        painel.add(enderecoImagem);
        
    }
}
