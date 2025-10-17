import java.util.*;

class Jace extends Personagem {
    public Jace() {
        super("Jace Herondale", 130, 24, 14, 1);
    }

    public Jace(Jace outro) {
        super(outro);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        int rolagem = dado.nextInt(6) + 1;
        int dano = ataque + 6 + rolagem - inimigo.defesa;
        if (dano > 0) inimigo.receberDano(dano);
        System.out.println(nome + " usa a Espada Mortal: causa " + Math.max(dano, 0) + " de dano direto!");
    }
}