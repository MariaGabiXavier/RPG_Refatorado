import java.util.*;

class Inimigo extends Personagem {
    public Inimigo(String nome, int pontosVida, int ataque, int defesa, int nivel) {
        super(nome, pontosVida, ataque, defesa, nivel);
    }

    public Inimigo(Inimigo outro) {
        super(outro);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        ataque += 3;
        System.out.println(nome + " fica mais furioso e aumenta seu ataque!");
    }
}