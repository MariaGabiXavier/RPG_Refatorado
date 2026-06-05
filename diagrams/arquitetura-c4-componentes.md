# Diagrama de Arquitetura C4 - Componentes

Este diagrama representa o nível de componentes do monolito modular. O sistema
roda como uma única aplicação Java de console, mas as responsabilidades internas
ficam separadas por pacotes.

```mermaid
flowchart TB
    jogador["Jogador\nInterage pelo terminal"]

    subgraph app["Shadowhunters RPG\nAplicação Java de console"]
        main["ShadowhuntersRPG\nPonto de entrada\nMenu, inicialização e capítulos"]
        capitulos["chapter.Capitulos\nNarrativa, escolhas e eventos"]
        batalha["battle.Batalha\nLoop de combate turno a turno"]
        personagemFactory["factory.PersonagemFactory\nCriação de personagens jogáveis"]
        effectFactory["factory.EffectFactory\nCriação de efeitos por chave"]
        console["ui.Console\nFormatação ANSI de saída"]
        input["util.InputUtil\nLeitura validada de opções"]

        subgraph dominio["model\nDomínio do jogo"]
            personagem["Personagem\nAtributos, vida, ataque, defesa e habilidade"]
            inimigo["Inimigo\nPersonagem controlado pelo sistema"]
            inventario["Inventario\nColeção e uso de itens"]
            item["Item\nNome, descrição, quantidade e efeito"]
        end

        subgraph efeitos["model.effect\nStrategy de efeitos"]
            itemEffect["ItemEffect\nContrato de aplicação"]
            efeitosConcretos["CuraEffect, ForcaEffect,\nDefesaEffect e variações"]
        end
    end

    jogador -->|"digita escolhas"| main
    main -->|"solicita opção"| input
    main -->|"exibe textos"| console
    main -->|"cria personagem"| personagemFactory
    main -->|"executa"| capitulos

    capitulos -->|"solicita escolhas"| input
    capitulos -->|"exibe narrativa"| console
    capitulos -->|"inicia batalhas"| batalha
    capitulos -->|"cria inimigos"| inimigo
    capitulos -->|"adiciona recompensas"| item

    batalha -->|"solicita ação"| input
    batalha -->|"exibe turno"| console
    batalha -->|"consulta e altera estado"| personagem
    batalha -->|"consulta e altera estado"| inimigo
    batalha -->|"usa itens"| inventario

    personagemFactory -->|"retorna Clary, Jace, Isabelle ou Alec"| personagem
    inimigo -.->|"especializa"| personagem
    personagem -->|"possui"| inventario
    inventario -->|"contém"| item

    item -->|"resolve chave de efeito"| effectFactory
    effectFactory -->|"instancia"| efeitosConcretos
    efeitosConcretos -.->|"implementa"| itemEffect
    item -->|"delegação Strategy"| itemEffect
```

## Leitura do Diagrama

- `ShadowhuntersRPG` não contém regras de batalha nem regras de item; ele apenas
  coordena o início do jogo e a sequência dos capítulos.
- `Capitulos` concentra a narrativa e cria batalhas quando uma escolha leva a um
  confronto.
- `Batalha` coordena turnos, ações do jogador, dano, habilidades e encerramento
  do combate.
- `Item` delega comportamento para `ItemEffect`, evitando `switch` de efeitos
  dentro da entidade.
- `InputUtil` padroniza a leitura numérica em todos os fluxos.

## ADRs Relacionadas

- `ADR-001`: monolito modular;
- `ADR-002`: Strategy para efeitos de item;
- `ADR-003`: Factory Method para criação;
- `ADR-005`: centralização da entrada em `InputUtil`.
