# ADR-002: Usar Strategy para os efeitos de item

**Status:** Accepted
**Data:** 2026-06-01
**Escopo:** Regras de item e inventário

## Contexto

Itens do inventário podem alterar atributos do personagem, como vida, ataque e
defesa. Exemplos atuais incluem poção de cura, poção superior, tônico de força,
elixir de força e bônus de defesa.

Na versão anterior, a classe `Item` concentrava a lógica dos efeitos por meio de
um `switch` baseado em uma string de efeito. Isso fazia `Item` conhecer todos os
efeitos concretos e obrigava a alteração dessa classe sempre que um novo efeito
fosse criado.

## Problema

A lógica de item violava o princípio Open-Closed Principle (OCP): a classe estava
aberta para modificação sempre que o jogo precisasse de um novo comportamento de
efeito. Além disso, a classe `Item` misturava responsabilidades de identidade,
quantidade, descrição e regra concreta de aplicação.

## Decisão

Foi adotado o padrão **Strategy** para modelar os efeitos de item.

A interface `ItemEffect` define o contrato:

```java
void aplicar(Personagem alvo, String nomeItem);
```

Cada efeito concreto implementa esse contrato em uma classe própria:

- `CuraEffect`;
- `CuraGrandeEffect`;
- `ForcaEffect`;
- `ElixirForcaEffect`;
- `DefesaEffect`;
- `BonusDefesaEffect`.

A classe `Item` mantém uma referência para `ItemEffect` e delega a execução do
efeito ao método `aplicar`. A criação da estratégia concreta fica centralizada
em `EffectFactory`.

## Alternativas Consideradas

**Manter `switch` dentro de `Item`:** reduziria o número de classes, mas manteria
alto acoplamento e crescimento contínuo da classe.

**Usar `enum` com comportamento:** permitiria concentrar os efeitos em uma enum,
mas ainda exigiria modificar a enum a cada novo efeito e misturaria chaves de
configuração com regra de domínio.

**Usar lambdas ou funções anônimas:** seria conciso, mas menos didático para
demonstrar o padrão GoF e menos claro para manutenção por uma equipe iniciante.

## Consequências

**Benefícios:**

- `Item` fica responsável por uso, quantidade e delegação, não pela regra de cada
  efeito;
- novos efeitos podem ser adicionados com uma nova classe e um novo registro na
  fábrica;
- cada efeito pode ser testado ou lido isoladamente;
- o diagrama de classes deixa explícito o uso de Strategy.

**Custos e riscos:**

- há mais classes pequenas no projeto;
- a `EffectFactory` ainda precisa ser atualizada quando uma nova chave de efeito
  for adicionada;
- efeitos muito parecidos, como cura simples e cura grande, podem gerar
  duplicação se o conjunto crescer sem parametrização.

## Relação com o código

Esta decisão aparece nas classes `Item`, `ItemEffect`, implementações de
`model.effect` e `EffectFactory`. O relacionamento está documentado em
`diagrams/classes-gof.md`.
