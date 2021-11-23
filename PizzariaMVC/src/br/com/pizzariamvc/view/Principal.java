package br.com.pizzariamvc.view;

import br.com.pizzariamvc.dao.ClienteDAO;
import br.com.pizzariamvc.dao.PizzaDAO;
import br.com.pizzariamvc.entities.Cliente;
import br.com.pizzariamvc.entities.Pedido;
import br.com.pizzariamvc.entities.Pizza;
import br.com.pizzariamvc.entities.Pizzaria;
import br.com.pizzariamvc.util.Botoes;
import br.com.pizzariamvc.util.Label;
import br.com.pizzariamvc.util.Paineis;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Principal{
    
    ImageIcon telefoneIcon = new ImageIcon(getClass().getResource("telefone.png"));
    ImageIcon whatsappIcon = new ImageIcon(getClass().getResource("whatsapp.png"));
    ImageIcon enderecoIcon = new ImageIcon(getClass().getResource("localizacao.png"));
    ImageIcon obterIcon = new ImageIcon(getClass().getResource("obter.png"));
    ImageIcon addIcon = new ImageIcon(getClass().getResource("add.png"));
    ImageIcon menosIcon = new ImageIcon(getClass().getResource("menos.png"));
    ImageIcon pizzaPadrao = new ImageIcon(getClass().getResource("pizza.png"));
    ImageIcon bebidaPadrao = new ImageIcon(getClass().getResource("bebida.png"));
    ImageIcon buyIcon = new ImageIcon(getClass().getResource("buy.png"));
    ImageIcon voltarIcon = new ImageIcon(getClass().getResource("voltar.png"));
    
    DecimalFormat df = new DecimalFormat("#,###.00");
    ArrayList<JLabel> labelList = new ArrayList<>();
    ArrayList<JLabel> priceList = new ArrayList<>();
    ArrayList<Integer> qtdList = new ArrayList<>();
    ArrayList<Pizza> lista;
    ArrayList<Pizza> pizzasSelect = new ArrayList<>();
    Integer yPosition = 50;
    Double total = 0.0;
    
    PedidoView pedidoView = new PedidoView();
    JScrollPane scroll;
    JScrollPane scrollSolicita;
    Cliente c;
    JPanel solicitaPizza;
    public Principal(Cliente c, Pizzaria pizzaria) throws SQLException, ClassNotFoundException {
        JFrame a = new JFrame();
        a.setSize(1366, 768);
        a.setLayout(null);
        a.setLocationRelativeTo(null);
        a.setResizable(false);
        a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        PizzaDAO pd = new PizzaDAO();
        Pizza p = new Pizza();
        lista = pd.getPizza();
        int yPizza = 20;

        JPanel titulo = Paineis.painel(0, 0, 1000, 100, Color.gray, a,"");
        titulo.setLayout(new BorderLayout());
        JLabel tituloLabel = Label.texto(pizzaria.getNome(), 0, 0, 0, 0, new Font("Georgia", Font.BOLD, 50), titulo);
        tituloLabel.setHorizontalAlignment(JLabel.CENTER);
        JPanel login = Paineis.painel(titulo.getX() + titulo.getWidth(), titulo.getY(), a.getWidth() - titulo.getWidth(), titulo.getHeight(), Color.ORANGE, a);
        JLabel nomeUser = Label.texto("Bem-vindo(a) "+c.getNome().substring(0,c.getNome().indexOf(" ")), 20,10,500,40, new Font("Georgia",Font.BOLD,18), login);
        JButton comprasButon = Botoes.botao(nomeUser.getX(), nomeUser.getY() + nomeUser.getHeight(), 50, 50, Color.ORANGE, buyIcon, login);
        JButton voltar = Botoes.botao(comprasButon.getX(), comprasButon.getY(), comprasButon.getWidth(), comprasButon.getHeight(), Color.ORANGE, voltarIcon, login);
        voltar.setVisible(false);
        
        JPanel informacao = Paineis.painel(login.getX(), login.getY() + login.getHeight(), login.getWidth(), a.getHeight() - login.getHeight(), Color.gray, a);
        JLabel telefone = Label.texto(pizzaria.getTelefone(), 20, 100, 250, 50, new Font("Georgia", Font.BOLD, 25), telefoneIcon, informacao);
        JLabel whatsapp = Label.texto(pizzaria.getWhatsapp(), telefone.getX()-40, telefone.getY() + telefone.getHeight() + 50, telefone.getWidth(), telefone.getHeight(), telefone.getFont(), whatsappIcon,informacao);
        JLabel endereco = Label.texto(pizzaria.getEndereco(), telefone.getX()-40, whatsapp.getY() + whatsapp.getHeight() + 50, whatsapp.getWidth()+300, whatsapp.getHeight(), whatsapp.getFont(), enderecoIcon,informacao);
        endereco.setFont(new Font("Georgia", Font.BOLD, 20));
        JPanel pizzas = Paineis.painel(titulo.getX(), titulo.getY() + titulo.getHeight(), titulo.getWidth(), a.getHeight() - titulo.getHeight(), Color.darkGray, a);
        solicitaPizza = Paineis.painel(login.getX(), login.getY() + login.getHeight(), login.getWidth(), 478, Color.orange, a);
        solicitaPizza.setVisible(false);
        JPanel confirmacao = Paineis.painel(solicitaPizza.getX(), solicitaPizza.getY() + solicitaPizza.getHeight(), solicitaPizza.getWidth(), a.getHeight() - solicitaPizza.getHeight(), Color.darkGray, a);
        confirmacao.setVisible(false);
        JButton confirmar = Botoes.botao("Confirmar Pedido", 100, 80, 150, 50, Color.WHITE, confirmacao);
        JLabel totalLabel = Label.texto("", 70, confirmar.getY()-60, 250, 30, Color.WHITE, telefone.getFont(), confirmacao);
        JLabel bebidaLabel = Label.texto("--------------------------------------Bebidas----------------------------------------", new Font("Georgia", Font.BOLD, 30), Color.black, pizzas);
        bebidaLabel.setVisible(false);
        
        
        for (int i = 0; i < lista.size(); i++) {
            if(lista.get(i).getTipo().equals("Pizza")){
                addPizza(lista.get(i), yPizza, pizzas, informacao, solicitaPizza, confirmacao, pizzaPadrao,totalLabel);
                yPizza += 120;
            }
        }
        
        for(int i = 0; i < lista.size(); i++){
           if (lista.get(i).getTipo().equals("Bebida") && bebidaLabel.isVisible() == false) {
                bebidaLabel.setBounds(50, yPizza, 1000, 30);
                bebidaLabel.setVisible(true);
                yPizza += 50;
                addPizza(lista.get(i), yPizza, pizzas, informacao, solicitaPizza, confirmacao, bebidaPadrao,totalLabel);
                yPizza += 120;
            }else if(lista.get(i).getTipo().equals("Bebida")){
                addPizza(lista.get(i), yPizza, pizzas, informacao, solicitaPizza, confirmacao, bebidaPadrao, totalLabel);
                yPizza += 120;
            } 
        }
        
        JScrollPane scrollPizzas =  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        a.add(scrollPizzas);
        scrollPizzas.setBounds(0,100,1000,638);
        scrollPizzas.setViewportView(pizzas);
        pizzas.setPreferredSize(new Dimension(1000,yPizza));
        
        scrollSolicita =  new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        a.add(scrollSolicita);
        scrollSolicita.setBounds(solicitaPizza.getX(),solicitaPizza.getY(),solicitaPizza.getWidth()-5,solicitaPizza.getHeight());
        scrollSolicita.setViewportView(solicitaPizza);
        solicitaPizza.setPreferredSize(new Dimension(366,yPosition));
        
        comprasButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                ArrayList<JPanel> paineil = new ArrayList<>();
                pizzas.setVisible(false);
                scrollPizzas.setVisible(false);
                paineil.add(pizzas);
                scrollSolicita.setVisible(false);
                
                ClienteDAO cDao = new ClienteDAO();
                Compras compras = new Compras();
                scroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                try {
                    if(labelList.isEmpty()){
                        informacao.setVisible(false);
                        confirmacao.setVisible(false);
                        paineil.add(informacao);
                        compras = new Compras(cDao.getHistorico(c), c, voltar, paineil,scrollPizzas, comprasButon, scroll, scrollPizzas);   
                    }else{
                        compras = new Compras(cDao.getHistorico(c), c, voltar, paineil,scrollPizzas, comprasButon, scroll, scrollSolicita);
                        solicitaPizza.setVisible(false);
                        confirmacao.setVisible(false);
                        paineil.add(solicitaPizza);
                        paineil.add(confirmacao);
                    }
                    
                    a.add(compras);
                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
                comprasButon.setVisible(false);
                a.add(scroll);
                scroll.setVisible(true);
                scroll.setBounds(0,100,1355,638);
                scroll.setViewportView(compras);
                compras.setPreferredSize(new Dimension(1366, compras.retornarY()));
            }
        });
        
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    comprasButon.setVisible(false);
                    pizzas.setVisible(false);
                    solicitaPizza.setVisible(false);
                    scrollPizzas.setVisible(false);
                    confirmacao.setVisible(false);
                    scrollSolicita.setVisible(false);
                    try {
                        pedidoView = new PedidoView(labelList, priceList, qtdList, c, pizzasSelect);
                    } catch (SQLException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                        a.add(pedidoView);
                        a.add(pedidoView.enviarPanel(totalLabel, c, total, new Pedido(), pizzasSelect, labelList, priceList, qtdList, scrollPizzas,confirmacao,solicitaPizza,pizzas,comprasButon,scrollSolicita,a, pizzaria));
                    } catch (ParseException ex) {
                        JOptionPane.showMessageDialog(null, "Erro inesperado no botão de confirmar " + ex);
                    }
            }
        });
        a.setVisible(true);
    }
    
    public void addPizza(Pizza pizza, int y, JPanel painelFundo, JPanel painelFalse, JPanel painelTrue, JPanel painelTrueButton, ImageIcon a, JLabel totalLabel){
        
        JPanel painel = Paineis.painel(50, y, 900, 100, painelFundo);
        JLabel imagem = Label.texto(0, 0, 100, 100, a, painel);
        JLabel titulo = Label.texto(pizza.getName(), 110, 0, 500, 50, new Font("Georgia", Font.BOLD, 20), painel);
        JLabel descricao = Label.texto(pizza.getDescricao(), titulo.getX(), 30, 800, 50, new Font("Georgia", Font.BOLD, 12), painel);
        JButton adc = Botoes.botao(800, 0, 100, 100, Color.orange, obterIcon, painel);
        Double price = pizza.getPrice();
        JPanel fundo = Paineis.painel(adc.getX(), adc.getY(), adc.getWidth(), adc.getHeight(), Color.LIGHT_GRAY, painel);
        fundo.setVisible(false);
        JButton adcQtd = Botoes.botao(5, 35, 25, 25, Color.WHITE, addIcon, fundo);
        JLabel qtd = Label.texto("1", adcQtd.getX() + adcQtd.getWidth() + 12, adcQtd.getY() - 18, adcQtd.getWidth() + 10 , titulo.getHeight(), new Font("Georgia", Font.BOLD, 30), fundo);
        JButton menosQtd = Botoes.botao(adcQtd.getX() + 65, adcQtd.getY(), adcQtd.getWidth(), adcQtd.getHeight(), adcQtd.getBackground(), menosIcon, fundo);
        
        if (pizza.getTipo().equals("Bebida")){
            titulo.setFont(new Font("Georgia", Font.BOLD, 30));
        }
        
        adc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scrollSolicita.setVisible(true);
                adc.setVisible(false);
                addSolicitado(titulo.getText(), String.valueOf(df.format(pizza.getPrice())), new Font("Georgia", Font.BOLD, 20), painelFalse, painelTrue, painelTrueButton);
                qtd.setText("1");
                qtdList.add(1);
                total += pizza.getPrice();
                totalLabel.setText("Total: R$ "+df.format(total));
                fundo.setVisible(true);
                pizzasSelect.add(pizza);
            }
        });
        
        adcQtd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(qtd.getText().equals("9")){
                    qtd.setBounds(qtd.getX() - 8,qtd.getY(),qtd.getWidth(),qtd.getHeight());
                }
                qtd.setText(String.valueOf(Integer.valueOf(qtd.getText()) + 1));
                int i = 0;
                for (JLabel jLabel : labelList) {
                    String temp = jLabel.getText().substring(2);
                    if (temp.equals(titulo.getText())) {
                        Preco(priceList.get(i), price, Integer.valueOf(qtd.getText()),df);
                        qtdList.set(i, qtdList.get(i) + 1);
                        
                        total+=pizza.getPrice();
                        totalLabel.setText("Total: R$ "+df.format(total));
                    }
                    i++;
                }
            }
        });
        
        menosQtd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(qtd.getText().equals("10")){
                    qtd.setBounds(qtd.getX() + 8,qtd.getY(),qtd.getWidth(),qtd.getHeight());
                }
                
                qtd.setText(String.valueOf(Integer.valueOf(qtd.getText()) - 1));
                total-=pizza.getPrice();
                totalLabel.setText("Total: R$ "+df.format(total));
                int i = 0;
                for (JLabel jLabel : labelList) {
                    String temp = jLabel.getText().substring(2);
                    if (temp.equals(titulo.getText())) {
                        Preco(priceList.get(i), price, Integer.valueOf(qtd.getText()),df);
                        qtdList.set(i, qtdList.get(i) - 1);                     
                        
                    }
                    i++;
                }
                
                if (Integer.valueOf(qtd.getText()) < 1) {
                    yPosition -= 50;
                    solicitaPizza.setPreferredSize(new Dimension(366,yPosition));
                    fundo.setVisible(false);
                    adc.setVisible(true); 
                    menosSolicitado(labelList, titulo.getText(), painelTrue, priceList, painelFalse);
                    pizzasSelect.remove(pizza);
                }
            }
        });
        
    }   
    
    public void addSolicitado(String nome, String price, Font font, JPanel painelFalse, JPanel painel, JPanel painelBotao){
        labelList.add(Label.texto("- " + nome, 20, yPosition, 300, 30, font, painel));
        priceList.add(Label.texto(price, 270, yPosition, 300, 30, font, painel));
        painelFalse.setVisible(false);
        painel.setVisible(true);
        painelBotao.setVisible(true);
        yPosition += 50;
        solicitaPizza.setPreferredSize(new Dimension(366,yPosition));
    }
    
    public void menosSolicitado(ArrayList<JLabel> label, String remover, JPanel painel, ArrayList<JLabel> litstPrice, JPanel painelFalse){
        try {

        int temp = 0;
        for (Iterator<JLabel> jlabel = labelList.iterator(); jlabel.hasNext(); temp++ ) {
            JLabel teste = jlabel.next();
            
            if(teste.getText().substring(2).equals(remover)){
                painel.remove(labelList.get(temp));
                painel.remove(litstPrice.get(temp));
                painel.setVisible(true);
                reposcisionar(labelList.get(temp), label, painel, litstPrice);
                jlabel.remove();
                litstPrice.remove(temp);
                qtdList.remove(temp);
                painel.setVisible(false);
                painel.setVisible(true);
                
                if (label.isEmpty()) {
                    painelFalse.setVisible(true);
                    painel.setVisible(false);
                }
            }
        }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro inesperado ao remover produto da tela " + e);
        }
    }
    
    public void reposcisionar(JLabel label, ArrayList<JLabel> list, JPanel painel, ArrayList<JLabel> listPrice){
        
        int y = label.getY();
        for (int i = (list.indexOf(label) + 1); i < list.size(); i++) {
            list.get(i).setBounds(20, y, 300, 20);
            listPrice.get(i).setBounds(270, y, 300, 20);
            painel.setVisible(false);
            painel.setVisible(true);
            y += 50;
        }
    }
    
    public void Preco(JLabel label, Double precoVal, Integer qtd, DecimalFormat df){
        double preco = precoVal * qtd;
        label.setText("");
        label.setText(String.valueOf(df.format(preco)));
        label.setVisible(false);
        label.setVisible(true);
    }
    
}
