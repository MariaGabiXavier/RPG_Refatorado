import java.util.*;

public class Isabelle extends Personagem implements Comparable<Isabelle> {
    public Isabelle() {
        setNome("Isabelle Lightwood");
        setPontosVida(115);
        setAtaque(20);
        setDefesa(12);
        setNivel(1);
    }

    public Isabelle(Isabelle modelo) throws Exception {
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
        Isabelle retorno = null;
        try {
            retorno = new Isabelle(this);
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
        Isabelle i = (Isabelle)obj;
        if (!getNome().equalsIgnoreCase(i.getNome())) return false;
        if (getPontosVida() != i.getPontosVida()) return false;
        if (getAtaque() != i.getAtaque()) return false;
        if (getDefesa() != i.getDefesa()) return false;
        if (getNivel() != i.getNivel()) return false;
        if (!getInventario().equals(i.getInventario())) return false;
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
    public int compareTo(Isabelle i) {
        if (this == i) return 0;
        return getNome().compareToIgnoreCase(i.getNome());
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int rolagem = dado.nextInt(6) + 1;
        System.out.println("\n" + getNome() + " tenta usar o Chicote Serafim...");

        if (rolagem >= 4) {
            setAtaque(getAtaque() + 7);
            System.out.println(getNome() + " acerta com golpes rápidos! +7 de ataque temporário!");
        } else {
            System.out.println("Mas o inimigo desvia com agilidade! A habilidade falha.");
        }
    }
}