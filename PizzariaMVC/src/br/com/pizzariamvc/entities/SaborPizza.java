package br.com.pizzariamvc.entities;

public class SaborPizza {
    
    private Integer idSaborPizza;
    private Integer idSabor;
    private Integer quantidade;
    private Integer idPedidoPizza;

    /**
     * @return the idSaborPizza
     */
    public Integer getIdSaborPizza() {
        return idSaborPizza;
    }

    /**
     * @param idSaborPizza the idSaborPizza to set
     */
    public void setIdSaborPizza(Integer idSaborPizza) {
        this.idSaborPizza = idSaborPizza;
    }

    /**
     * @return the idSabor
     */
    public Integer getIdSabor() {
        return idSabor;
    }

    /**
     * @param idSabor the idSabor to set
     */
    public void setIdSabor(Integer idSabor) {
        this.idSabor = idSabor;
    }

    /**
     * @return the quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * @return the idPedidoPizza
     */
    public Integer getIdPedidoPizza() {
        return idPedidoPizza;
    }

    /**
     * @param idPedidoPizza the idPedidoPizza to set
     */
    public void setIdPedidoPizza(Integer idPedidoPizza) {
        this.idPedidoPizza = idPedidoPizza;
    }
    
    
}
