import java.util.*;

public class Inimigo extends Personagem implements Comparable<Inimigo> {
    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        super(nome, pontosVida, ataque, defesa, nivel);

        if (getNivel() >= 1) {
            getInventario().adicionarItem(new Item("Poção de Cura", "Restaura 30 HP", "cura", 2));
        }
        if (getNivel() >= 2) { 
            getInventario().adicionarItem(new Item("Poção de Força", "Aumenta ataque em +5", "força", 1));
        }
        if (getNome().equalsIgnoreCase("Valentine Morgenstern")) { 
            getInventario().adicionarItem(new Item("Poção Suprema", "Recupera 60 HP", "cura_grande", 1));
            getInventario().adicionarItem(new Item("Escudo Sombrio", "Aumenta defesa em +5", "defesa", 1));
        }
    }

    public Inimigo(Inimigo modelo) throws Exception {
        super(modelo);
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
        return getNome() + " [HP=" + getPontosVida() + ", Ataque=" + getAtaque() + ", Defesa=" + getDefesa() + ", Nível=" + getNivel() + "]";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(Inimigo i) {
        if (this == i) return 0;
        return this.getNome().compareToIgnoreCase(i.getNome());
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int ataqueAtual = getAtaque();
        setAtaque(ataqueAtual + 3);
        System.out.println(getNome() + " fica mais furioso e aumenta seu ataque!");
    }
}