import java.util.Random;

public class Alec extends Personagem {
    public Alec() { super("Alec Lightwood", 120, 19, 13, 1); }
    public Alec(Alec outro) { super(outro); }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int chance = dado.nextInt(100);
        if (chance < 70) {
            int dano = (ataque * 2) - inimigo.defesa;
            if (dano > 0) inimigo.receberDano(dano);
            System.out.println(nome + " atira com precisão: CRÍTICO! Causou " + Math.max(dano, 0) + " de dano!");
        } else {
            System.out.println(nome + " tentou um tiro preciso, mas errou o crítico.");
        }
    }
}