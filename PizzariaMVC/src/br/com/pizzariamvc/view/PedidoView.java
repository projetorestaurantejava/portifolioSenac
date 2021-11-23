package br.com.pizzariamvc.view;

import br.com.pizzariamvc.dao.PagamentoDAO;
import br.com.pizzariamvc.dao.PedidoDAO;
import br.com.pizzariamvc.dao.SaborPizzaDAO;
import br.com.pizzariamvc.entities.Cliente;
import br.com.pizzariamvc.entities.Pagamento;
import br.com.pizzariamvc.entities.Pedido;
import br.com.pizzariamvc.entities.Pizza;
import br.com.pizzariamvc.entities.Pizzaria;
import br.com.pizzariamvc.entities.SaborPizza;
import br.com.pizzariamvc.util.Botoes;
import br.com.pizzariamvc.util.Campos;
import br.com.pizzariamvc.util.Label;
import br.com.pizzariamvc.util.Paineis;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PedidoView extends JScrollPane{
    JPanel painelPrincipal;
    ArrayList<SaborPizza> saborPizzaList = new ArrayList<>();
    public PedidoView(){
    }
    
    public PedidoView(ArrayList<JLabel> pizzasLabel, ArrayList<JLabel> priceList, ArrayList<Integer> qtdList, Cliente c, ArrayList<Pizza> pizzas) throws SQLException{
        setBounds(0, 100, 1366, 275);
        setHorizontalScrollBarPolicy(HORIZONTAL_SCROLLBAR_NEVER);
        painelPrincipal = Paineis.painel(0, 100, 1350, 275, Color.darkGray);
        setViewportView(painelPrincipal);
        
        painelPrincipal.setLayout(null);
        int altura = 0;
        int temp = 0;
        
        for (JLabel pizza : pizzasLabel) {
            JLabel itensPedidoLabel = Label.texto("Itens do Pedido: ", 30, 50 + altura, 200, pizza.getHeight(), Color.white, pizza.getFont(),  painelPrincipal);
            pizza.setBounds(itensPedidoLabel.getX() + itensPedidoLabel.getWidth() - 20, 50 + altura, pizza.getWidth(), pizza.getHeight());
            pizza.setForeground(Color.white);
            
            JLabel qtdPedidoLabel = Label.texto("Quantidade: ", pizza.getX() + pizza.getWidth() + 50, itensPedidoLabel.getY(), 300, itensPedidoLabel.getHeight(), Color.WHITE, itensPedidoLabel.getFont(),  painelPrincipal);
            JLabel qtdLabel = Label.texto(String.valueOf(qtdList.get(temp)), qtdPedidoLabel.getX() + qtdPedidoLabel.getWidth() - 150, qtdPedidoLabel.getY(), 100, qtdPedidoLabel.getHeight(), qtdPedidoLabel.getBackground(), qtdPedidoLabel.getFont(), painelPrincipal);
            
                    
            JLabel pricePedidoLabel = Label.texto("Preço: ", qtdPedidoLabel.getX() + qtdPedidoLabel.getWidth() + 50, qtdPedidoLabel.getY(), 80, qtdPedidoLabel.getHeight(), Color.WHITE, qtdPedidoLabel.getFont(),  painelPrincipal);
            priceList.get(temp).setForeground(Color.WHITE);
            priceList.get(temp).setBounds(pricePedidoLabel.getX() + pricePedidoLabel.getWidth() + 10, pricePedidoLabel.getY(), 100, pricePedidoLabel.getHeight());
            
            SaborPizza saborPizza = new SaborPizza();
            saborPizza.setIdSabor(pizzas.get(temp).getIdPizza());
            saborPizza.setQuantidade(qtdList.get(temp));
            saborPizzaList.add(saborPizza);
            painelPrincipal.add(pizza);
            painelPrincipal.add(priceList.get(temp));
            altura += 30;
            temp += 1;
        }
        painelPrincipal.setPreferredSize(new Dimension(1356, altura + 50));
    }
    
    public JPanel enviarPanel(JLabel totalLabel, Cliente c, Double total, Pedido p, ArrayList<Pizza> pizzas, ArrayList<JLabel> pizzasLabel, ArrayList<JLabel> priceList, ArrayList<Integer> qtdList, 
        JScrollPane scroll, JPanel confirmacao, JPanel solicitaPizza, JPanel Painelpizzas, JButton botao, JScrollPane scroll2,JFrame a, Pizzaria pizzaria) throws ParseException{{

        JPanel painel = Paineis.painel(getX(), getY() + getHeight(), getWidth(), 400, painelPrincipal.getBackground());
        painel.setLayout(null);
        Font fonte = new Font("Georgia",Font.BOLD,20);
       
        JTextField nome = Campos.campos("Nome", 30, 50, 200, 100, Color.white, fonte, painel);
        JFormattedTextField telefone = Campos.camposFormatados("Telefone", 330, 50, 200, 100, Color.white, fonte, painel,"(##) #####-####");
        JTextField endereco = Campos.campos("Endereço", 630, 50, 200, 100, Color.white, fonte, painel);
        JTextField email = Campos.campos("E-mail", 930, 50, 200, 100, Color.white, fonte, painel); 
       
        nome.setText(c.getNome());
        telefone.setText(c.getTelefone());
        endereco.setText(c.getEndereco());
        email.setText(c.getEmail());
        painel.setVisible(true);

        JLabel pagamentoLabel = Label.texto("Opção de entrega:", 150, 210, 300, 30, Color.WHITE, new Font("Georgia", Font.BOLD, 25),  painel);

        JRadioButton delivery = new JRadioButton("Delivery   (R$ 5,00)");
        JRadioButton retirar = new JRadioButton("Retirar");
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(delivery);
        grupo.add(retirar);
        
        painel.add(totalLabel);
        totalLabel.setBounds(680, 280, 300, 30);
        DecimalFormat dc = new DecimalFormat("#,###.00");
        
        delivery.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalLabel.setText("Total: R$ "+dc.format(total+5));
            }
        });
        retirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                totalLabel.setText("Total: R$ "+dc.format(total));
            }
        });
        
        delivery.setBounds(pagamentoLabel.getX(), pagamentoLabel.getY() + pagamentoLabel.getHeight() + 10, 500, 30);
        delivery.setBackground(painel.getBackground());
        delivery.setForeground(Color.white);
        delivery.setFont(new Font("Georgia",Font.BOLD,20));
        retirar.setBounds(pagamentoLabel.getX(), delivery.getY() + delivery.getHeight() + 10, 500, 30);
        retirar.setBackground(painel.getBackground());
        retirar.setForeground(Color.white);
        retirar.setFont(new Font("Georgia",Font.BOLD,20));

        ImageIcon voltarIcon = new ImageIcon(getClass().getResource("voltar.png"));
        
        JButton voltar = Botoes.botao(50, retirar.getY()-30, 50, 50, Color.DARK_GRAY, voltarIcon, painel);
        
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                painel.setVisible(false);
                setVisible(false);
                scroll.setVisible(true);
                confirmacao.setVisible(true);
                botao.setVisible(true);
                Painelpizzas.setVisible(true);
                solicitaPizza.setVisible(true);
                Font font = new Font("Georgia", Font.BOLD, 20);
                for(int i=0; i<pizzasLabel.size();i++){
                    Label.texto(pizzasLabel.get(i),pizzasLabel.get(i).getText(), 20, (i*50)+50, 300, 30, font, solicitaPizza);
                    Label.texto(priceList.get(i),priceList.get(i).getText(), 270, (i*50)+50, 300, 20, font, solicitaPizza);
                    System.err.println((i*50)+100);
                }
                Label.texto(totalLabel,"Total: R$ "+dc.format(total), 70, 20, 250, 30, new Font("Georgia", Font.BOLD, 25), confirmacao);
                totalLabel.setForeground(Color.white);
                scroll2.setVisible(true);
                
            }
        });
        JButton enviar = Botoes.botao("Enviar pedido", 950, retirar.getY()-30, 250, 50, Color.ORANGE, painel);
        enviar.setEnabled(true);
        enviar.setForeground(Color.WHITE);
        enviar.setFont(pagamentoLabel.getFont());
        
        enviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(getPagamento(delivery, retirar).equals("")){
                    JOptionPane.showMessageDialog(null, "Selecione uma opção de entrega");
                }else{
                    
                    p.setIdClientePedido(c.getId());
                    PedidoDAO pd = new PedidoDAO();
                    try {
                        pd.setPedido(p);
                    } catch (SQLException ex) {
                        Logger.getLogger(PedidoView.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    SaborPizzaDAO sb = new SaborPizzaDAO();
                    for(SaborPizza list : saborPizzaList){
                        list.setIdPedidoPizza(p.getIdPedido());
                        try {
                            sb.setPedido(list);
                        } catch (SQLException ex) {

                        }
                    }

                    Pagamento pagamento = new Pagamento();
                    pagamento.setFormaPagamento(getPagamento(delivery, retirar));
                    pagamento.setIdPedidoPagamento(p.getIdPedido());
                    pagamento.setEmail(email.getText());
                    pagamento.setNome(nome.getText());
                    pagamento.setLocalizacao(endereco.getText());
                    pagamento.setTelefone(telefone.getText());
                    
                    if (delivery.isSelected()) {
                        pagamento.setTotal(total + 5);
                    }else{
                        pagamento.setTotal(total);
                    }

                    PagamentoDAO pgDao = new PagamentoDAO();
                    try {
                        pgDao.setPagamento(pagamento);
                    } catch (SQLException ex) {
                        Logger.getLogger(PedidoView.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    new CupomFiscal(p, pizzasLabel, priceList, qtdList, pagamento,pizzaria);
                    enviar.setEnabled(false);
                }
            }
        });

        painel.add(delivery);
        painel.add(retirar);
        painel.setVisible(true);
        return painel;
    }
    }
    public String getPagamento(JRadioButton delivery, JRadioButton retirar){
        
        if(delivery.isSelected()){
            return "delivery";
        }else if(retirar.isSelected()){
            return "retirar";
        }else{
            return "";
        }
    }
        
    
}
