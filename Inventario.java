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
        StringBuilder sb = new StringBuilder("\n=== Inventário ===\n");
        Collections.sort(itens);
        for (int i = 0; i < itens.size(); i++) {
            sb.append((i + 1))
              .append(" - ")
              .append(itens.get(i).toString())
              .append("\n");
        }
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
    public ArrayList<Item> getItens() {
        return new ArrayList<>(this.itens); 
    }

    public void listarItens() {
        System.out.println(this.toString());
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public void usarItemPorNumero(Scanner sc, Personagem p) {
        if (itens.isEmpty()) {
            System.out.println("Você não tem itens no inventário.");
            return;
        }

        System.out.print(this.toString());
        System.out.print("Digite o número do item que deseja usar (ou 0 para cancelar): ");

        int escolha = -1;
        while (true) {
            try {
                escolha = sc.nextInt();
                sc.nextLine(); 
                if (escolha == 0) {
                    System.out.println("Ação cancelada.");
                    return;
                }
                if (escolha < 1 || escolha > itens.size()) {
                    System.out.print("Número inválido. Digite um número entre 1 e " + itens.size() + ": ");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Entrada inválida! Digite o número do item: ");
                sc.nextLine(); 
            }
        }

        Item escolhido = itens.get(escolha - 1);
        escolhido.usar(p);

        if (escolhido.getQuantidade() <= 0) {
            itens.remove(escolhido);
        }
    }
}
