import Refatoracao_RPG.src.main.java.br.shadowhunters.chapter.Capitulos;
import Refatoracao_RPG.src.main.java.br.shadowhunters.model.*;

import java.util.*;

public class ShadowhuntersRPG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random dado = new Random();
        String negrito = "\u001B[1m"; 
        String reset = "\u001B[0m"; 

        System.out.println("\n===" + negrito + " SHADOWHUNTERS: O PORTAL DE VALENTINE" + reset + " ===\n");
        System.out.println("1 -" + negrito + " Refatoracao_RPG.src.main.java.br.shadowhunters.model.Clary Fairchild" + reset + " (Runas: cura/força)");
        System.out.println("2 -" + negrito + " Refatoracao_RPG.src.main.java.br.shadowhunters.model.Jace Herondale" + reset + " (Espada: alto dano)");
        System.out.println("3 -" + negrito + " Refatoracao_RPG.src.main.java.br.shadowhunters.model.Isabelle Lightwood" + reset + " (Chicote: ataques rápidos)");
        System.out.println("4 -" + negrito + " Refatoracao_RPG.src.main.java.br.shadowhunters.model.Alec Lightwood" + reset + " (Arco: precisão/crítico)");
        System.out.print("Escolha seu Shadowhunter:");

        int escolha = lerInt(sc, 1, 4);
        sc.nextLine(); 

        Personagem jogador;

        try {
            switch (escolha) {
                case 1: jogador = new Clary(); break;
                case 2: jogador = new Jace(); break;
                case 3: jogador = new Isabelle(); break;
                default: jogador = new Alec(); break;
            }
        } catch (Exception e) {
            System.out.println("Erro ao criar personagem.");
            return;
        }

        jogador.getInventario().adicionarItem(new Item("Poção de Cura", "Restaura 30 HP", "cura", 2));
        jogador.getInventario().adicionarItem(new Item("Tônico de Força", "Aumenta ataque temporariamente", "força", 1));

        System.out.println("\nOlá " + jogador.getNome() + "! Sua missão de hoje é impedir que Valentine use o cálice para abrir o Portal e criar um exército mortal.");
        System.out.println("Caminhe com cuidado! Armadilhas e inimigos te aguardam.");

        Capitulos.capitulo1(jogador, dado, sc, negrito, reset);
        if (!jogador.estaVivo()) {
            System.out.println("\nVocê caiu na primeira fase da missão. Tente novamente!");
            return;
        }

        Capitulos.capitulo2(jogador, dado, sc, negrito, reset);
        if (!jogador.estaVivo()) {
            System.out.println("\nSua jornada termina aqui. Boa sorte da próxima vez!");
            return;
        }

        Capitulos.capitulo3(jogador, dado, sc, negrito, reset);
        if (!jogador.estaVivo()) {
            System.out.println("\nFoi uma luta difícil no Enigma. Você será lembrado.");
            return;
        }

        Capitulos.capitulo4(jogador, dado, sc, negrito, reset);
        if (!jogador.estaVivo()) {
            System.out.println("\nVocê falhou na infiltração final. Valentine venceu. Tente novamente!");
            return;
        }

        Capitulos.capitulo5(jogador, dado, sc, negrito, reset);
    }

    private static int lerInt(Scanner sc, int min, int max) {
        int opt = -1;
        while (true) {
            try {
                opt = sc.nextInt();
                if (opt < min || opt > max) {
                    System.out.println("Escolha entre " + min + " e " + max + ":");
                    sc.nextLine(); 
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, digite um número:");
                sc.nextLine(); 
            }
        }
        return opt;
    }
}