public class  Personagem()
{
    private char nome;
    private short pontosVida;
    private short ataque;
    private short defesa;
    private short nivel;
    Inventario inventario;
    public Personagem(char nome, short pontosVida, short ataque ,short defesa, short nivel, Inventario inventario){
        nome = this.nome;
        pontosVida = this.pontosVida;
        ataque = this.ataque;
        defesa = this.defesa;
        nivel = this.nivel;
        inventario = this.inventario;
    }
}

public class Guerreiro extends Personagem(){
    public Guerreiro(char nome, short pontosVida, short ataque ,short defesa, short nivel, Inventario inventario){
        nome = this.nome;
        pontosVida = this.pontosVida;
        ataque = this.ataque;
        defesa = this.defesa;
        nivel = this.nivel;
        inventario = this.inventario;
} 

public class Mago extends Personagem(){
    public Mago(char nome, short pontosVida, short ataque ,short defesa, short nivel, Inventario inventario){
        nome = this.nome;
        pontosVida = this.pontosVida;
        ataque = this.ataque;
        defesa = this.defesa;
        nivel = this.nivel;
        inventario = this.inventario;
} 

public class Arqueiro extends Personagem(){
    public Arqueiro(char nome, short pontosVida, short ataque ,short defesa, short nivel, Inventario inventario){
        nome = this.nome;
        pontosVida = this.pontosVida;
        ataque = this.ataque;
        defesa = this.defesa;
        nivel = this.nivel;
        inventario = this.inventario;
} 

public class Inimigo extends Personagem(){
    public Inimigo(char nome, short pontosVida, short ataque ,short defesa, short nivel, Inventario inventario){
        nome = this.nome;
        pontosVida = this.pontosVida;
        ataque = this.ataque;
        defesa = this.defesa;
        nivel = this.nivel;
        inventario = this.inventario;
}

public class Item(){
    private char nome;
    private char descricao;
    private char efeito;
    private short quantidade;
    public Item(private char nome, private char descricao, private char efeito, private short quantidade){

    }
}
public class Inventario(){
    
}