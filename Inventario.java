import java.util.*;

public class Inventario implements Cloneable, Comparable<Inventario> {
    private ArrayList<Item> itens = new ArrayList<>();

    public Inventario() {}

    public Inventario(Inventario modelo) throws Exception {
        if (modelo == null)
            throw new Exception("Modelo ausente");

        for (Item i : modelo.itens)
            this.itens.add((Item)i.clone());
    }

    @Override
    public Object clone() {
        Inventario retorno = null;
        try {
            retorno = new Inventario(this);
        } catch (Exception erro) {}
        return retorno;
    }

    @Override
    public String toString() {
        if (itens.isEmpty())
            return "Inventário vazio.";
        StringBuilder sb = new StringBuilder("=== Inventário ===\n");
        Collections.sort(itens);
        for (Item i : itens)
            sb.append(i.toString()).append("\n");
        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Inventario inv = (Inventario)obj;
        if (this.itens.size() != inv.itens.size()) return false;
        for (int i = 0; i < this.itens.size(); i++) {
            if (!this.itens.get(i).equals(inv.itens.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int retorno = 1;
        for (int i = 0; i < this.itens.size(); i++) {
            if (this.itens.get(i) != null)
                retorno = retorno * 7 + this.itens.get(i).hashCode();
        }
        if (retorno < 0) retorno = -retorno;
        return retorno;
    }

    @Override
    public int compareTo(Inventario inv) {
        if (this == inv) return 0;
        if (this.itens.size() < inv.itens.size()) return -666;
        if (this.itens.size() > inv.itens.size()) return 666;
        for (int i = 0; i < this.itens.size(); i++) {
            int comp = this.itens.get(i).compareTo(inv.itens.get(i));
            if (comp != 0) return comp;
        }
        return 0;
    }

    public void adicionarItem(Item item) {
        for (Item i : itens) {
            if (i.equals(item)) {
                i.adicionar(item.getQuantidade());
                return;
            }
        }
        itens.add((Item)item.clone());
    }

    public void removerItem(String nomeItem, Personagem p) {
        Iterator<Item> it = itens.iterator();
        while (it.hasNext()) {
            Item i = it.next();
            if (i.getNome().equalsIgnoreCase(nomeItem)) {
                i.usar(p);
                if (i.getQuantidade() <= 0)
                    it.remove();
                return;
            }
        }
        System.out.println("Item '" + nomeItem + "' não encontrado no inventário.");
    }

    public void listarItens() {
        System.out.println(this.toString());
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }
}
