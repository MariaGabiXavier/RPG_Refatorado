# ADR-004: Usar Prototype para clonagem de personagens, inventários e itens

**Status:** Accepted
**Data:** 2026-06-01
**Escopo:** Cópia de objetos de domínio

## Contexto

Personagens possuem um `Inventario`, e o inventário contém uma lista de `Item`.
Itens são objetos mutáveis porque sua quantidade muda conforme são usados ou
empilhados. Isso cria risco de compartilhamento acidental de estado quando um
personagem, inventário ou item precisa ser copiado.

O próprio fluxo do jogo também transfere itens de inimigos derrotados para o
jogador. Por isso, a semântica de cópia precisa ser explícita e previsível.

## Problema

Uma cópia rasa de `Personagem` compartilharia o mesmo `Inventario`; uma cópia rasa
de `Inventario` compartilharia os mesmos `Item`. Nesse cenário, alterar a
quantidade de um item no clone poderia modificar o objeto original.

Era necessário garantir **deep copy** das estruturas mutáveis sem expor os detalhes
de cópia para o código cliente.

## Decisão

Foi adotado o padrão **Prototype** com `Cloneable`, métodos `clone()` e
construtores de cópia.

A semântica aplicada é:

- `Item.clone()` cria um novo item com mesma identidade, descrição, efeito e
  quantidade;
- `Inventario.clone()` cria um novo inventário e clona cada item da lista;
- `Personagem.clone()` cria uma cópia do personagem, incluindo uma cópia profunda
  do inventário;
- subclasses como `Clary`, `Jace`, `Isabelle`, `Alec` e `Inimigo` possuem
  construtores de cópia e sobrescrevem `clone()`.

Os efeitos de item são compartilhados entre cópias porque as implementações atuais
não mantêm estado interno; elas apenas aplicam alterações no alvo recebido.

## Alternativas Consideradas

**Cópia rasa:** seria simples, mas incorreta para inventários e itens mutáveis.

**Métodos manuais como `copiarPersonagem`:** funcionariam, mas espalhariam a
responsabilidade de cópia para fora das próprias classes.

**Serialização para copiar objetos:** seria mais pesada e desnecessária para um
modelo pequeno e em memória.

## Consequências

**Benefícios:**

- o código cliente pode copiar objetos chamando `clone()`;
- cada classe controla sua própria regra de cópia;
- o inventário clonado não compartilha a lista nem os itens com o original;
- a decisão demonstra um padrão GoF de criação aplicado a um problema concreto.

**Custos e riscos:**

- sempre que um novo campo mutável for adicionado, o construtor de cópia precisa
  ser atualizado;
- `Cloneable` exige disciplina, pois não força uma cópia profunda por contrato;
- o compartilhamento de `ItemEffect` permanece correto apenas enquanto os efeitos
  continuarem sem estado interno.

## Relação com o código

Esta decisão aparece em `Personagem`, subclasses de personagem, `Inimigo`,
`Inventario` e `Item`. O relacionamento está representado em
`diagrams/classes-gof.md`.
