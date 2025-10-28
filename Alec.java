import java.util.*;

public class Alec extends Personagem implements Comparable<Alec> {
    public Alec() {
        setNome("Alec Lightwood");
        setPontosVida(120);
        setAtaque(19);
        setDefesa(13);
        setNivel(1);
    }

    public Alec(Alec modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        setNome(modelo.getNome());
        setPontosVida(modelo.getPontosVida());
        setAtaque(modelo.getAtaque());
        setDefesa(modelo.getDefesa());
        setNivel(modelo.getNivel());
        setInventario((Inventario)modelo.getInventario().clone());
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
        return getNome() + " [HP=" + getPontosVida() + ", Ataque=" + getAtaque() + ", Defesa=" + getDefesa() + ", Nível=" + getNivel() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Alec a = (Alec)obj;
        if (!getNome().equalsIgnoreCase(a.getNome())) return false;
        if (getPontosVida() != a.getPontosVida()) return false;
        if (getAtaque() != a.getAtaque()) return false;
        if (getDefesa() != a.getDefesa()) return false;
        if (getNivel() != a.getNivel()) return false;
        if (!getInventario().equals(a.getInventario())) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int retorno = 1;
        retorno = retorno * 3 + getNome().toLowerCase().hashCode();
        retorno = retorno * 5 + ((Integer)getPontosVida()).hashCode();
        retorno = retorno * 7 + ((Integer)getAtaque()).hashCode();
        retorno = retorno * 11 + ((Integer)getDefesa()).hashCode();
        retorno = retorno * 13 + ((Integer)getNivel()).hashCode();
        retorno = retorno * 17 + getInventario().hashCode();
        if (retorno < 0) retorno = -retorno;
        return retorno;
    }

    @Override
    public int compareTo(Alec a) {
        if (this == a) return 0;
        return getNome().compareToIgnoreCase(a.getNome());
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int chance = dado.nextInt(100);
        if (chance < 70) {
            int dano = (getAtaque() * 2) - inimigo.getDefesa(); 
            if (dano > 0)
                inimigo.receberDano(dano);
            System.out.println("\n" + getNome() + " atira com precisão em seu inimigo! Causou " + Math.max(dano, 0) + " de dano!");
        } else {
            System.out.println("\n" + getNome() + " tentou um tiro preciso, mas errou o alvo.");
        }
    }
}