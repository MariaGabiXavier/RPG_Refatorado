package br.shadowhunters.chapter;

import br.shadowhunters.battle.Batalha;
import br.shadowhunters.model.Inimigo;
import br.shadowhunters.model.Item;
import br.shadowhunters.model.Personagem;
import br.shadowhunters.ui.Console;
import br.shadowhunters.util.InputUtil;

import java.util.Random;
import java.util.Scanner;

public class Capitulos {

    private final Personagem jogador;
    private final Random     dado;
    private final Scanner    sc;

    public Capitulos(Personagem jogador, Random dado, Scanner sc) {
        this.jogador = jogador;
        this.dado    = dado;
        this.sc      = sc;
    }

    public void capitulo1() {
        Console.titulolinha("\nCapítulo 1: Cidade dos Ossos");
        System.out.println("Você rastreia Valentine até a periferia da Cidade dos Ossos. Qual rota escolher?");
        System.out.println("1 - As Catacumbas Esquecidas");
        System.out.println("2 - O Desfiladeiro da Sombra");
        System.out.print("Sua escolha: ");

        if (InputUtil.lerIntervalo(sc, 1, 2) == 1) {
            rotaCatacumbas();
        } else {
            rotaDesfiladeiro();
        }
    }

    public void capitulo2() {
        Console.titulolinha("\nCapítulo 2: O Desafio da Biblioteca Esquecida");
        System.out.println("Suas pistas levam a uma biblioteca trancada. Como entrar?");
        System.out.println("1 - Buscar a Chave Secreta");
        System.out.println("2 - Forçar a Passagem");
        System.out.print("Sua escolha: ");

        if (InputUtil.lerIntervalo(sc, 1, 2) == 1) {
            buscarChaveSecreta();
        } else {
            forcarPassagem();
        }
    }

    public void capitulo3() {
        Console.titulolinha("\nCapítulo 3: Ponte das Ruínas de Idris");
        System.out.println("Um Demônio de Eidolon bloqueia a passagem da lendária ponte.");
        batalhar(new Inimigo("Demônio de Eidolon", 100, 20, 10, 3));

        if (jogador.estaVivo() && dado.nextInt(100) < 30) {
            System.out.println(Console.emNegrito("SURPRESA!") + " Um Ceifador de Sombras te ataca por trás!");
            batalhar(new Inimigo("Ceifador de Sombras", 120, 22, 11, 2));
        } else if (jogador.estaVivo()) {
            System.out.println("O caminho está livre, mas cure-se rapidamente.");
        }
    }

    public void capitulo4() {
        Console.titulolinha("\nCapítulo 4: O Laboratório de Sangue e a Grande Infiltração");
        System.out.println("Você precisa atravessar uma sala de ritual. O que você faz?");
        System.out.println("1 - Sala Lateral");
        System.out.println("2 - Corredor Principal");
        System.out.print("Sua escolha: ");

        if (InputUtil.lerIntervalo(sc, 1, 2) == 1) {
            salaLateral();
        } else {
            corredorPrincipal();
        }
    }

    public void capitulo5() {
        Console.titulolinha("\nCapítulo 5: O Altar de Valentine — Confronto Final");
        System.out.println("Valentine prepara o ritual. Seja rápido!");
        aguardarPreparacao();
        System.out.println("\nValentine surge: " + Console.emNegrito("O duelo final começa!"));
        batalhar(new Inimigo(Inimigo.NOME_CHEFE_FINAL, 150, 26, 15, 5));
    }

    private void rotaCatacumbas() {
        System.out.println(Console.emNegrito("\nVocê entra nas Catacumbas Esquecidas.") + " O silêncio é ensurdecedor.");
        System.out.println("Você encontra um baú lacrado. Abrirá?");
        System.out.println("1 - Abrir o Baú");
        System.out.println("2 - Ignorar e seguir em frente");
        System.out.print("Sua escolha: ");

        if (InputUtil.lerIntervalo(sc, 1, 2) == 1) {
            abrirBau();
        } else {
            System.out.println("Você passa por um caminho secreto sem surpresas.");
        }
    }

