package br.com.alura.loja.dao;

import br.com.alura.loja.modelo.Cliente;

import javax.persistence.EntityManager;
import java.util.List;

public class ClienteDao {
    private EntityManager em;

    public ClienteDao(EntityManager em) {
        this.em = em;
    }

    public void cadastrar(Cliente cliente) {
        this.em.persist(cliente);
    }

    public void atualizar(Cliente cliente) {
        this.em.merge(cliente);
    }

    public void remover(Cliente cliente) {
        cliente = this.em.merge(cliente);
        this.em.remove(cliente);
    }
    public Cliente buscarPorId(int id){
        return em.find(Cliente.class, id);
    }

    public List<Cliente> buscarTodos(){
        String query = "SELECT p FROM Cliente p";
        return em.createQuery(query).getResultList();
    }

    public List<Cliente> buscarPorNome(String nome){
        String query = "SELECT p FROM Cliente p WHERE p.nome LIKE ?1";
        return em.createQuery(query)
                .setParameter(1, nome)
                .getResultList();
    }
}