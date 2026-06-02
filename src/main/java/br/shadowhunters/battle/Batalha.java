package Refatoracao_RPG.src.main.java.br.shadowhunters.battle;

import Refatoracao_RPG.src.main.java.br.shadowhunters.model.Inimigo;
import Refatoracao_RPG.src.main.java.br.shadowhunters.model.Personagem;

import java.util.*;

class Batalha {
    public static void batalhar(Personagem jogador, Inimigo inimigo, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\nRefatoracao_RPG.src.main.java.br.shadowhunters.battle.Batalha: " + negrito + jogador.getNome() + reset + " vs " + negrito + inimigo.getNome() + reset);

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
                int danoJogador = jogador.getAtaque() + rolagemJogador - inimigo.getDefesa();
                int danoInimigo = inimigo.getAtaque() + rolagemInimigo - jogador.getDefesa();

                if (danoJogador > 0) inimigo.receberDano(danoJogador);
                else danoJogador = 0;

                if (danoInimigo > 0) jogador.receberDano(danoInimigo);
                else danoInimigo = 0;

                System.out.println("\nVocê causou " + danoJogador + " de dano!");
                System.out.println("O inimigo causou " + danoInimigo + " de dano!");
            } else if (acao == 2) {
                jogador.getInventario().usarItemPorNumero(sc, jogador);
            } else if (acao == 3) {
                jogador.habilidadeEspecial(dado, inimigo);
            } else if (acao == 4) {
                if (inimigo.getNome().equalsIgnoreCase("Valentine Morgenstern")) {
                    System.out.println("\n" + inimigo.getNome() + " é implacável! Fugir não é uma opção contra ele!");
                } else if (dado.nextInt(100) < 50) {
                    System.out.println(negrito + "\nVocê aproveita uma distração e consegue escapar das garras de " + inimigo.getNome() + "!" + reset);
                    return;
                } else {
                    System.out.println("\nO " + inimigo.getNome() + " é muito rápido! Ele bloqueia sua rota de fuga e te ataca!");

                    int rolagemInimigo = dado.nextInt(6) + 1;
                    int danoInimigo = inimigo.getAtaque() + rolagemInimigo - jogador.getDefesa();

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
            System.out.println("\n" + negrito + "Você derrotou o " + inimigo.getNome() + "!" + reset);
            if (inimigo.getNome().equalsIgnoreCase("Valentine Morgenstern")) {
                System.out.println(negrito + "Obrigado por nos ajudar a salvar o mundo das sombras!\n" + reset);
                System.exit(0);
            } else {
                jogador.absorverInventario(inimigo);
                if (!inimigo.getInventario().estaVazio()) {
                    System.out.println("Você coletou os seguintes itens do " + inimigo.getNome()+":");
                    inimigo.getInventario().listarItens();
                } else {
                    System.out.println("O inimigo não carregava nenhum item.");
                }

                jogador.setNivel(jogador.getNivel() + 1);
                jogador.setAtaque(jogador.getAtaque() + 2);
                jogador.setDefesa(jogador.getDefesa() + 1);
                jogador.setPontosVida(jogador.getPontosVida() + 10);

                System.out.println("Com isso você sobe para o nível " + jogador.getNivel() + " e tem seus atributos melhorados.");
            }
        } else if (!jogador.estaVivo()) {
            System.out.println("\n" + negrito + "Você foi derrotado por " + inimigo.getNome() + "..." + reset);
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