    private void abrirBau() {
        if (dado.nextInt(100) < 65) {
            System.out.println("O baú se abre! Você encontra uma Poção de Cura Superior!");
            jogador.getInventario().adicionarItem(new Item("Poção Superior", "Restaura 60 HP", "cura_grande", 1));
            jogador.setPontosVida(jogador.getPontosVida() + 15);
            System.out.println("Ganhou 15 HP de bônus.");
        } else {
            System.out.println("O baú estava amaldiçoado! Ele explode e atrai um Demônio Menor!");
            batalhar(new Inimigo("Demônio Menor", 60, 14, 6, 1));
        }
    }

    private void rotaDesfiladeiro() {
        System.out.println(Console.emNegrito("\nDesfiladeiro da Sombra:") + " infestado por criaturas de Valentine!");
        batalhar(new Inimigo("Demônio Lilith", 90, 18, 9, 3));

        if (jogador.estaVivo()) {
            System.out.println("Ao derrotar o demônio, você encontra um Amuleto de Sorte!");
            jogador.getInventario().adicionarItem(new Item("Amuleto de Sorte", "Defesa aumentada", "bonus_defesa", 1));
        }
    }

    private void buscarChaveSecreta() {
        System.out.println("Você procura a chave numa sala coberta de runas de alerta...");

        if (dado.nextInt(100) < 50) {
            System.out.println(Console.emNegrito("Silêncio perfeito!") + " Encontrou a chave sem ser notado.");
            System.out.println("Um elfo oferece um bônus: +2 de ataque!");
            jogador.setAtaque(jogador.getAtaque() + 2);
        } else {
            System.out.println("Você pisa em uma runa! Fumaça tóxica irrompe.");
            jogador.receberDano(10);
            System.out.println("Perdeu 10 HP. HP atual: " + jogador.getPontosVida());
            batalhar(new Inimigo("Guardião Secreto", 75, 16, 8, 1));
        }
    }

    private void forcarPassagem() {
        System.out.println("O barulho da porta derrubada atrai um monstro!");
        batalhar(new Inimigo("Demônio de Fogo", 85, 17, 9, 1));

        if (jogador.estaVivo()) {
            System.out.println("Você segue em busca de pistas sobre Valentine.");
        }
    }

    private void salaLateral() {
        System.out.println("Magnus Baine, mestre em magia de sangue, te intercepta!");
        batalhar(new Inimigo("Magnus Baine", 130, 25, 12, 4));

        if (jogador.estaVivo()) {
            System.out.println("Magnus deixou cair uma Poção de Força. Você ganha +3 de ataque!");
            jogador.setAtaque(jogador.getAtaque() + 3);
        }
    }

    private void corredorPrincipal() {
        System.out.println("O corredor está repleto de armadilhas rúnicas. Qual runa desativar primeiro?");
        System.out.println("1 - Runa da Dor");
        System.out.println("2 - Runa do Medo");
        System.out.print("Sua escolha: ");

        if (InputUtil.lerIntervalo(sc, 1, 2) == 1) {
            System.out.println("É uma distração! A armadilha explode em chamas!");
            jogador.receberDano(30);
            System.out.println("Perdeu 30 HP. HP atual: " + jogador.getPontosVida());
            batalhar(new Inimigo("Demônio Maior", 150, 18, 9, 3));
        } else {
            System.out.println("Você escolheu certo! Todas as armadilhas são desativadas.");
        }
    }

    private void aguardarPreparacao() {
        boolean pronto = false;
        while (!pronto) {
            System.out.println("\nPreparado? (1-Começar Batalha / 2-Usar item / 3-Ver inventário)");
            System.out.print("Sua escolha: ");
            int escolha = InputUtil.lerIntervalo(sc, 1, 3);

            switch (escolha) {
                case 1: pronto = true;                                         break;
                case 2: jogador.getInventario().usarItemPorNumero(sc, jogador); break;
                case 3: jogador.getInventario().listarItens();                  break;
            }
        }
    }

    private void batalhar(Inimigo inimigo) {
        new Batalha(jogador, inimigo, dado, sc).executar();
    }
}
