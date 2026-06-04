package br.shadowhunters.model.effect;

import br.shadowhunters.model.Personagem;

/** Aumenta o ataque do alvo em  pontos. */
public class ElixirForcaEffect implements ItemEffect {

    private static final int BONUS_ATAQUE = 3;

    @Override
    public void aplicar(Personagem alvo, String nomeItem) {
        alvo.setAtaque(alvo.getAtaque() + BONUS_ATAQUE);
        System.out.println("\n" + alvo.getNome() + " usou " + nomeItem
                + " e ganhou +" + BONUS_ATAQUE + " de ataque!");
    }
}
