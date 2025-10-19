import java.util.*;

class Batalha {
    public static void batalhar(Personagem jogador, Inimigo inimigo, Random dado, Scanner sc) {
        System.out.println("\nBatalha: " + jogador.nome + " vs " + inimigo.nome);

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n--- Novo Turno ---");
            System.out.println(jogador);
            System.out.println(inimigo);
            System.out.println("Ações: 1-Atacar 2-Usar item 3-Habilidade especial");
            int acao = lerInt(sc, 1, 3);

            if (acao == 1) {
                int rolagemJogador = dado.nextInt(6) + 1;
                int rolagemInimigo = dado.nextInt(6) + 1;

                int danoJogador = jogador.ataque + rolagemJogador - inimigo.defesa;
                int danoInimigo = inimigo.ataque + rolagemInimigo - jogador.defesa;

                if (danoJogador > 0) inimigo.receberDano(danoJogador);
                else danoJogador = 0;

                if (danoInimigo > 0) jogador.receberDano(danoInimigo);
                else danoInimigo = 0;

                System.out.println("Você causou " + danoJogador + " de dano!");
                System.out.println("O inimigo causou " + danoInimigo + " de dano!");
            } else if (acao == 2) {
                jogador.inventario.usarItemPorNumero(sc, jogador);
            } else {
                jogador.habilidadeEspecial(dado, inimigo);
            }

            if (inimigo.estaVivo() && dado.nextInt(100) < 15) {
                inimigo.habilidadeEspecial(dado, inimigo);
            }
        }

        if (jogador.estaVivo() && !inimigo.estaVivo()) {
            System.out.println("\nVocê derrotou " + inimigo.nome + "!");
            jogador.nivel++;
            jogador.ataque += 2;
            jogador.defesa += 1;
            jogador.pontosVida += 10;
            System.out.println("Você subiu para o nível " + jogador.nivel + "! Atributos melhorados.");
        } else if (!jogador.estaVivo()) {
            System.out.println("\nVocê foi derrotado por " + inimigo.nome + "...");
        }
    }

    private static int lerInt(Scanner sc, int min, int max) {
        int opt = -1;
        while (true) {
            try {
                opt = sc.nextInt();
                if (opt < min || opt > max) {
                    System.out.println("Escolha entre " + min + " e " + max + ":");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, digite um número:");
                sc.next();
            }
        }
        return opt;
    }
}