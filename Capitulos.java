import java.util.*;

class Capitulos {
    public static void capitulo1(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 1:" +reset + " Ruas de Nova York");
        System.out.println("Você está investigando pistas que possam te levar até Valentine. Há dois caminhos:");
        System.out.println("1 - Beco escuro (mais rápido, porém perigoso)");
        System.out.println("2 - Mercado do submundo (mais seguro, chance de itens)");
        System.out.print("Escolha qual irá seguir: ");
        int escolha = lerInt(sc, 1, 2);

        if (escolha == 1) {
            System.out.println("\nVocê avança pelo beco... uma armadilha preparada por um aliado de Valentine aparece!");
            jogador.receberDano(15);
            System.out.println("Perdeu 15 HP. HP atual: " + jogador.pontosVida);
            System.out.println("Logo depois surge um Demônio Menor!");
            Batalha.batalhar(jogador, new Inimigo("Demônio Menor", 60, 14, 6, 1), dado, sc, negrito, reset);
        } else {
            System.out.println("\nNo mercado você negocia e encontra um vendedor que oferece runas de cura.");
            jogador.inventario.adicionarItem(new Item("Runa de Cura", "Cura grande", "cura_grande", 1));
            System.out.println("Ainda assim, um caçador corrompido aparece e ataca!");
            Batalha.batalhar(jogador, new Inimigo("Caçador Corrompido", 70, 15, 7, 1), dado, sc, negrito, reset);
        }
    }

    public static void capitulo2(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 2:" + reset + " O Instituto sob ataque");
        System.out.println("O instituto foi atacado! Há sinais de magia de Valentine. O que irá fazer?");
        System.out.println("1 - Ajudar na defesa. 2 - Procurar pistas na sala de arquivos.");
        System.out.print("Escolha: ");
        int escolha = lerInt(sc, 1, 2);

        if (escolha == 1) {
            System.out.println("Você corre para as muralhas e enfrenta um grupo de demônios!");
            Batalha.batalhar(jogador, new Inimigo("Demônio de Fogo", 85, 17, 9, 1), dado, sc, negrito, reset);
        } else {
            System.out.println("\nVocê passa horas revirando documentos e encontra uma pista sobre o local do Portal.");
            jogador.inventario.adicionarItem(new Item("Poção de Cura", "Restaura 30 HP", "cura", 1));
            if (dado.nextInt(100) < 40) {
                System.out.println("Um Corrupto Renegado te surpreende!");
                Batalha.batalhar(jogador, new Inimigo("Corrupto Renegado", 75, 16, 8, 1), dado, sc, negrito, reset);
            }
        }
    }

    public static void capitulo3(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 3:" + reset + " Ruínas de Idris");
        System.out.println("As ruínas estão tomadas por energia demoníaca. Você precisa limpar o caminho.");
        Batalha.batalhar(jogador, new Inimigo("Demônio de Eidolon", 100, 20, 10, 2), dado, sc, negrito, reset);
    }

    public static void capitulo4Valentine(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 4:" + reset + " O Altar de Valentine");
        System.out.println("Você chega ao altar onde Valentine preparou o ritual. Ele é o último inimigo.");
        System.out.println("Preparado? (1 - Sim / 2 - Usar item / 3 - Ver inventário)");
        int escolha = lerInt(sc, 1, 3);

        if (escolha == 2) {
            jogador.inventario.usarItemPorNumero(sc, jogador);
        } else if (escolha == 3) {
            jogador.inventario.listarItens();
        }

        System.out.println("Valentine surge: o duelo final começa!");
        Batalha.batalhar(jogador, new Inimigo("Valentine Morgenstern", 150, 24, 13, 3), dado, sc, negrito, reset);
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