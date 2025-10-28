import java.util.*;

public class Clary extends Personagem implements Comparable<Clary> {
    public Clary() {
        setNome("Clary Fairchild");
        setPontosVida(110);
        setAtaque(18);
        setDefesa(10);
        setNivel(1);
    }

    public Clary(Clary modelo) throws Exception {
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
        Clary retorno = null;
        try {
            retorno = new Clary(this);
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
        Clary c = (Clary)obj;
        if (!getNome().equalsIgnoreCase(c.getNome())) return false;
        if (getPontosVida() != c.getPontosVida()) return false;
        if (getAtaque() != c.getAtaque()) return false;
        if (getDefesa() != c.getDefesa()) return false;
        if (getNivel() != c.getNivel()) return false;
        if (!getInventario().equals(c.getInventario())) return false;
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
    public int compareTo(Clary c) {
        if (this == c) return 0;
        return getNome().compareToIgnoreCase(c.getNome());
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int rolagem = dado.nextInt(6) + 1;
        System.out.println("\n" + getNome() + " invoca uma Runa Mística...");

        if (rolagem <= 2) {
            System.out.println("A runa falha em brilhar. Nada acontece.");
        } else if (rolagem <= 4) {
            setPontosVida(getPontosVida() + 30);
            System.out.println(getNome() + " ativa a Runa de Criação e recupera 30 HP!");
        } else {
            setAtaque(getAtaque() + 8);
            System.out.println(getNome() + " ativa a Runa de Fúria e ganha +8 de ataque!");
        }
    }
}