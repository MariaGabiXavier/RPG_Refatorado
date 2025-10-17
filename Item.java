import java.util.*;

class Item implements Comparable<Item>, Cloneable {
    private String nome;
    private String descricao;
    private String efeito;
    private int quantidade;

    public Item(String nome, String descricao, String efeito, int quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.efeito = efeito;
        this.quantidade = Math.max(0, quantidade);
    }

    public Item(Item outro) {
        this.nome = outro.nome;
        this.descricao = outro.descricao;
        this.efeito = outro.efeito;
        this.quantidade = outro.quantidade;
    }

    public String getNome() { return nome; }
    public int getQuantidade() { return quantidade; }

    public void usar(Personagem p) {
        if (quantidade <= 0) {
            System.out.println("Não há " + nome + "!");
            return;
        }
        quantidade--;
        switch (efeito.toLowerCase()) {
            case "cura":
                p.pontosVida += 30;
                System.out.println(p.nome + " usou " + nome + " e recuperou 30 HP!");
                break;
            case "força":
                p.ataque += 5;
                System.out.println(p.nome + " usou " + nome + " e ganhou +5 de ataque!");
                break;
            case "cura_grande":
                p.pontosVida += 60;
                System.out.println(p.nome + " usou " + nome + " e recuperou 60 HP!");
                break;
            default:
                System.out.println(p.nome + " usou " + nome + ": " + efeito);
        }
    }

    public void adicionar(int qtd) {
        quantidade += qtd;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Item)) return false;
        Item outro = (Item) o;
        return this.nome.equalsIgnoreCase(outro.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome.toLowerCase());
    }

    @Override
    public int compareTo(Item outro) {
        return this.nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public Item clone() {
        return new Item(this);
    }

    @Override
    public String toString() {
        return nome + " (" + quantidade + ") - " + descricao;
    }
}