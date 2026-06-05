package br.shadowhunters.model;

import java.util.Random;

public class Inimigo extends Personagem implements Comparable<Inimigo> {

    /** Nome canônico do chefe final */
    public static final String NOME_CHEFE_FINAL = "Valentine Morgenstern";

    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        super(nome, pontosVida, ataque, defesa, nivel);
        popularInventarioInicial();
    }

    /** Construtor de cópia*/
    public Inimigo(Inimigo modelo) {
        super(modelo);
    }

    private void popularInventarioInicial() {
        if (nivel >= 1) {
            inventario.adicionarItem(new Item("Poção de Cura", "Restaura 30 HP", "cura", 2));
        }
        if (nivel >= 2) {
            inventario.adicionarItem(new Item("Poção de Força", "Aumenta ataque em +5", "forca", 1));
        }
        if (nome.equalsIgnoreCase(NOME_CHEFE_FINAL)) {
            inventario.adicionarItem(new Item("Poção Suprema", "Recupera 60 HP", "cura_grande", 1));
            inventario.adicionarItem(new Item("Escudo Sombrio", "Aumenta defesa em +5", "defesa", 1));
        }
    }

    @Override
    public Object clone() {
        return new Inimigo(this);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo alvo) {
        ataque += 3;
        br.shadowhunters.ui.Console.titulolinha(nome + " fica mais furioso e aumenta seu ataque em 3!");
    }

    @Override
    public int compareTo(Inimigo outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }
}
