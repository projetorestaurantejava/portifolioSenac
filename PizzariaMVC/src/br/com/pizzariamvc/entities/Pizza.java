package br.com.pizzariamvc.entities;


public class Pizza {

    private Integer idPizza;
    private String name;
    private String descricao;
    private Double price;
    private String tipo;
    
    public Pizza() {
    }

    public Pizza(Integer idPizza, String name, String descricao, Double price, String tipo) {
        this.idPizza = idPizza;
        this.name = name;
        this.descricao = descricao;
        this.price = price;
        this.tipo = tipo;
    }

    /**
     * @return the idPizza
     */
    public Integer getIdPizza() {
        return idPizza;
    }

    /**
     * @param idPizza the idPizza to set
     */
    public void setIdPizza(Integer idPizza) {
        this.idPizza = idPizza;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * @return the tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
