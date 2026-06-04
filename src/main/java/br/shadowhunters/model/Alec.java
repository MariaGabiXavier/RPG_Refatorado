package br.shadowhunters.model;

import java.util.Random;

public class Alec extends Personagem implements Comparable<Alec> {

    private static final int CHANCE_ACERTO_PERCENTUAL = 70;

    public Alec() {
        super("Alec Lightwood", 120, 19, 13, 1);
    }

    public Alec(Alec modelo) {
        super(modelo);
    }

    @Override
    public Object clone() {
        return new Alec(this);
    }

    @Override
    public int compareTo(Alec outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        System.out.println("\n" + nome + " mira cuidadosamente...");

        if (dado.nextInt(100) < CHANCE_ACERTO_PERCENTUAL) {
            int dano = Math.max(0, ataque * 2 - inimigo.getDefesa());
            inimigo.receberDano(dano);
            System.out.println(nome + " acerta! Tiro preciso causou " + dano + " de dano!");
        } else {
            System.out.println(nome + " errou o alvo desta vez.");
        }
    }
}