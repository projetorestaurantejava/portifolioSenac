package br.com.pizzariamvc.entities;

public class Pedido {
    private Integer idPedido;
    private Integer idClientePedido;

    /**
     * @return the idPedido
     */
    public Integer getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the idClientePedido
     */
    public Integer getIdClientePedido() {
        return idClientePedido;
    }

    /**
     * @param idClientePedido the idClientePedido to set
     */
    public void setIdClientePedido(Integer idClientePedido) {
        this.idClientePedido = idClientePedido;
    }

    public void setIdClientePedido(Cliente c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
