package br.shadowhunters;

import br.shadowhunters.chapter.Capitulos;
import br.shadowhunters.factory.PersonagemFactory;
import br.shadowhunters.model.Item;
import br.shadowhunters.model.Personagem;
import br.shadowhunters.ui.Console;
import br.shadowhunters.util.InputUtil;

import java.util.Random;
import java.util.Scanner;

/**
 * Ponto de entrada do jogo Shadowhunters RPG.
 *
 * <p>Responsável apenas por: inicializar recursos, exibir o menu de seleção
 * de personagem e orquestrar a sequência de capítulos (SRP).</p>
 */
public class ShadowhuntersRPG {

    public static void main(String[] args) {
        Scanner sc   = new Scanner(System.in);
        Random  dado = new Random();

        exibirMenuPrincipal();
        Personagem jogador = criarPersonagem(sc);
        popularInventarioInicial(jogador);

        System.out.println("\nOlá, " + Console.emNegrito(jogador.getNome()) + "!");
        System.out.println("Sua missão: impedir que Valentine use o Cálice Mortal para criar um exército das sombras.");
        System.out.println("Caminhe com cuidado — armadilhas e inimigos te aguardam.\n");

        Capitulos capitulos = new Capitulos(jogador, dado, sc);

        executarCapitulo(1, capitulos::capitulo1, jogador, "Você caiu na primeira fase. Tente novamente!");
        executarCapitulo(2, capitulos::capitulo2, jogador, "Sua jornada termina aqui. Boa sorte da próxima vez!");
        executarCapitulo(3, capitulos::capitulo3, jogador, "Foi uma luta difícil. Você será lembrado.");
        executarCapitulo(4, capitulos::capitulo4, jogador, "Você falhou na infiltração final. Valentine venceu!");
        capitulos.capitulo5();

        sc.close();
    }

    // -------------------------------------------------------------------------
    // Métodos privados
    // -------------------------------------------------------------------------

    private static void exibirMenuPrincipal() {
        Console.titulo("SHADOWHUNTERS: O PORTAL DE VALENTINE");
        System.out.println("Escolha seu Shadowhunter:");
        System.out.println("1 - " + Console.emNegrito("Clary Fairchild")    + "  (Runas: cura/força)");
        System.out.println("2 - " + Console.emNegrito("Jace Herondale")     + "  (Espada: alto dano)");
        System.out.println("3 - " + Console.emNegrito("Isabelle Lightwood") + "  (Chicote: ataques rápidos)");
        System.out.println("4 - " + Console.emNegrito("Alec Lightwood")     + "  (Arco: precisão/crítico)");
        System.out.print("Opção: ");
    }

    private static Personagem criarPersonagem(Scanner sc) {
        int opcao = InputUtil.lerIntervalo(sc, 1, 4);
        sc.nextLine();
        return PersonagemFactory.criar(opcao);
    }

    private static void popularInventarioInicial(Personagem jogador) {
        jogador.getInventario().adicionarItem(new Item("Poção de Cura",  "Restaura 30 HP",                     "cura",  2));
        jogador.getInventario().adicionarItem(new Item("Tônico de Força","Aumenta ataque temporariamente",      "forca", 1));
    }

    /**
     * Executa um capítulo e encerra o programa com mensagem de derrota
     * caso o jogador não sobreviva.
     *
     * @param numero      número do capítulo (para mensagem de log)
     * @param capitulo    ação que executa o capítulo
     * @param jogador     personagem atual
     * @param msgDerrota  mensagem exibida em caso de morte
     */
    private static void executarCapitulo(int numero, Runnable capitulo,
                                         Personagem jogador, String msgDerrota) {
        capitulo.run();
        if (!jogador.estaVivo()) {
            System.out.println("\n" + msgDerrota);
            System.exit(0);
        }
    }
}
