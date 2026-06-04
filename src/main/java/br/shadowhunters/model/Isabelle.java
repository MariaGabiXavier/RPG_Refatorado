package br.shadowhunters.model;

import java.util.Random;

public class Isabelle extends Personagem implements Comparable<Isabelle> {

    private static final int BONUS_ATAQUE_CHICOTE = 7;

    public Isabelle() {
        super("Isabelle Lightwood", 115, 20, 12, 1);
    }

    public Isabelle(Isabelle modelo) {
        super(modelo);
    }

    @Override
    public Object clone() {
        return new Isabelle(this);
    }

    @Override
    public int compareTo(Isabelle outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int rolagem = dado.nextInt(6) + 1;
        System.out.println("\n" + nome + " usa o Chicote Serafim...");

        if (rolagem >= 4) {
            ataque += BONUS_ATAQUE_CHICOTE;
            System.out.println(nome + " acerta com golpes rápidos! +" + BONUS_ATAQUE_CHICOTE + " de ataque!");
        } else {
            System.out.println("O inimigo desvia com agilidade! A habilidade falha.");
        }
    }
}