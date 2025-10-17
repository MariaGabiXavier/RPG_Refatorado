import java.util.*;

class Isabelle extends Personagem {
    public Isabelle() {
        super("Isabelle Lightwood", 115, 20, 12, 1);
    }

    public Isabelle(Isabelle outro) {
        super(outro);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        ataque += 7;
        System.out.println(nome + " usa o Chicote Serafim: +7 de ataque (golpes rápidos)!");
    }
}