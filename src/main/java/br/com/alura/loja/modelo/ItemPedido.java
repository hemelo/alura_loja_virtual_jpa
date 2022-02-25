package br.com.alura.loja.modelo;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name="itens_pedido")
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int quantidade;

    @Column(name = "preco_unitario")
    private BigDecimal precoUnitario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pedido pedido;

    @ManyToOne(fetch = FetchType.LAZY)
    private Produto produto;

    public ItemPedido() {
        this.precoUnitario = produto.getPreco();
    }

    public ItemPedido(int quantidade, Pedido pedido, Produto produto){
        this.precoUnitario = produto.getPreco();
        this.produto = produto;
        this.pedido = pedido;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public BigDecimal getValor() {
        return precoUnitario.multiply(new BigDecimal(quantidade));
    }
}
