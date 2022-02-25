package br.com.alura.loja.dao;

import br.com.alura.loja.gestao.RelatorioProduto;
import br.com.alura.loja.modelo.Pedido;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class PedidoDao {
    private EntityManager em;

    public PedidoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Pedido pedido) {
        this.em.persist(pedido);
    }

    public void atualizar(Pedido pedido) {
        this.em.merge(pedido);
    }

    public void remover(Pedido pedido) {
        pedido = this.em.merge(pedido);
        this.em.remove(pedido);
    }

    public BigDecimal valorTotalVendido() {
        String query = "SELECT SUM(p.valorTotal) FROM Pedido p";
        return em.createQuery(query, BigDecimal.class)
            .getSingleResult();
    }

    public List<RelatorioProduto> relatorioDeVendas() {
        String relatorioClass = "br.com.alura.loja.gestao.RelatorioProduto";
        String query = "SELECT new" + relatorioClass +
                "(produto.nome, SUM(item.quantidade), MAX(pedido.data)) " +
                "FROM Pedido pedido JOIN pedido.itens item " +
                "JOIN item.produto produto " +
                "GROUP BY produto.nome " +
                "ORDER BY item.quantidade";

        return em.createQuery(query, RelatorioProduto.class)
                .getResultList();
    }

    public Pedido buscarPedidoComCliente(int id) {
        return em.createQuery("SELECT p FROM Pedido p JOIN FETCH p.cliente WHERE p.id = ?1", Pedido.class)
                .setParameter(1, id)
                .getSingleResult();
    }
}