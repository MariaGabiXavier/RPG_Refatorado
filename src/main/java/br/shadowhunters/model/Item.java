package br.shadowhunters.model;

import br.shadowhunters.factory.EffectFactory;
import br.shadowhunters.model.effect.ItemEffect;

public class Item implements Comparable<Item>, Cloneable {

    private final String nome;
    private final String descricao;
    private final ItemEffect efeito;
    private int quantidade;


    public Item(String nome, String descricao, String chaveEfeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = EffectFactory.criar(chaveEfeito);
        this.quantidade = Math.max(0, quantidade);
    }

    public Item(Item modelo) {
        if (modelo == null) throw new IllegalArgumentException("Modelo de item ausente");
        this.nome = modelo.nome;
        this.descricao = modelo.descricao;
        this.efeito = modelo.efeito;
        this.quantidade = modelo.quantidade;
    }

    @Override
    public Object clone() {
        return new Item(this);
    }

    // Comportamento
    public void usar(br.shadowhunters.model.Personagem alvo) {
        if (quantidade <= 0) {
            System.out.println("Não há " + nome + " disponível!");
            return;
        }
        quantidade--;
        efeito.aplicar(alvo, nome);
    }

    public void adicionar(int qtd) {
        if (qtd < 0) throw new IllegalArgumentException("Quantidade a adicionar não pode ser negativa");
        quantidade += qtd;
    }

    // Object overrides
    @Override
    public String toString() {
        return nome + " (" + quantidade + ") - " + descricao;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Item)) return false;
        Item outro = (Item) obj;
        return nome.equalsIgnoreCase(outro.nome)
                && descricao.equalsIgnoreCase(outro.descricao);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + nome.toLowerCase().hashCode();
        hash = hash * 31 + descricao.toLowerCase().hashCode();
        return Math.abs(hash);
    }

    @Override
    public int compareTo(Item outro) {
        if (this == outro) return 0;
        int porNome = nome.compareToIgnoreCase(outro.nome);
        if (porNome != 0) return porNome;
        return descricao.compareToIgnoreCase(outro.descricao);
    }


    public String getNome()      { return nome; }
    public String getDescricao() { return descricao; }
    public int    getQuantidade(){ return quantidade; }
}
