package br.shadowhunters.model;

import java.util.Random;

public class Jace extends Personagem implements Comparable<Jace> {

    private static final int BONUS_DANO_ESPADA = 6;

    public Jace() {
        super("Jace Herondale", 130, 24, 14, 1);
    }

    public Jace(Jace modelo) {
        super(modelo);
    }

    @Override
    public Object clone() {
        return new Jace(this);
    }

    @Override
    public int compareTo(Jace outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int rolagem = dado.nextInt(6) + 1;
        System.out.println("\n" + nome + " ergue a Espada Mortal...");

        if (rolagem >= 4) {
            int dano = Math.max(0, ataque + BONUS_DANO_ESPADA + rolagem - inimigo.getDefesa());
            inimigo.receberDano(dano);
            System.out.println(nome + " acerta com precisão! Causa " + dano + " de dano!");
        } else {
            System.out.println("O golpe falha! " + inimigo.getNome() + " se esquiva da Espada Mortal.");
        }
    }
}