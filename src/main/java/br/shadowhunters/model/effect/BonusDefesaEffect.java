package br.shadowhunters.model.effect;

import br.shadowhunters.model.Personagem;

/** Aumenta a defesa do alvo em  ponto. */
public class BonusDefesaEffect implements ItemEffect {

    private static final int BONUS_DEFESA = 1;

    @Override
    public void aplicar(Personagem alvo, String nomeItem) {
        alvo.setDefesa(alvo.getDefesa() + BONUS_DEFESA);
        System.out.println("\n" + alvo.getNome() + " usou " + nomeItem
                + " e ganhou +" + BONUS_DEFESA + " de defesa!");
    }
}
