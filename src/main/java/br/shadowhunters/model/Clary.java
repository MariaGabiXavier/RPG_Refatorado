package br.shadowhunters.model;

import java.util.Random;

public class Clary extends Personagem implements Comparable<Clary> {

    public Clary() {
        super("Clary Fairchild", 110, 18, 10, 1);
    }

    public Clary(Clary modelo) {
        super(modelo);
    }

    @Override
    public Object clone() {
        return new Clary(this);
    }

    @Override
    public int compareTo(Clary outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int rolagem = dado.nextInt(6) + 1;
        System.out.println("\n" + nome + " invoca uma Runa Mística...");

        if (rolagem <= 2) {
            System.out.println("A runa falha em brilhar. Nada acontece.");
        } else if (rolagem <= 4) {
            pontosVida += 30;
            System.out.println(nome + " ativa a Runa de Criação e recupera 30 HP!");
        } else {
            ataque += 8;
            System.out.println(nome + " ativa a Runa de Fúria e ganha +8 de ataque!");
        }
    }
}
