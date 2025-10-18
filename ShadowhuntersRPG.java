import java.util.*;

public class ShadowhuntersRPG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random dado = new Random();

        System.out.println("=== SHADOWHUNTERS: O PORTAL DE VALENTINE ===");
        System.out.println("Escolha seu Shadowhunter:");
        System.out.println("1 - Clary Fairchild (Runas: cura/força)");
        System.out.println("2 - Jace Herondale (Espada: alto dano)");
        System.out.println("3 - Isabelle Lightwood (Chicote: ataques rápidos)");
        System.out.println("4 - Alec Lightwood (Arco: precisão/crítico)");

        int escolha = lerInt(sc, 1, 4);
        sc.nextLine(); // limpa o ENTER que ficou após nextInt

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

        jogador.inventario.adicionarItem(new Item("Poção de Cura", "Restaura 30 HP", "cura", 2));
        jogador.inventario.adicionarItem(new Item("Tônico de Força", "Aumenta ataque temporariamente", "força", 1));

        System.out.println("\nVocê é " + jogador.nome + ". Sua missão: impedir Valentine de abrir o Portal.");
        System.out.println("Caminhe com cuidado: armadilhas e inimigos aguardam. Valentine é o chefe final.");

        Capitulos.capitulo1(jogador, dado, sc);
        if (!jogador.estaVivo()) {
            System.out.println("\nVocê caiu na primeira fase da missão. Tente novamente!");
            return;
        }

        Capitulos.capitulo2(jogador, dado, sc);
        if (!jogador.estaVivo()) {
            System.out.println("\nSua jornada termina aqui. Boa sorte da próxima vez!");
            return;
        }

        Capitulos.capitulo3(jogador, dado, sc);
        if (!jogador.estaVivo()) {
            System.out.println("\nFoi uma luta difícil... você será lembrado.");
            return;
        }

        Capitulos.capitulo4Valentine(jogador, dado, sc);
    }

    private static int lerInt(Scanner sc, int min, int max) {
        int opt = -1;
        while (true) {
            try {
                opt = sc.nextInt();
                if (opt < min || opt > max) {
                    System.out.println("Escolha entre " + min + " e " + max + ":");
                    sc.nextLine(); // limpa o buffer após número fora do intervalo
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, digite um número:");
                sc.nextLine(); // limpa o buffer após erro de tipo
            }
        }
        return opt;
    }
}
