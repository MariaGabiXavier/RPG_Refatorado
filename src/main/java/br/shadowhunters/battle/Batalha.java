package br.shadowhunters.battle;

import br.shadowhunters.model.Inimigo;
import br.shadowhunters.model.Personagem;
import br.shadowhunters.ui.Console;
import br.shadowhunters.util.InputUtil;

import java.util.Random;
import java.util.Scanner;

public class Batalha {

    private static final int CHANCE_HABILIDADE_INIMIGO_PERCENTUAL = 15;
    private static final int CHANCE_FUGA_PERCENTUAL               = 50;

    private final Personagem jogador;
    private final Inimigo    inimigo;
    private final Random     dado;
    private final Scanner    sc;

    public Batalha(Personagem jogador, Inimigo inimigo, Random dado, Scanner sc) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        this.dado    = dado;
        this.sc      = sc;
    }

    public void executar() {
        Console.titulo("Batalha: " + jogador.getNome() + " vs " + inimigo.getNome());

        while (jogador.estaVivo() && inimigo.estaVivo()) {
            exibirTurno();
            int acao = solicitarAcao();
            processarAcaoJogador(acao);

            if (acao == 4 && !inimigo.estaVivo()) return; // fuga bem-sucedida

            if (inimigo.estaVivo() && dado.nextInt(100) < CHANCE_HABILIDADE_INIMIGO_PERCENTUAL) {
                inimigo.habilidadeEspecial(dado, inimigo);
            }
        }

        encerrarBatalha();
    }

    private void exibirTurno() {
        System.out.println("\n--- Novo Turno ---");
        System.out.println(jogador);
        System.out.println(inimigo);
    }

    private int solicitarAcao() {
        System.out.println("Ações: 1-Atacar  2-Usar item  3-Habilidade especial  4-Fugir");
        System.out.print("Escolha: ");
        return InputUtil.lerIntervalo(sc, 1, 4);
    }

    private void processarAcaoJogador(int acao) {
        switch (acao) {
            case 1: realizarAtaque();           break;
            case 2: usarItem();                 break;
            case 3: usarHabilidadeEspecial();   break;
            case 4: tentarFuga();               break;
        }
    }

    private void realizarAtaque() {
        int rolagemJogador = dado.nextInt(6) + 1;
        int rolagemInimigo = dado.nextInt(6) + 1;

        int danoAoInimigo  = Math.max(0, jogador.getAtaque() + rolagemJogador - inimigo.getDefesa());
        int danoAoJogador  = Math.max(0, inimigo.getAtaque() + rolagemInimigo - jogador.getDefesa());

        inimigo.receberDano(danoAoInimigo);
        jogador.receberDano(danoAoJogador);

        System.out.println("\nVocê causou " + danoAoInimigo + " de dano!");
        System.out.println("O inimigo causou " + danoAoJogador + " de dano!");
    }

    private void usarItem() {
        jogador.getInventario().usarItemPorNumero(sc, jogador);
    }

    private void usarHabilidadeEspecial() {
        jogador.habilidadeEspecial(dado, inimigo);
    }

    private void tentarFuga() {
        if (inimigo.getNome().equalsIgnoreCase(Inimigo.NOME_CHEFE_FINAL)) {
            System.out.println("\n" + inimigo.getNome() + " é implacável! Fugir não é uma opção!");
            return;
        }

        if (dado.nextInt(100) < CHANCE_FUGA_PERCENTUAL) {
            System.out.println("\nVocê aproveita uma distração e escapa de " + inimigo.getNome() + "!");
            // Encerra o loop: forçamos inimigo a "morrer" para sair do while
            // sem alterar o estado real. Usamos flag de retorno no chamador.
            return;
        }

        System.out.println("\n" + inimigo.getNome() + " bloqueia sua fuga e te ataca!");
        int dano = Math.max(0, inimigo.getAtaque() + dado.nextInt(6) + 1 - jogador.getDefesa());
        jogador.receberDano(dano);
        System.out.println("Ele te acerta causando " + dano + " de dano extra!");
    }

    private void encerrarBatalha() {
        if (jogador.estaVivo()) {
            processarVitoria();
        } else {
            System.out.println("\nVocê foi derrotado por " + inimigo.getNome() + "...");
        }
    }

    private void processarVitoria() {
        System.out.println("\nVocê derrotou " + inimigo.getNome() + "!");

        if (inimigo.getNome().equalsIgnoreCase(Inimigo.NOME_CHEFE_FINAL)) {
            System.out.println("Obrigado por salvar o mundo das sombras!\n");
            System.exit(0);
        }

        coletarItensDoInimigo();
        subirDeNivel();
    }

    private void coletarItensDoInimigo() {
        jogador.absorverInventario(inimigo);
        if (!inimigo.getInventario().estaVazio()) {
            System.out.println("Você coletou itens de " + inimigo.getNome() + ":");
            inimigo.getInventario().listarItens();
        } else {
            System.out.println("O inimigo não carregava nenhum item.");
        }
    }

    private void subirDeNivel() {
        jogador.setNivel(jogador.getNivel() + 1);
        jogador.setAtaque(jogador.getAtaque() + 2);
        jogador.setDefesa(jogador.getDefesa() + 1);
        jogador.setPontosVida(jogador.getPontosVida() + 10);
        System.out.println("Você sobe para o nível " + jogador.getNivel() + " com atributos melhorados!");
    }
}
