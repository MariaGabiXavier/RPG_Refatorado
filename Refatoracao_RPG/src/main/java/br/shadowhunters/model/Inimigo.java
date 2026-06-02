package Refatoracao_RPG.src.main.java.br.shadowhunters.model;

import java.util.*;

public class Inimigo extends Personagem implements Comparable<Inimigo> {
    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        super(nome, pontosVida, ataque, defesa, nivel);

        if (this.nivel >= 1) {
            this.inventario.adicionarItem(new Item("Poção de Cura", "Restaura 30 HP", "cura", 2));
        }
        if (this.nivel >= 2) { 
            this.inventario.adicionarItem(new Item("Poção de Força", "Aumenta ataque em +5", "força", 1));
        }
        if (this.nome.equalsIgnoreCase("Valentine Morgenstern")) { 
            this.inventario.adicionarItem(new Item("Poção Suprema", "Recupera 60 HP", "cura_grande", 1));
            this.inventario.adicionarItem(new Item("Escudo Sombrio", "Aumenta defesa em +5", "defesa", 1));
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
        return this.nome + " [HP=" + this.pontosVida + ", Ataque=" + this.ataque + ", Defesa=" + this.defesa + ", Nível=" + this.nivel + "]";
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
        return this.nome.compareToIgnoreCase(i.nome);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        this.ataque += 3;
        System.out.println(this.nome + " fica mais furioso e aumenta seu ataque!");
    }
}