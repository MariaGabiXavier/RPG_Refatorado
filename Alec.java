import java.util.*;

public class Alec extends Personagem implements Comparable<Alec> {
    public Alec() {
        this.nome = "Alec Lightwood";
        this.pontosVida = 120;
        this.ataque = 19;
        this.defesa = 13;
        this.nivel = 1;
        this.inventario = new Inventario();
    }

    public Alec(Alec modelo) throws Exception {
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
        Alec retorno = null;
        try {
            retorno = new Alec(this);
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
        Alec a = (Alec)obj;
        if (!this.nome.equalsIgnoreCase(a.nome)) return false;
        if (this.pontosVida != a.pontosVida) return false;
        if (this.ataque != a.ataque) return false;
        if (this.defesa != a.defesa) return false;
        if (this.nivel != a.nivel) return false;
        if (!this.inventario.equals(a.inventario)) return false;
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
    public int compareTo(Alec a) {
        if (this == a) return 0;
        return this.nome.compareToIgnoreCase(a.nome);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int chance = dado.nextInt(100);
        if (chance < 70) {
            int dano = (this.ataque * 2) - inimigo.defesa;
            if (dano > 0)
                inimigo.receberDano(dano);
            System.out.println(this.nome + " atira com precisão: CRÍTICO! Causou " + Math.max(dano, 0) + " de dano!");
        } else {
            System.out.println(this.nome + " tentou um tiro preciso, mas errou o crítico.");
        }
    }
}
