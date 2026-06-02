package br.shadowhunters.model;

import br.shadowhunters.util.InputUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Inventario implements Cloneable, Comparable<Inventario> {

    private final List<br.shadowhunters.model.Item> itens = new ArrayList<>();

    public Inventario() {}

    public Inventario(Inventario modelo) {
        if (modelo == null) throw new IllegalArgumentException("Modelo de inventário ausente");
        for (br.shadowhunters.model.Item item : modelo.itens) {
            itens.add((br.shadowhunters.model.Item) item.clone());
        }
    }

    @Override
    public Object clone() {
        return new Inventario(this);
    }

    // Operações de itens
    public void adicionarItem(br.shadowhunters.model.Item item) {
        for (br.shadowhunters.model.Item existente : itens) {
            if (existente.equals(item)) {
                existente.adicionar(item.getQuantidade());
                return;
            }
        }
        itens.add((br.shadowhunters.model.Item) item.clone());
    }

    public void usarItemPorNumero(Scanner sc, br.shadowhunters.model.Personagem alvo) {
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

        br.shadowhunters.model.Item escolhido = itens.get(escolha - 1);
        escolhido.usar(alvo);

        if (escolhido.getQuantidade() <= 0) {
            itens.remove(escolhido);
        }
    }

    public void removerItem(String nomeItem, br.shadowhunters.model.Personagem alvo) {
        Iterator<br.shadowhunters.model.Item> it = itens.iterator();
        while (it.hasNext()) {
            br.shadowhunters.model.Item item = it.next();
            if (item.getNome().equalsIgnoreCase(nomeItem)) {
                item.usar(alvo);
                if (item.getQuantidade() <= 0) it.remove();
                return;
            }
        }
        System.out.println("Item '" + nomeItem + "' não encontrado no inventário.");
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public List<br.shadowhunters.model.Item> getItens() {
        return new ArrayList<>(itens);
    }

    public void listarItens() {
        System.out.println(this);
    }

    // Object overrides
    @Override
    public String toString() {
        if (itens.isEmpty()) return "Inventário vazio.\n";
        List<br.shadowhunters.model.Item> ordenados = new ArrayList<>(itens);
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
        for (br.shadowhunters.model.Item item : itens) {
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
