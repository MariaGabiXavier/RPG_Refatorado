import java.util.*;

class Inventario implements Cloneable {
    private ArrayList<Item> itens = new ArrayList<>();

    public Inventario() {}

    public Inventario(Inventario outro) {
        for (Item i : outro.itens) {
            this.itens.add(i.clone());
        }
    }

    public void adicionarItem(Item item) {
        for (Item i : itens) {
            if (i.equals(item)) {
                i.adicionar(item.getQuantidade());
                return;
            }
        }
        itens.add(item.clone());
    }

    public void removerItem(String nomeItem, Personagem p) {
        Iterator<Item> it = itens.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i.getNome().equalsIgnoreCase(nomeItem)) {
                i.usar(p);
                if (i.getQuantidade() <= 0) it.remove();
                return;
            }
        }
        System.out.println("Item '" + nomeItem + "' não encontrado no inventário.");
    }

    public void listarItens() {
        Collections.sort(itens);
        if (itens.isEmpty()) {
            System.out.println("Inventário vazio.");
            return;
        }
        System.out.println("=== Inventário ===");
        for (Item i : itens) System.out.println(i);
    }

    public Inventario clone() {
        return new Inventario(this);
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}