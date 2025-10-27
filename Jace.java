import java.util.*;

public class Jace extends Personagem implements Comparable<Jace> {
    public Jace() {
        this.nome = "Jace Herondale";
        this.pontosVida = 130;
        this.ataque = 24;
        this.defesa = 14;
        this.nivel = 1;
        this.inventario = new Inventario();
    }

    public Jace(Jace modelo) throws Exception {
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
        Jace retorno = null;
        try {
            retorno = new Jace(this);
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
        Jace j = (Jace)obj;
        if (!this.nome.equalsIgnoreCase(j.nome)) return false;
        if (this.pontosVida != j.pontosVida) return false;
        if (this.ataque != j.ataque) return false;
        if (this.defesa != j.defesa) return false;
        if (this.nivel != j.nivel) return false;
        if (!this.inventario.equals(j.inventario)) return false;
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
    public int compareTo(Jace j) {
        if (this == j) return 0;
        return this.nome.compareToIgnoreCase(j.nome);
    }

    @Override
public void habilidadeEspecial(Random dado, Inimigo inimigo) {
    int rolagem = dado.nextInt(6) + 1;
    System.out.println("\n" + nome + " tenta usar sua Espada Mortal...");

    if (rolagem >= 4) {
        int dano = ataque + 6 + rolagem - inimigo.defesa;
        if (dano > 0) {
            inimigo.receberDano(dano);
            System.out.println(nome + " acerta com precisão! Causa " + dano + " de dano direto!");
        } else {
            System.out.println(nome + " acerta, mas o inimigo resiste! Sem dano efetivo.");
        }
    } else {
        System.out.println("O golpe falha! " + inimigo.nome + " se esquiva da Espada Mortal.");
    }
}

}
