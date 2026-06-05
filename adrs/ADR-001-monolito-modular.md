# ADR-001: Adotar monolito modular como arquitetura macro

**Status:** Accepted
**Data:** 2026-06-01
**Escopo:** Arquitetura da aplicação

## Contexto

O Shadowhunters RPG é um jogo de texto em Java executado localmente pelo terminal.
O jogo é single-player, mantém todo o estado em memória e não possui requisitos de
deploy distribuído, banco de dados, APIs externas ou comunicação em rede.

A aplicação precisa organizar três grupos principais de responsabilidade:

- fluxo de execução do jogo, incluindo menu inicial e avanço dos capítulos;
- regras de domínio, como personagens, inimigos, inventário, itens e efeitos;
- interação com o usuário pelo console, incluindo leitura validada e saída formatada.

Como o projeto é acadêmico e de pequeno porte, a arquitetura deve ser simples de
compilar, executar e explicar, mas ainda precisa demonstrar separação de
responsabilidades, coesão e baixo acoplamento.

## Problema

Era necessário escolher uma arquitetura que evitasse concentrar toda a lógica em
uma única classe principal sem introduzir complexidade desnecessária. A solução
também precisava deixar explícito onde cada decisão de design foi aplicada, para
facilitar a documentação dos padrões GoF e dos princípios de Clean Code/SOLID.

## Decisão

Foi adotado o estilo **monolito modular**, com a aplicação rodando em um único
processo Java e com responsabilidades separadas por pacotes:

- `br.shadowhunters`: ponto de entrada da aplicação;
- `br.shadowhunters.chapter`: narrativa, escolhas e progressão dos capítulos;
- `br.shadowhunters.battle`: loop de batalha e regras de turno;
- `br.shadowhunters.model`: entidades de domínio do jogo;
- `br.shadowhunters.model.effect`: estratégias de efeito de item;
- `br.shadowhunters.factory`: criação centralizada de personagens e efeitos;
- `br.shadowhunters.ui`: formatação de saída no console;
- `br.shadowhunters.util`: utilitários compartilhados de entrada.

O ponto de entrada (`ShadowhuntersRPG`) apenas inicializa recursos, seleciona o
personagem e orquestra a execução dos capítulos. As regras de combate ficam em
`Batalha`, a narrativa fica em `Capitulos`, e o domínio fica concentrado em
`Personagem`, `Inimigo`, `Inventario`, `Item` e classes relacionadas.

## Alternativas Consideradas

**Monolito sem modularização:** seria o caminho mais simples inicialmente, mas
manteria menu, narrativa, batalha, itens e personagens acoplados nas mesmas
classes. Isso dificultaria manutenção e explicação arquitetural.

**Arquitetura em camadas clássica:** separaria apresentação, serviço e domínio,
mas criaria camadas artificiais para uma aplicação sem persistência, sem API e
sem múltiplos adaptadores de entrada.

**Microsserviços ou arquitetura distribuída:** não se justifica para um jogo de
console local. A solução adicionaria latência, infraestrutura e protocolos sem
benefício para o escopo do projeto.

## Consequências

**Benefícios:**

- execução simples com `javac` e `java`, sem dependências externas;
- separação clara entre narrativa, batalha, domínio, criação de objetos e UI;
- manutenção mais localizada quando novos personagens, itens ou capítulos forem
  adicionados;
- aderência ao SRP, pois cada pacote concentra um tipo de responsabilidade.

**Custos e riscos:**

- o jogo continua sendo uma única aplicação; se evoluir para multiplayer online,
  persistência remota ou API pública, a arquitetura precisará ser revista;
- a separação por pacotes depende de disciplina da equipe, pois Java não impede
  automaticamente todos os acoplamentos indesejados entre módulos internos;
- algumas classes ainda coordenam fluxos longos, especialmente `Capitulos`, por
  causa da natureza narrativa do jogo.

## Relação com a documentação

Esta decisão é representada no diagrama `diagrams/arquitetura-c4-componentes.md`
e fundamenta as demais ADRs, que detalham decisões internas de criação de objetos,
efeitos de item, clonagem e leitura de entrada.
