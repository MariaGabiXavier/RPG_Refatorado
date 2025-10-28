import java.util.*;

public class Jace extends Personagem implements Comparable<Jace> {
    public Jace() {
        setNome("Jace Herondale");
        setPontosVida(130);
        setAtaque(24);
        setDefesa(14);
        setNivel(1);
    }

    public Jace(Jace modelo) throws Exception {
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
        Jace retorno = null;
        try {
            retorno = new Jace(this);
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
        Jace j = (Jace)obj;
        if (!getNome().equalsIgnoreCase(j.getNome())) return false;
        if (getPontosVida() != j.getPontosVida()) return false;
        if (getAtaque() != j.getAtaque()) return false;
        if (getDefesa() != j.getDefesa()) return false;
        if (getNivel() != j.getNivel()) return false;
        if (!getInventario().equals(j.getInventario())) return false;
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
    public int compareTo(Jace j) {
        return getNome().compareToIgnoreCase(j.getNome());
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int rolagem = dado.nextInt(6) + 1;
        System.out.println("\n" + getNome() + " tenta usar sua Espada Mortal...");

        if (rolagem >= 4) {
            int dano = getAtaque() + 6 + rolagem - inimigo.getDefesa();
            if (dano > 0) {
                inimigo.receberDano(dano);
                System.out.println(getNome() + " acerta com precisão! Causa " + dano + " de dano direto!");
            } else {
                System.out.println(getNome() + " acerta, mas o inimigo resiste! Sem dano efetivo.");
            }
        } else {
            System.out.println("O golpe falha! " + inimigo.getNome() + " se esquiva da Espada Mortal.");
        }
    }
}