package br.com.pizzariamvc.view;

import br.com.pizzariamvc.entities.Pagamento;
import br.com.pizzariamvc.entities.Pedido;
import br.com.pizzariamvc.entities.Pizzaria;
import br.com.pizzariamvc.util.Label;
import br.com.pizzariamvc.util.Paineis;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class CupomFiscal extends JFrame{
    public CupomFiscal(Pedido p, ArrayList<JLabel> saborPizzaSelect, ArrayList<JLabel> preco, ArrayList<Integer> qtd,Pagamento pagamento, Pizzaria pizzaria){
        setSize(400, 600);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        DecimalFormat df = new DecimalFormat("#,###.00");
       
        JPanel painel = Paineis.painel(0, 0, 400, 600, Color.ORANGE, this);
       
        JLabel titulo = Label.texto(pizzaria.getNome(), 150, 20, 100, 20,  Color.BLACK, new Font("Georgia",Font.BOLD,20), painel);
        JLabel pedido = Label.texto("Pedido Nº: " + p.getIdPedido(), 15, titulo.getY() + titulo.getHeight() + 30, 200, 20,  titulo.getForeground(), titulo.getFont(), painel);
        JLabel item;
        JLabel qtdLabel;
        JLabel precoLabel;
        
        JLabel barra = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        painel.add(barra);
        barra.setBounds(0,pedido.getY()+20,400,30);
        
        int temp = 0;
        int y = pedido.getY() + pedido.getHeight() + 50;
        
        for (JLabel jLabel : saborPizzaSelect) {
            item = Label.texto(jLabel.getText() + ";", pedido.getX(), y, 400, 30, titulo.getForeground(), pedido.getFont(), painel);
            qtdLabel = Label.texto("quantidade: " + qtd.get(temp), item.getX(), item.getY() + item.getHeight() + 10, 150, 30, titulo.getForeground(), pedido.getFont(), painel);
            precoLabel = Label.texto("Preço: " + preco.get(temp).getText(), qtdLabel.getX() + qtdLabel.getWidth() + 10, qtdLabel.getY(), 200, 30, titulo.getForeground(), pedido.getFont(), painel);
            barra = new JLabel("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            painel.add(barra);
            barra.setBounds(0,precoLabel.getY()+50,400,30);
            temp ++;
            y = barra.getY() + barra.getHeight() + 20;
        }
       
        JLabel total = Label.texto("Total : R$"  + df.format(pagamento.getTotal()), pedido.getX(), y + 10, 400, 30, titulo.getForeground() ,pedido.getFont(), painel);
        JLabel metodoPagamento = Label.texto("Opção de entrega : " + pagamento.getFormaPagamento(), pedido.getX(), total.getY() + total.getHeight() + 10, 400, 30, titulo.getForeground(), pedido.getFont(), painel);
        JLabel nomeLabel = Label.texto("Nome: " + pagamento.getNome(), pedido.getX(), metodoPagamento.getY() + metodoPagamento.getHeight() + 10, 360, 30, titulo.getForeground(), pedido.getFont(), painel);
        JLabel enderecoLabel = Label.texto("Endereço: " + pagamento.getLocalizacao(), pedido.getX(), nomeLabel.getY() + nomeLabel.getHeight() + 10, 360, 30, titulo.getForeground(), pedido.getFont(), painel);
        JLabel telefoneLabel = Label.texto("Telefone: " + pagamento.getTelefone(), pedido.getX(), enderecoLabel.getY() + enderecoLabel.getHeight() + 10, 360, 30, titulo.getForeground(), pedido.getFont(), painel);
        JLabel emailLabel = Label.texto("Email: " + pagamento.getEmail(), pedido.getX(), telefoneLabel.getY() + telefoneLabel.getHeight() + 10, 360, 30, titulo.getForeground(), pedido.getFont(), painel);
       
        JScrollPane scroll=  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        add(scroll);
        scroll.setBounds(0, 0 , 390, 565);
        scroll.setViewportView(painel);
        painel.setPreferredSize(new Dimension(400, emailLabel.getY() + emailLabel.getHeight() + 35));
       
        painel.setVisible(true);
        setVisible(true);
    }
}
