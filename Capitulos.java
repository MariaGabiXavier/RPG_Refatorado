import java.util.*;

class Capitulos {
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
    
    public static void capitulo1(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 1: Cidade dos Ossos" + reset);
        System.out.println("Você rastreia Valentine até a periferia da Cidade dos Ossos. Você encontra um desvio. Qual rota escolher?");

        System.out.println("1 - As Catacumbas Esquecidas");
        System.out.println("2 - O Desfiladeiro da Sombra");
        System.out.print("Sua escolha: ");
        int escolha = lerInt(sc, 1, 2);

        if (escolha == 1) {
            System.out.println(negrito + "\nVocê entra nas Catacumbas Esquecidas." + reset + " O silêncio é ensurdecedor.");
            System.out.println("Você encontra um baú lacrado. Tentará abri-lo?");
            System.out.println("1 - Abrir o Baú");
            System.out.println("2 - Ignorar o Baú e seguir em frente");
            System.out.print("Sua escolha: ");
            int subEscolha = lerInt(sc, 1, 2);

            if (subEscolha == 1) {
                if (dado.nextInt(100) < 65) { 
                    System.out.println("\nO baú se abre! Você encontra uma Poção de Cura Superior e Pontos de Vida extra!");
                    jogador.getInventario().adicionarItem(new Item("Poção Superior", "Restaura 60 HP", "cura_grande", 1));
                    System.out.println("Ganhou 15 HP."); 
                    jogador.setPontosVida(jogador.getPontosVida() + 15); 
                } else {
                    System.out.println("\nO baú estava amaldiçoado! Ele explode, e a maldição atrai um Demônio Menor fora do controle!");
                    Batalha.batalhar(jogador, new Inimigo("Demônio Menor", 60, 14, 6, 1), dado, sc, negrito, reset);
                }
            } else {
                System.out.println("\nVocê decide não arriscar e passa por um caminho secreto. Você avança sem surpresas.");
            }
        } else {
            System.out.println(negrito + "\nVocê segue pelo Desfiladeiro da Sombra." + reset + " O caminho está infestado por criaturas de Valentine!");
            Batalha.batalhar(jogador, new Inimigo("Demônio Lilith", 90, 18, 9, 3), dado, sc, negrito, reset);
            
            if (jogador.estaVivo()) {
                System.out.println("Ao derrotar o demônio, você encontra um Amuleto de Sorte!");
                jogador.getInventario().adicionarItem(new Item("Amuleto de Sorte", "Defesa aumentada", "bonus_defesa", 1));
            }
        }
    }

    public static void capitulo2(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 2: O Desafio da Biblioteca Esquecida" + reset);
        System.out.println("Suas pistas levam a uma biblioteca trancada. Você precisa de um método para entrar.");

        System.out.println("1 - Buscar a Chave Secreta");
        System.out.println("2 - Forçar a Passagem");
        System.out.print("Sua escolha: ");
        int escolha = lerInt(sc, 1, 2);

        if (escolha == 1) {
            System.out.println("\nVocê decide procurar a chave em uma sala próxima, que está coberta de runas de alerta.");
            System.out.println("Você precisa atravessar sem disparar o alarme.");
            if (dado.nextInt(100) < 50) {
                System.out.println(negrito + "Silêncio perfeito!" + reset + " Você encontra a chave e entra na biblioteca sem ser notado.");
                System.out.println("Você encontra um elfo que te oferece um Ponto de Habilidade. Seu Ataque aumenta em 2!");
                jogador.setAtaque(jogador.getAtaque() + 2);
                System.out.println("Seu Ataque aumentou para: " + jogador.getAtaque());
            } else {
                System.out.println("\nVocê pisa em uma runa! A sala se enche de fumaça tóxica.");
                jogador.receberDano(10);
                System.out.println("Perdeu 10 HP. HP atual: " + jogador.getPontosVida());
                System.out.println("A explosão atraiu um... Guardião Secreto!");
                Batalha.batalhar(jogador, new Inimigo("Guardião Secreto", 75, 16, 8, 1), dado, sc, negrito, reset);
            }
        } else {
            System.out.println("\nVocê decide forçar a passagem, derrubando a porta lacrada. O barulho atrai um monstro!");
            Batalha.batalhar(jogador, new Inimigo("Demônio de Fogo", 85, 17, 9, 1), dado, sc, negrito, reset);
            
            if (jogador.estaVivo()) {
                System.out.println("Após a luta, você consegue romper a porta e segue em busca de mais pistas que te levem a Valentine.");
            }
        }
    }

