# ADR-003: Centralizar criação com Factory Method

**Status:** Accepted
**Data:** 2026-06-01
**Escopo:** Criação de personagens e efeitos

## Contexto

O jogo precisa criar personagens jogáveis a partir da opção escolhida pelo
jogador e também precisa transformar chaves textuais de itens em estratégias de
efeito concretas.

Sem uma fábrica, o ponto de entrada do jogo e a classe `Item` precisariam conhecer
diretamente as classes concretas (`Clary`, `Jace`, `Isabelle`, `Alec`,
`CuraEffect`, `ForcaEffect` etc.). Isso espalharia regras de instanciação e
condições `switch` pela aplicação.

## Problema

Era necessário reduzir o acoplamento entre o código cliente e os tipos concretos,
mantendo a criação de objetos em pontos previsíveis. A escolha também precisava
ser simples o bastante para um projeto sem container de injeção de dependência e
sem dependências externas.

## Decisão

Foi adotada uma fábrica central para cada família de objetos:

- `PersonagemFactory.criar(int opcao)` cria o personagem jogável selecionado;
- `EffectFactory.criar(String chave)` cria a estratégia de efeito associada ao
  item.

No contexto do trabalho, essa solução é registrada como **Factory Method**, pois
centraliza o método responsável pela criação e faz o código cliente trabalhar com
abstrações (`Personagem` e `ItemEffect`) em vez de depender diretamente das
classes concretas.

## Alternativas Consideradas

**Instanciação direta no código cliente:** deixaria o fluxo mais curto, mas faria
`ShadowhuntersRPG` e `Item` conhecerem todos os tipos concretos.

**Mapeamento com `Map<String, Supplier<ItemEffect>>`:** facilitaria extensão por
registro, mas adicionaria uma estrutura menos familiar para o escopo acadêmico.

**Injeção de dependência com framework:** seria excessivo para uma aplicação Java
de console sem dependências externas.

## Consequências

**Benefícios:**

- criação de personagens e efeitos fica localizada;
- `ShadowhuntersRPG` depende de `Personagem`, não de cada subclasse concreta;
- `Item` depende de `ItemEffect`, não das implementações específicas;
- adicionar personagem ou efeito exige mudança pequena e previsível.

**Custos e riscos:**

- as fábricas ainda possuem `switch`, mas o acoplamento fica isolado nelas;
- cada novo tipo concreto precisa ser registrado manualmente;
- o uso de métodos estáticos simplifica o projeto, mas reduz flexibilidade para
  substituição das fábricas em testes mais avançados.

## Relação com o código

Esta decisão aparece em `PersonagemFactory`, `EffectFactory`,
`ShadowhuntersRPG.criarPersonagem` e no construtor de `Item`.
