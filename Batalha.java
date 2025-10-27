import java.util.*;

class Batalha {
    public static void batalhar(Personagem jogador, Inimigo inimigo, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\nBatalha: " + negrito + jogador.nome + reset + " vs " + negrito + inimigo.nome + reset);

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            System.out.println("\n" + negrito + "--- Novo Turno ---" + reset);
            System.out.println(jogador);
            System.out.println(inimigo);
            System.out.println("Ações: 1-Atacar 2-Usar item 3-Habilidade especial 4-Fugir");
            System.out.print("Escolha: ");
            int acao = lerInt(sc, 1, 4);

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
            } else if (acao == 3) {
                jogador.habilidadeEspecial(dado, inimigo);
            } else if (acao == 4) {
            if (inimigo.nome.equalsIgnoreCase("Valentine Morgenstern")) {
                System.out.println("\n" + inimigo.nome + " é implacável! Fugir não é uma opção contra ele!");
            } else if (dado.nextInt(100) < 50) {
                System.out.println(negrito + "\nVocê aproveita uma distração e consegue escapar das garras de " + inimigo.nome + "!" + reset);
                return;
            } else {
                System.out.println("\nO " + inimigo.nome + " é muito rápido! Ele bloqueia sua rota de fuga e te ataca!");

                int rolagemInimigo = dado.nextInt(6) + 1;
                int danoInimigo = inimigo.ataque + rolagemInimigo - jogador.defesa;

                if (danoInimigo > 0) jogador.receberDano(danoInimigo);
                else danoInimigo = 0;

                System.out.println("Ele te acerta e causa " + danoInimigo + " de dano extra!");

                if (!jogador.estaVivo()) break;
            }
        }


            if (inimigo.estaVivo() && dado.nextInt(100) < 15) {
                inimigo.habilidadeEspecial(dado, inimigo);
            }
        }

        if (jogador.estaVivo() && !inimigo.estaVivo()) {
            System.out.println("\n" + negrito + "Você derrotou o " + inimigo.nome + "!" + reset);

            // ✅ Clona os itens do inimigo
            jogador.absorverInventario(inimigo);

            // ✅ Exibe os itens coletados
            if (!inimigo.inventario.estaVazio()) {
                System.out.println("Você coletou os seguintes itens do inimigo:");
                inimigo.inventario.listarItens();
            } else {
                System.out.println("O inimigo não carregava nenhum item.");
            }

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
