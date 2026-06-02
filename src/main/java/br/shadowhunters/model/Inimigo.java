package br.shadowhunters.model;

import java.util.Random;

public class Inimigo extends br.shadowhunters.model.Personagem implements Comparable<Inimigo> {

    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        super(nome, pontosVida, ataque, defesa, nivel);
        popularInventarioInicial();
    }

    public Inimigo(Inimigo modelo) {
        super(modelo);
    }

    private void popularInventarioInicial() {
        if (nivel >= 1) {
            inventario.adicionarItem(new br.shadowhunters.model.Item("Poção de Cura", "Restaura 30 HP", "cura", 2));
        }
        if (nivel >= 2) {
            inventario.adicionarItem(new br.shadowhunters.model.Item("Poção de Força", "Aumenta ataque em +5", "forca", 1));
        }
        if (nome.equalsIgnoreCase("Valentine Morgenstern")) {
            inventario.adicionarItem(new br.shadowhunters.model.Item("Poção Suprema", "Recupera 60 HP", "cura_grande", 1));
            inventario.adicionarItem(new br.shadowhunters.model.Item("Escudo Sombrio", "Aumenta defesa em +5", "defesa", 1));
        }
    }

    @Override
    public Object clone() {
        return new Inimigo(this);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo ignorado) {
        ataque += 3;
        System.out.println(nome + " fica mais furioso e aumenta seu ataque em 3!");
    }

    @Override
    public int compareTo(Inimigo outro) {
        return nome.compareToIgnoreCase(outro.nome);
    }
}
