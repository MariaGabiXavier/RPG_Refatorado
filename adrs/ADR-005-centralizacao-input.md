# ADR-005: Centralizar leitura validada em InputUtil

**Status:** Accepted
**Data:** 2026-06-01
**Escopo:** Entrada de dados no console

## Contexto

O jogo lê várias escolhas numéricas do jogador: seleção de personagem, decisões
de capítulo, ações de batalha, uso de itens e preparação para a batalha final.

Na versão anterior, a leitura de inteiros dentro de um intervalo estava duplicada
em `Batalha`, `Capitulos` e `ShadowhuntersRPG`. As cópias tinham pequenas
diferenças de tratamento do `Scanner`, o que poderia gerar comportamento
inconsistente diante de entradas inválidas.

## Problema

A duplicação da leitura de entrada violava DRY e aumentava o custo de manutenção.
Qualquer ajuste na validação de intervalo, no tratamento de `InputMismatchException`
ou na limpeza do token inválido teria que ser replicado em vários pontos.

## Decisão Inicial Revertida

Inicialmente foi considerada a manutenção de métodos locais de leitura, com a
justificativa de que cada contexto do jogo poderia ter mensagens ou necessidades
próprias. Essa decisão foi revertida porque as diferenças observadas eram
acidentais, não regras reais de negócio.

## Decisão

Foi criada a classe utilitária `InputUtil`, com o método:

```java
InputUtil.lerIntervalo(Scanner sc, int min, int max)
```

Todo ponto do jogo que precisa de uma opção numérica validada passa a usar esse
método. A implementação fica responsável por:

- ler um inteiro do `Scanner`;
- verificar se o valor está no intervalo fechado `[min, max]`;
- solicitar nova entrada quando o valor estiver fora do intervalo;
- capturar `InputMismatchException` e consumir o token inválido.

## Alternativas Consideradas

**Manter métodos duplicados:** reduziria uma classe no projeto, mas manteria
inconsistência e retrabalho.

**Colocar leitura dentro de `Console`:** misturaria saída formatada com entrada
validada. `Console` permanece focado em apresentação.

**Criar uma camada completa de UI:** seria excessivo para o escopo atual; o
utilitário resolve o problema sem reorganizar o jogo inteiro.

## Consequências

**Benefícios:**

- comportamento único para leitura de opções numéricas;
- remoção de duplicação em classes de fluxo;
- manutenção simples caso a regra de validação precise mudar;
- documentação clara de uma decisão de Clean Code aplicada ao projeto.

**Custos e riscos:**

- `InputUtil` é uma classe utilitária estática, então deve continuar pequena e
  focada em entrada;
- mensagens de erro ficam padronizadas, com menor flexibilidade de texto por
  contexto;
- chamadas que precisem consumir quebras de linha específicas ainda devem tratar
  esse detalhe no fluxo chamador quando necessário.

## Relação com o código

Esta decisão aparece em `InputUtil`, `ShadowhuntersRPG`, `Capitulos`, `Batalha` e
`Inventario`. O fluxo de leitura também aparece nos diagramas de arquitetura e de
sequência.
