package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Produto;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ProdutoDao {
    private EntityManager em;

    public ProdutoDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Produto produto) {
        this.em.persist(produto);
    }

    public void atualizar(Produto produto) {
        this.em.merge(produto);
    }

    public void remover(Produto produto) {
        produto = this.em.merge(produto);
        this.em.remove(produto);
    }

    public Produto buscarPorId(int id){
        return em.find(Produto.class, id);
    }

    public List<Produto> buscarTodos(){
        String query = "SELECT p FROM Produto p";
        return em.createQuery(query).getResultList();
    }

    public List<Produto> buscarPorNome(String nome){
        String query = "SELECT p FROM Produto p WHERE p.nome LIKE ?1";
        return em.createQuery(query)
                .setParameter(1, nome)
                .getResultList();
    }

    public BigDecimal buscarPrecoDoProdutoComNome(String nome){
        String query = "SELECT p.preco FROM Produto p WHERE p.nome = ?1";
        return em.createQuery(query, BigDecimal.class)
                .setParameter(1, nome)
                .getSingleResult();
    }

    public List<Produto> buscarPorNomeDaCategoria(String nome){
        String query = "SELECT p FROM Produto p WHERE p.categoria.nome LIKE ?1";
        return em.createQuery(query)
                .setParameter(1, nome)
                .getResultList();
    }

    public List<Produto> buscarPorParametros(String nome, String descricao, BigDecimal preco) {
        String queryString = "SELECT p FROM Produto p WHERE 1=1";
        if(nome != null && !nome.trim().isEmpty()) {
            queryString += " AND p.nome = :nome ";
        }
        if(preco != null){
            queryString += " AND p.preco = :preco ";
        }
        if(descricao != null && !descricao.trim().isEmpty()){
            queryString += " AND p.descricao = :descricao";
        }

        TypedQuery<Produto> query = em.createQuery(queryString, Produto.class);

        if(nome != null && !nome.trim().isEmpty()) {
            query.setParameter("nome", nome);
        }
        if(preco != null){
            query.setParameter("preco", preco);
        }
        if(descricao != null && !descricao.trim().isEmpty()){
            query.setParameter("descricao", descricao);
        }

        return query.getResultList();
    }

    public List<Produto> buscarPorParametrosCriteria(String nome, String descricao, BigDecimal preco) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
        Root<Produto> from = query.from(Produto.class);

        Predicate filtros = builder.and();
        if(nome != null && !nome.trim().isEmpty()) {
            filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
        }
        if(preco != null){
            filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
        }
        if(descricao != null && !descricao.trim().isEmpty()){
            filtros = builder.and(filtros, builder.equal(from.get("descricao"), descricao));
        }

        query.where(filtros);
        return em.createQuery(query).getResultList();
    }
}
