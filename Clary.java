import java.util.*;

class Clary extends Personagem {
    public Clary() {
        super("Clary Fairchild", 110, 18, 10, 1);
    }

    public Clary(Clary outro) {
        super(outro);
    }

    @Override
    public void habilidadeEspecial(Random dado, Inimigo inimigo) {
        if (dado.nextBoolean()) {
            pontosVida += 30;
            System.out.println(nome + " usa a Runa de Criação e cura 30 HP!");
        } else {
            ataque += 8;
            System.out.println(nome + " ativa uma runa poderosa e ganha +8 de ataque!");
        }
    }
}