    public static void capitulo3(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 3: Ponte das Ruínas de Idris" + reset);
        System.out.println("Você alcança a lendária ponte que leva às ruínas principais de Idris. Um Demônio de Eidolon bloqueia a passagem.");
        Batalha.batalhar(jogador, new Inimigo("Demônio de Eidolon", 100, 20, 10, 3), dado, sc, negrito, reset);

        if (jogador.estaVivo()) {
            System.out.println("\nVocê cruzou a ponte, mas sente a presença de algo sombrio observando...");
            if (dado.nextInt(100) < 30) { 
                System.out.println(negrito + "SURPRESA!" + reset + " Um inimigo de elite, o Ceifador de Sombras, te ataca por trás!");
                Batalha.batalhar(jogador, new Inimigo("Ceifador de Sombras", 120, 22, 11, 2), dado, sc, negrito, reset);
            } else {
                System.out.println("O caminho está livre por enquanto, mas você deve se curar rapidamente.");
            }
        }
    }

    public static void capitulo4(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 4: O Laboratório de Sangue e a Grande Infiltração" + reset);
        System.out.println("Você está nos corredores finais, mas precisa atravessar uma sala de ritual. O que você faz?");
        
        System.out.println("1 - Sala Lateral");
        System.out.println("2 - Corredor Principal");
        System.out.print("Sua escolha: ");
        int escolha = lerInt(sc, 1, 2);

        if (escolha == 1) {
            System.out.println("\nVocê se esgueira pela sala lateral. Seu inimigo Magnus Beine, mestre em magia de sangue, te impede!");
            Batalha.batalhar(jogador, new Inimigo("Magnus Baine", 130, 25, 12, 4), dado, sc, negrito, reset);
            
            if (jogador.estaVivo()) {
                System.out.println("Magnus deixou cair uma Poção de Força. Você ganha +3 de ataque!");
                jogador.setAtaque(jogador.getAtaque() + 3);
                System.out.println("Seu Ataque aumentou para: " + jogador.getAtaque());
            }
        } else {
            System.out.println("\nVocê tenta passar pelo Corredor Principal, que está repleto de armadilhas rúnicas ativadas.");
            System.out.println("Você precisa desativar as runas ou sofrerá um dano massivo. Qual runa desativar primeiro?");
            System.out.println("1 - Runa da Dor");
            System.out.println("2 - Runa do Medo");
            System.out.print("Sua escolha: ");
            int subEscolha = lerInt(sc, 1, 2);

            if (subEscolha == 1) {
                System.out.println("\nVocê escolhe a Runa da Dor. É uma distração! A armadilha explode em chamas!");
                jogador.receberDano(30);
                System.out.println("Perdeu 30 HP. HP atual: " + jogador.getPontosVida());
                System.out.println("A explosão atrai um Demônio Maior de Patrulha que estava na vigilância!");
                Batalha.batalhar(jogador, new Inimigo("Demônio Maior", 150, 18, 9, 3), dado, sc, negrito, reset);
            } else {
                System.out.println("\nVocê escolhe a Runa do Medo. É a chave! Todas as armadilhas são desativadas e o caminho se abre.");
            }
        }
    }

    public static void capitulo5(Personagem jogador, Random dado, Scanner sc, String negrito, String reset) {
        System.out.println("\n" + negrito + "Capítulo 5: O Altar de Valentine - Confronto Final" + reset);
        System.out.println("Você chega ao altar onde Valentine preparou o ritual. Seja rápido, antes que ele abra o portal!");

        boolean pronto = false;
        while (!pronto) {
            System.out.println("\nPreparado? (1 - Começar Batalha / 2 - Usar item / 3 - Ver inventário)");
            System.out.print("Sua escolha: ");
            int escolha = lerInt(sc, 1, 3);
            
            if (escolha == 1) {
                pronto = true;
            } else if (escolha == 2) {
                jogador.getInventario().usarItemPorNumero(sc, jogador);
            } else if (escolha == 3) {
                jogador.getInventario().listarItens(); 
            }
        }
        
        System.out.println("\nValentine surge: " + negrito + "O duelo final começa!" + reset);
        Batalha.batalhar(jogador, new Inimigo("Valentine Morgenstern", 150, 26, 15, 5), dado, sc, negrito, reset);
    }
}