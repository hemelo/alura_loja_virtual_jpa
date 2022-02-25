package br.com.alura.loja.gestao;

import java.time.LocalDate;

public class RelatorioProduto {
    private String nomeProduto;
    private Long quantidadeVendida;
    private LocalDate dataUltimaVenda;

    public RelatorioProduto(String nomeProduto, Long quantidadeVendida, LocalDate dataUltimaVenda) {
        this.nomeProduto = nomeProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.dataUltimaVenda = dataUltimaVenda;
    }

    @Override
    public String toString() {
        return  "Produto:'" + nomeProduto + '\'' +
                ", Quantidade vendida=" + quantidadeVendida +
                ", Ultima venda=" + dataUltimaVenda +
                '}';
    }
}
