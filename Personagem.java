import java.util.*;

abstract class Personagem {
    protected String nome;
    protected int pontosVida;
    protected int ataque;
    protected int defesa;
    protected int nivel;
    protected Inventario inventario;

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    public Personagem(Personagem outro) {
        this.nome = outro.nome;
        this.pontosVida = outro.pontosVida;
        this.ataque = outro.ataque;
        this.defesa = outro.defesa;
        this.nivel = outro.nivel;
        this.inventario = new Inventario(outro.inventario);
    }

    public abstract void habilidadeEspecial(Random dado, Inimigo inimigo);

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    public void receberDano(int dano) {
        pontosVida -= dano;
        if (pontosVida < 0) pontosVida = 0;
    }

    @Override
    public String toString() {
        return nome + " [HP=" + pontosVida + ", Ataque=" + ataque + ", Defesa=" + defesa + ", Nível=" + nivel + "]";
    }
}