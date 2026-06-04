package br.shadowhunters.model.effect;

import br.shadowhunters.model.Personagem;

public interface ItemEffect {

    void aplicar(Personagem alvo, String nomeItem);
}
