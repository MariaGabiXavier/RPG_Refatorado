package br.shadowhunters.model.effect;

import br.shadowhunters.model.Personagem;

/** Recupera pontos de vida do alvo. */
public class CuraEffect implements ItemEffect {

    private static final int CURA_HP = 30;

    @Override
    public void aplicar(Personagem alvo, String nomeItem) {
        alvo.setPontosVida(alvo.getPontosVida() + CURA_HP);
        System.out.println("\n" + alvo.getNome() + " usou " + nomeItem
                + " e recuperou " + CURA_HP + " HP!");
    }
}
