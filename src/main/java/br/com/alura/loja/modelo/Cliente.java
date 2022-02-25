package br.com.alura.loja.modelo;

import javax.persistence.*;

@Entity
@Table
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Embedded
    private DadosPessoais dadosPessoais;

    public Cliente() { }

    public Cliente(String nome, String cpf) {
        this.dadosPessoais = new DadosPessoais(nome, cpf);
    }

    public int getId() {
        return id;
    }

    public String getNome(){
        return this.dadosPessoais.getNome();
    }
}
