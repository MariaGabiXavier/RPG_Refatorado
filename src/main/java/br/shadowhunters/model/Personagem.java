package br.shadowhunters.model;

import java.util.Random;

public abstract class Personagem implements Cloneable {

    protected String nome;
    protected int pontosVida;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Inventario inventario;

    protected Personagem() {
        this.inventario = new Inventario();
    }

    protected Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    /** Construtor de cópia  */
    protected Personagem(Personagem modelo) {
        if (modelo == null) throw new IllegalArgumentException("Modelo de personagem ausente");
        this.nome = modelo.nome;
        this.pontosVida = modelo.pontosVida;
        this.ataque = modelo.ataque;
        this.defesa = modelo.defesa;
        this.nivel = modelo.nivel;
        this.inventario = (Inventario) modelo.inventario.clone();
    }

    @Override
    public abstract Object clone();

    public abstract void habilidadeEspecial(Random dado, Inimigo inimigo);


    // Comportamento comum

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    /** Recebe dano, garantindo que HP não fique negativo. */
    public void receberDano(int dano) {
        pontosVida = Math.max(0, pontosVida - dano);
    }

    /** Transfere todos os itens do inventário do inimigo para este personagem. */
    public void absorverInventario(Inimigo inimigo) {
        for (Item item : inimigo.getInventario().getItens()) {
            inventario.adicionarItem(item);
        }
    }

    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida
                + ", Ataque=" + ataque
                + ", Defesa=" + defesa
                + ", Nível=" + nivel + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Personagem)) return false;
        Personagem outro = (Personagem) obj;
        if (getClass() != outro.getClass()) return false;
        return nome.equalsIgnoreCase(outro.nome)
                && pontosVida == outro.pontosVida
                && ataque == outro.ataque
                && defesa == outro.defesa
                && nivel == outro.nivel
                && inventario.equals(outro.inventario);
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + nome.toLowerCase().hashCode();
        hash = hash * 31 + Integer.hashCode(pontosVida);
        hash = hash * 31 + Integer.hashCode(ataque);
        hash = hash * 31 + Integer.hashCode(defesa);
        hash = hash * 31 + Integer.hashCode(nivel);
        hash = hash * 31 + inventario.hashCode();
        return Math.abs(hash);
    }



    public String getNome()          { return nome; }
    public int getPontosVida()       { return pontosVida; }
    public int getAtaque()           { return ataque; }
    public int getDefesa()           { return defesa; }
    public int getNivel()            { return nivel; }
    public Inventario getInventario(){ return inventario; }

    public void setNome(String nome)              { this.nome = nome; }
    public void setPontosVida(int pontosVida)     { this.pontosVida = Math.max(0, pontosVida); }
    public void setAtaque(int ataque)             { this.ataque = ataque; }
    public void setDefesa(int defesa)             { this.defesa = defesa; }
    public void setNivel(int nivel)               { this.nivel = nivel; }
    public void setInventario(Inventario inv)     { this.inventario = inv; }
}
