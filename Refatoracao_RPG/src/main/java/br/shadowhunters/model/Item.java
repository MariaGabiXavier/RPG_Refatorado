package Refatoracao_RPG.src.main.java.br.shadowhunters.model;

public class Item implements Comparable<Item>, Cloneable {
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

    public Item(Item modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        this.nome = modelo.nome;
        this.descricao = modelo.descricao;
        this.efeito = modelo.efeito;
        this.quantidade = modelo.quantidade;
    }

    @Override
    public Object clone() {
        Item retorno = null;
        try {
            retorno = new Item(this);
        } catch (Exception erro) {}
        return retorno;
    }

    @Override
    public String toString() {
        return this.nome + " (" + this.quantidade + ") - " + this.descricao;
    }

@Override
public boolean equals(Object obj) {
    if (obj == this) return true;
    if (obj == null || obj.getClass() != this.getClass()) return false;
    Item i = (Item) obj;
    return this.nome.equalsIgnoreCase(i.nome)
        && this.descricao.equalsIgnoreCase(i.descricao)
        && this.efeito.equalsIgnoreCase(i.efeito);
}


    @Override
    public int hashCode() {
        int retorno = 1;
        retorno = retorno * 3 + this.nome.toLowerCase().hashCode();
        retorno = retorno * 5 + this.descricao.toLowerCase().hashCode();
        retorno = retorno * 7 + this.efeito.toLowerCase().hashCode();
        retorno = retorno * 11 + ((Integer)this.quantidade).hashCode();
        if (retorno < 0) retorno = -retorno;
        return retorno;
    }

    @Override
    public int compareTo(Item i) {
        if (this == i) return 0;
        int comp = this.nome.compareToIgnoreCase(i.nome);
        if (comp != 0) return comp;
        comp = this.descricao.compareToIgnoreCase(i.descricao);
        if (comp != 0) return comp;
        comp = this.efeito.compareToIgnoreCase(i.efeito);
        if (comp != 0) return comp;
        if (this.quantidade < i.quantidade) return -666;
        if (this.quantidade > i.quantidade) return 666;
        return 0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void usar(Personagem p) {
        if (quantidade <= 0) {
            System.out.println("Não há " + nome + "!");
            return;
        }
        quantidade--;

        String nomePersonagem = p.getNome(); 
        int hpAtual = p.getPontosVida();
        int ataqueAtual = p.getAtaque();
        int defesaAtual = p.getDefesa();


        switch (efeito.toLowerCase()) {
            case "cura":
                p.setPontosVida(hpAtual + 30); 
                System.out.println("\n" + nomePersonagem + " usou " + nome + " e recuperou 30 HP!");
                break;
            case "força":
                p.setAtaque(ataqueAtual + 5); 
                System.out.println("\n" + nomePersonagem + " usou " + nome + " e ganhou +5 de ataque!");
                break;
            case "cura_grande":
                p.setPontosVida(hpAtual + 60); 
                System.out.println("\n" + nomePersonagem + " usou " + nome + " e recuperou 60 HP!");
                break;
            case "defesa":
                p.setDefesa(defesaAtual + 5); 
                System.out.println("\n" + nomePersonagem + " usou " + nome + " e ganhou +5 de defesa!");
                break;
            case "elixir_força":
                p.setAtaque(ataqueAtual + 3); 
                System.out.println("\n" + nomePersonagem + " usou " + nome + " e ganhou +3 de ataque!");
                break;
            case "bonus_defesa":
                p.setDefesa(defesaAtual + 1); 
                System.out.println("\n" + nomePersonagem + " usou " + nome + " e ganhou +1 de defesa!");
                break;
            default:
                System.out.println("\n" + nomePersonagem + " usou " + nome + ": " + efeito);
        }
    }
    public void adicionar(int qtd) {
        quantidade += qtd;
    }
}