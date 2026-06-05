package br.shadowhunters.model;

import br.shadowhunters.util.InputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * Gerencia a coleção de {@link Item} de um personagem.
 *
 * <p>Itens iguais (mesmo nome e descrição) são empilhados automaticamente
 * em vez de duplicados.</p>
 */
public class Inventario implements Cloneable, Comparable<Inventario> {

    private final List<Item> itens = new ArrayList<>();

    public Inventario() {}

    /** Construtor de cópia usado pelo padrão Prototype. */
    public Inventario(Inventario modelo) {
        if (modelo == null) throw new IllegalArgumentException("Modelo de inventário ausente");
        for (Item item : modelo.itens) {
            itens.add((Item) item.clone());
        }
    }

    @Override
    public Object clone() {
        return new Inventario(this);
    }

    
    public void adicionarItem(Item item) {
        for (Item existente : itens) {
            if (existente.equals(item)) {
                existente.adicionar(item.getQuantidade());
                return;
            }
        }
        itens.add((Item) item.clone());
    }

    public void usarItemPorNumero(Scanner sc, Personagem alvo) {
        if (itens.isEmpty()) {
            System.out.println("Você não tem itens no inventário.");
            return;
        }

        System.out.print(this);
        System.out.print("Digite o número do item (0 para cancelar): ");

        int escolha = InputUtil.lerIntervalo(sc, 0, itens.size());
        if (escolha == 0) {
            System.out.println("Ação cancelada.");
            return;
        }

        Item escolhido = itens.get(escolha - 1);
        try {
            escolhido.usar(alvo);
        } catch (IllegalStateException e) {
            System.out.println(e.getMessage());
        }

        if (escolhido.getQuantidade() <= 0) {
            itens.remove(escolhido);
        }
    }

    public void removerItem(String nomeItem, Personagem alvo) {
        Iterator<Item> it = itens.iterator();
        while (it.hasNext()) {
            Item item = it.next();
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                try {
                    item.usar(alvo);
                } catch (IllegalStateException e) {
                    System.out.println(e.getMessage());
                }
                if (item.getQuantidade() <= 0) it.remove();
                return;
            }
        }
        System.out.println("Item '" + nomeItem + "' não encontrado no inventário.");
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public List<Item> getItens() {
        return new ArrayList<>(itens);
    }

    public void listarItens() {
        System.out.println(this);
    }

    // Object overrides
    @Override
    public String toString() {
        if (itens.isEmpty()) return "Inventário vazio.\n";
        List<Item> ordenados = new ArrayList<>(itens);
        Collections.sort(ordenados);
        StringBuilder sb = new StringBuilder("\n=== Inventário ===\n");
        for (int i = 0; i < ordenados.size(); i++) {
            sb.append(i + 1).append(" - ").append(ordenados.get(i)).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Inventario)) return false;
        Inventario outro = (Inventario) obj;
        if (itens.size() != outro.itens.size()) return false;
        for (int i = 0; i < itens.size(); i++) {
            if (!itens.get(i).equals(outro.itens.get(i))) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;
        for (Item item : itens) {
            hash = hash * 31 + item.hashCode();
        }
        return Math.abs(hash);
    }

    @Override
    public int compareTo(Inventario outro) {
        if (this == outro) return 0;
        int porTamanho = Integer.compare(itens.size(), outro.itens.size());
        if (porTamanho != 0) return porTamanho;
        for (int i = 0; i < itens.size(); i++) {
            int comp = itens.get(i).compareTo(outro.itens.get(i));
            if (comp != 0) return comp;
        }
        return 0;
    }
}
