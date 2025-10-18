import java.util.*;

public abstract class Personagem implements Cloneable {
    String nome;
    int pontosVida;
    int ataque;
    int defesa;
    int nivel;
    Inventario inventario;

    // Construtor padrão necessário para evitar erro nas subclasses
    public Personagem() {
        this.nome = "";
        this.pontosVida = 0;
        this.ataque = 0;
        this.defesa = 0;
        this.nivel = 0;
        this.inventario = new Inventario();
    }

    public Personagem(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    public Personagem(Personagem modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        this.nome = modelo.nome;
        this.pontosVida = modelo.pontosVida;
        this.ataque = modelo.ataque;
        this.defesa = modelo.defesa;
        this.nivel = modelo.nivel;
        this.inventario = (Inventario)modelo.inventario.clone();
    }

    @Override
    public Object clone() {
        Personagem retorno = null;
        try {
            retorno = this.getClass().getConstructor(this.getClass()).newInstance(this);
        } catch (Exception erro) {}
        return retorno;
    }

    @Override
    public String toString() {
        return this.nome + " [HP=" + this.pontosVida + ", Ataque=" + this.ataque + ", Defesa=" + this.defesa + ", Nível=" + this.nivel + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Personagem p = (Personagem)obj;
        if (!this.nome.equalsIgnoreCase(p.nome)) return false;
        if (this.pontosVida != p.pontosVida) return false;
        if (this.ataque != p.ataque) return false;
        if (this.defesa != p.defesa) return false;
        if (this.nivel != p.nivel) return false;
        if (!this.inventario.equals(p.inventario)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int retorno = 1;
        retorno = retorno * 3 + this.nome.toLowerCase().hashCode();
        retorno = retorno * 5 + ((Integer)this.pontosVida).hashCode();
        retorno = retorno * 7 + ((Integer)this.ataque).hashCode();
        retorno = retorno * 11 + ((Integer)this.defesa).hashCode();
        retorno = retorno * 13 + ((Integer)this.nivel).hashCode();
        retorno = retorno * 17 + this.inventario.hashCode();
        if (retorno < 0) retorno = -retorno;
        return retorno;
    }

    public abstract void habilidadeEspecial(Random dado, Inimigo inimigo);

    public boolean estaVivo() {
        return pontosVida > 0;
    }

    public void receberDano(int dano) {
        pontosVida -= dano;
        if (pontosVida < 0)
            pontosVida = 0;
    }
}
