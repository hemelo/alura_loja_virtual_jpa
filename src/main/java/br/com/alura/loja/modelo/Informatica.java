package br.com.alura.loja.modelo;

import java.math.BigDecimal;

public class Informatica extends Produto{
    public String marca;
    public String modelo;

    public Informatica() { super(); }
    public Informatica(String nome, String descricao, BigDecimal preco, Categoria categoria, String marca, String modelo) {
        super(nome, descricao, preco, categoria);
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
