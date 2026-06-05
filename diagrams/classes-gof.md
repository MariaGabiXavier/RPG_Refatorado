# Diagrama de Classes - Padrões GoF

Este diagrama destaca as classes de domínio e os pontos em que os padrões GoF
foram aplicados na refatoração.

```mermaid
classDiagram
    direction LR

    class Personagem {
        <<abstract>>
        #String nome
        #int pontosVida
        #int ataque
        #int defesa
        #int nivel
        #Inventario inventario
        +habilidadeEspecial(Random dado, Inimigo inimigo)*
        +clone() Object
        +estaVivo() boolean
        +receberDano(int dano)
        +absorverInventario(Inimigo inimigo)
    }

    class Clary {
        +habilidadeEspecial(Random dado, Inimigo inimigo)
        +clone() Object
    }
    class Jace {
        +habilidadeEspecial(Random dado, Inimigo inimigo)
        +clone() Object
    }
    class Isabelle {
        +habilidadeEspecial(Random dado, Inimigo inimigo)
        +clone() Object
    }
    class Alec {
        +habilidadeEspecial(Random dado, Inimigo inimigo)
        +clone() Object
    }
    class Inimigo {
        +habilidadeEspecial(Random dado, Inimigo inimigo)
        +clone() Object
    }

    Personagem <|-- Clary
    Personagem <|-- Jace
    Personagem <|-- Isabelle
    Personagem <|-- Alec
    Personagem <|-- Inimigo

    class Inventario {
        -List~Item~ itens
        +adicionarItem(Item item)
        +usarItemPorNumero(Scanner sc, Personagem alvo)
        +removerItem(String nomeItem, Personagem alvo)
        +getItens() List~Item~
        +clone() Object
    }

    class Item {
        -String nome
        -String descricao
        -ItemEffect efeito
        -int quantidade
        +usar(Personagem alvo)
        +adicionar(int qtd)
        +clone() Object
        +compareTo(Item outro) int
    }

    Personagem *-- Inventario : possui
    Inventario *-- Item : contém

    class ItemEffect {
        <<interface>>
        +aplicar(Personagem alvo, String nomeItem)
    }

    class CuraEffect {
        +aplicar(Personagem alvo, String nomeItem)
    }
    class CuraGrandeEffect {
        +aplicar(Personagem alvo, String nomeItem)
    }
    class ForcaEffect {
        +aplicar(Personagem alvo, String nomeItem)
    }
    class ElixirForcaEffect {
        +aplicar(Personagem alvo, String nomeItem)
    }
    class DefesaEffect {
        +aplicar(Personagem alvo, String nomeItem)
    }
    class BonusDefesaEffect {
        +aplicar(Personagem alvo, String nomeItem)
    }

    ItemEffect <|.. CuraEffect
    ItemEffect <|.. CuraGrandeEffect
    ItemEffect <|.. ForcaEffect
    ItemEffect <|.. ElixirForcaEffect
    ItemEffect <|.. DefesaEffect
    ItemEffect <|.. BonusDefesaEffect
    Item o-- ItemEffect : Strategy

    class PersonagemFactory {
        <<utility>>
        +criar(int opcao) Personagem
    }

    class EffectFactory {
        <<utility>>
        +criar(String chave) ItemEffect
    }

    PersonagemFactory ..> Personagem : Factory Method
    EffectFactory ..> ItemEffect : Factory Method
    Item ..> EffectFactory : resolve efeito

    class Batalha {
        -Personagem jogador
        -Inimigo inimigo
        -Random dado
        -Scanner sc
        +executar()
    }

    class Capitulos {
        -Personagem jogador
        -Random dado
        -Scanner sc
        +capitulo1()
        +capitulo2()
        +capitulo3()
        +capitulo4()
        +capitulo5()
    }

    Capitulos ..> Batalha : cria e executa
    Batalha ..> Personagem : altera estado
    Batalha ..> Inimigo : altera estado
    Batalha ..> Inventario : usa item
```

## Padrões Representados

| Padrão | Classes | Intenção |
|---|---|---|
| Strategy | `ItemEffect` e implementações | Separar cada efeito de item em uma estratégia independente. |
| Factory Method | `PersonagemFactory`, `EffectFactory` | Centralizar a criação de personagens e efeitos. |
| Prototype | `clone()` e construtores de cópia em `Personagem`, `Inventario` e `Item` | Garantir cópias profundas dos objetos mutáveis. |
| Template Method / Polimorfismo | `Personagem.habilidadeEspecial` nas subclasses | Padronizar o contrato da habilidade especial e deixar cada personagem implementar sua variação. |

## Observações

- `ItemEffect` é compartilhável porque os efeitos atuais não guardam estado.
- `Inventario.getItens()` retorna cópia defensiva da lista, preservando a lista
  interna do inventário.
- `Batalha` e `Capitulos` aparecem no diagrama para mostrar como o domínio é
  usado no fluxo principal do jogo.
