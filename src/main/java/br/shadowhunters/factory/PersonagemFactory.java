package br.shadowhunters.factory;

import br.shadowhunters.model.*;

public class PersonagemFactory {

    private PersonagemFactory() {}

    public static Personagem criar(int opcao) {
        switch (opcao) {
            case 1: return new Clary();
            case 2: return new Jace();
            case 3: return new Isabelle();
            case 4: return new Alec();
            default:
                throw new IllegalArgumentException("Opção de personagem inválida: " + opcao);
        }
    }
}