package br.shadowhunters.factory;

import br.shadowhunters.model.effect.*;

public class EffectFactory {

    private EffectFactory() {}

    public static ItemEffect criar(String chave) {
        switch (chave.toLowerCase()) {
            case "cura":         return new CuraEffect();
            case "cura_grande":  return new CuraGrandeEffect();
            case "forca":
            case "força":        return new ForcaEffect();
            case "elixir_forca":
            case "elixir_força": return new ElixirForcaEffect();
            case "defesa":       return new DefesaEffect();
            case "bonus_defesa": return new BonusDefesaEffect();
            default:
                throw new IllegalArgumentException("Efeito desconhecido: " + chave);
        }
    }
}