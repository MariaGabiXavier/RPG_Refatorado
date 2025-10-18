import java.util.*;

public class Inimigo extends Personagem implements Comparable<Inimigo> {
    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        this.nome = nome;
        this.pontosVida = pontosVida;
        this.ataque = ataque;
        this.defesa = defesa;
        this.nivel = nivel;
        this.inventario = new Inventario();
    }

    public Inimigo(Inimigo modelo) throws Exception {
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
        Inimigo retorno = null;
        try {
            retorno = new Inimigo(this);
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
        Inimigo i = (Inimigo)obj;
        if (!this.nome.equalsIgnoreCase(i.nome)) return false;
        if (this.pontosVida != i.pontosVida) return false;
        if (this.ataque != i.ataque) return false;
        if (this.defesa != i.defesa) return false;
        if (this.nivel != i.nivel) return false;
        if (!this.inventario.equals(i.inventario)) return false;
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

    @Override
    public int compareTo(Inimigo i) {
        if (this == i) return 0;
        return this.nome.compareToIgnoreCase(i.nome);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        ataque += 3;
        System.out.println(nome + " fica mais furioso e aumenta seu ataque!");
    }
}
