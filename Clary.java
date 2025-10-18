import java.util.*;

public class Clary extends Personagem implements Comparable<Clary> {
    public Clary() {
        this.nome = "Clary Fairchild";
        this.pontosVida = 110;
        this.ataque = 18;
        this.defesa = 10;
        this.nivel = 1;
        this.inventario = new Inventario();
    }

    public Clary(Clary modelo) throws Exception {
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
        Clary retorno = null;
        try {
            retorno = new Clary(this);
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
        Clary c = (Clary)obj;
        if (!this.nome.equalsIgnoreCase(c.nome)) return false;
        if (this.pontosVida != c.pontosVida) return false;
        if (this.ataque != c.ataque) return false;
        if (this.defesa != c.defesa) return false;
        if (this.nivel != c.nivel) return false;
        if (!this.inventario.equals(c.inventario)) return false;
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
    public int compareTo(Clary c) {
        if (this == c) return 0;
        return this.nome.compareToIgnoreCase(c.nome);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        if (dado.nextBoolean()) {
            pontosVida += 30;
            System.out.println(nome + " usa a Runa de Criação e cura 30 HP!");
        } else {
            ataque += 8;
            System.out.println(nome + " ativa uma runa poderosa e ganha +8 de ataque!");
        }
    }
}
