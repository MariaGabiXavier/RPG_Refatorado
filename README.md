# Shadowhunters RPG - Refatoração Arquitetural

RPG de texto em Java baseado no universo de *The Mortal Instruments*. O jogador
escolhe um Shadowhunter, avança por capítulos narrativos, enfrenta inimigos em
batalhas por turno e usa itens com efeitos estratégicos.

Este repositório documenta uma refatoração arquitetural com foco em:

- monolito modular;
- princípios de Clean Code e SOLID;
- padrões GoF de criação e comportamento;
- documentação por ADRs e diagramas Mermaid.

## Pré-Requisitos

- Java 11 ou superior;
- terminal com suporte a execução de `javac` e `java`;
- nenhuma dependência externa.

## Como Executar

### Windows PowerShell

```powershell
New-Item -ItemType Directory -Force -Path out | Out-Null
Get-ChildItem -Recurse -Filter *.java src | ForEach-Object { $_.FullName } | ForEach-Object { javac -encoding UTF-8 -d out $_ }
java -cp out br.shadowhunters.ShadowhuntersRPG
```

### Linux, macOS ou Git Bash

```bash
find src -name "*.java" -print0 | xargs -0 javac -encoding UTF-8 -d out
java -cp out br.shadowhunters.ShadowhuntersRPG
```

## Estrutura do Projeto

```text
shadowhunters-rpg/
├── README.md
├── adrs/
│   ├── ADR-001-monolito-modular.md
│   ├── ADR-002-strategy-item-effects.md
│   ├── ADR-003-factory-method.md
│   ├── ADR-004-prototype.md
│   └── ADR-005-centralizacao-input.md
├── diagrams/
│   ├── arquitetura-c4-componentes.md
│   ├── classes-gof.md
│   └── sequencia-batalha.md
├── docs/
│   └── Shadowhunters_RPG_Trabalho_Final.pdf
└── src/main/java/br/shadowhunters/
    ├── ShadowhuntersRPG.java
    ├── battle/
    ├── chapter/
    ├── factory/
    ├── model/
    │   └── effect/
    ├── ui/
    └── util/
```

## Arquitetura

O projeto usa **monolito modular**: a aplicação roda em um único processo Java,
mas é organizada por pacotes com responsabilidades separadas.

| Pacote | Responsabilidade |
|---|---|
| `br.shadowhunters` | Ponto de entrada e orquestração inicial do jogo. |
| `chapter` | Capítulos, escolhas narrativas e criação de confrontos. |
| `battle` | Loop de batalha, ações do jogador e evolução pós-vitória. |
| `model` | Personagens, inimigos, inventário e itens. |
| `model.effect` | Estratégias de efeitos aplicáveis por itens. |
| `factory` | Criação centralizada de personagens e efeitos. |
| `ui` | Formatação de saída no console. |
| `util` | Leitura validada de entrada do usuário. |

## Padrões GoF Aplicados

| Padrão | Categoria | Aplicação |
|---|---|---|
| Strategy | Comportamental | `ItemEffect` e classes de efeito como `CuraEffect`, `ForcaEffect` e `DefesaEffect`. |
| Factory Method | Criacional | `PersonagemFactory` e `EffectFactory` centralizam a criação de objetos. |
| Prototype | Criacional | `clone()` abstrato e construtores de cópia em `Personagem`, `Inventario` e `Item`. |
| Template Method | Comportamental | `Personagem.habilidadeEspecial(...)` abstrato, implementado por cada subclasse. |

## Documentação Arquitetural

### ADRs

- [ADR-001: Adotar monolito modular como arquitetura macro](adrs/ADR-001-monolito-modular.md)
- [ADR-002: Usar Strategy para os efeitos de item](adrs/ADR-002-strategy-item-effects.md)
- [ADR-003: Centralizar criação com Factory Method](adrs/ADR-003-factory-method.md)
- [ADR-004: Usar Prototype para clonagem de personagens, inventários e itens](adrs/ADR-004-prototype.md)
- [ADR-005: Centralizar leitura validada em InputUtil](adrs/ADR-005-centralizacao-input.md)

### Diagramas

- [Arquitetura C4 - Componentes](diagrams/arquitetura-c4-componentes.md)
- [Classes - Padrões GoF](diagrams/classes-gof.md)
- [Sequência - Fluxo de Batalha](diagrams/sequencia-batalha.md)

Os diagramas estão em Mermaid dentro de arquivos Markdown. Eles podem ser
renderizados diretamente no GitHub, no VS Code com a extensão
"Markdown Preview Mermaid Support" ou no site [mermaid.live](https://mermaid.live).

## Fluxo do Jogo

1. O jogador escolhe entre Clary, Jace, Isabelle e Alec.
2. O inventário inicial recebe itens básicos de cura e força.
3. `Capitulos` conduz a narrativa e apresenta escolhas.
4. Quando há confronto, `Batalha` executa o loop de turnos.
5. O jogador pode atacar, usar item, usar habilidade especial ou tentar fugir.
6. Ao vencer, o jogador coleta itens do inimigo e melhora seus atributos.
7. A jornada termina no confronto contra Valentine Morgenstern.

## Decisões de Qualidade

- `InputUtil` elimina duplicação de leitura numérica e padroniza validação.
- `Console` centraliza códigos ANSI e evita literais de formatação espalhados.
- `Item` delega efeitos para `ItemEffect` e sinaliza falta de estoque via `IllegalStateException`.
- `Inventario` trata erros de uso, empilha itens equivalentes e usa cópia defensiva ao expor a lista.
- `Personagem` centraliza comportamento comum, `equals`, `hashCode` e `toString`.
- `Inimigo.NOME_CHEFE_FINAL` elimina a magic string `"Valentine Morgenstern"` duplicada no código.

## Observações

- O projeto não usa Maven ou Gradle; a compilação é feita diretamente com `javac`.
- A pasta `out/`, quando criada, contém apenas classes compiladas e pode ser ignorada pelo controle de versão.
