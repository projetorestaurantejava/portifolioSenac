package br.com.pizzariamvc.view;

import br.com.pizzariamvc.entities.Cliente;
import br.com.pizzariamvc.entities.Historico;
import br.com.pizzariamvc.util.Label;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Compras extends JPanel{
        int y;
        A a = new A();
    public Compras(){
    }
    public Compras(ArrayList<Historico> historicoList, Cliente c, JButton voltar,ArrayList<JPanel> painel, JScrollPane scroll, JButton buy, JScrollPane scroll2, JScrollPane scroll3){
        
        setLayout(null);
        setBounds(0, 100, 1366, 768);
        setBackground(Color.darkGray);
        
        voltar.setVisible(true);
        DecimalFormat df = new DecimalFormat("#,###.00");
        JLabel clienteLabel = new JLabel();
        JLabel idPedido = new JLabel();
        JLabel total = new JLabel();
        JLabel separador = new JLabel();
        JLabel aviso = Label.texto("Sem compras realizadas", 10, 20, 300, 20, new Font("Georgia", Font.BOLD, 20), this);
        aviso.setForeground(Color.white);
        aviso.setVisible(false);
        
        y = 20;
        
        if (historicoList.isEmpty()) {
            aviso.setVisible(true);
        }else{
            for (Historico historico : historicoList) {
                if (historico.getSenha().equals(c.getSenha())) {
                    clienteLabel = Label.texto("Cliente: = " + c.getNome(), 10, y, 1000, 30, Color.WHITE, new Font("Georgia", Font.BOLD, 20), this);
                    idPedido = Label.texto("Pedido: = " + historico.getIdPedido(), 10, clienteLabel.getY() + clienteLabel.getHeight() + 5, 200, 30, clienteLabel.getForeground(), clienteLabel.getFont(), this);
                    total = Label.texto("Total: = R$" + df.format(historico.getTotal()), idPedido.getX() + idPedido.getWidth(), idPedido.getY(), 200, 30, clienteLabel.getForeground(), clienteLabel.getFont(), this);
                    separador = Label.texto("---------------------------------------------------", 10, idPedido.getY() + idPedido.getHeight() + 5, 1000, 30, clienteLabel.getForeground(), clienteLabel.getFont(), this);
                    y += 100;
                }
            }
        }
        a.setA(y);
        
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                setVisible(false);
                for(int i = 0; i<painel.size(); i++){
                    painel.get(i).setVisible(true);
                }                
                scroll2.setVisible(false);
                scroll.setVisible(true);
                scroll3.setVisible(true);
                voltar.setVisible(false);
                buy.setVisible(true); 
                painel.clear();
                
            }
        });
    }
    public class A {
        private int a;
        public int getA() {
        return a;
        }    
        public void setA(int a) {
        this.a = a;
        }
    }
    public int retornarY(){
        return a.getA();
    }
    
}
