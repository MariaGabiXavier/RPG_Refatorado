import java.util.*;

class Batalha {
    public static void batalhar(Personagem jogador, Inimigo inimigo, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\nBatalha: " + negrito +  jogador.nome + reset + " vs " + negrito + inimigo.nome + reset);

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n" + negrito + "--- Novo Turno ---" + reset);
            System.out.println(jogador);
            System.out.println(inimigo);
            System.out.println("Ações: 1-Atacar 2-Usar item 3-Habilidade especial");
            System.out.print("Escolha: ");           
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

                System.out.println("\nVocê causou " + danoJogador + " de dano!");
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
    System.out.println("\n" + negrito + "Você derrotou " + inimigo.nome + "!" + reset);

    // Se o inimigo for Valentine (último chefe)
    if (inimigo.nome.equalsIgnoreCase("Valentine Morgenstern")) {
        System.out.println(negrito + "Obrigado por nos ajudar a salvar o mundo das sombras!\n" + reset);
        System.exit(0); 
    } else {
        jogador.nivel++;
        jogador.ataque += 2;
        jogador.defesa += 1;
        jogador.pontosVida += 10;
        System.out.println("Com isso você sobe para o nível " + jogador.nivel + " e tem seus atributos melhorados.");
    }

    } else if (!jogador.estaVivo()) {
        System.out.println("\n" + negrito + "Você foi derrotado por " + inimigo.nome + "..." + reset);
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