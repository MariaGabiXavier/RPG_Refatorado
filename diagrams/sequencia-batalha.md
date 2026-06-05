# Diagrama de Sequência - Fluxo de Batalha

Este diagrama descreve o fluxo principal de uma batalha iniciada por um capítulo.
Ele foca nas interações entre narrativa, combate, personagem, inimigo, inventário
e estratégias de item.

```mermaid
sequenceDiagram
    actor Jogador
    participant Capitulos
    participant Batalha
    participant InputUtil
    participant Personagem
    participant Inimigo
    participant Inventario
    participant Item
    participant ItemEffect

    Capitulos->>Batalha: new Batalha(jogador, inimigo, dado, sc)
    Capitulos->>Batalha: executar()

    loop Enquanto jogador e inimigo estão vivos
        Batalha->>Personagem: estaVivo()
        Batalha->>Inimigo: estaVivo()
        Batalha-->>Jogador: exibe status e menu de ações
        Jogador->>Batalha: informa ação escolhida
        Batalha->>InputUtil: lerIntervalo(sc, 1, 4)
        InputUtil-->>Batalha: ação válida

        alt Ação 1 - Atacar
            Batalha->>Batalha: rolar dados e calcular dano
            Batalha->>Inimigo: receberDano(danoAoInimigo)
            Batalha->>Personagem: receberDano(danoAoJogador)
        else Ação 2 - Usar item
            Batalha->>Inventario: usarItemPorNumero(sc, jogador)
            Inventario-->>Jogador: exibe itens disponíveis
            Jogador->>Inventario: escolhe item
            Inventario->>InputUtil: lerIntervalo(sc, 0, itens.size())
            InputUtil-->>Inventario: item válido ou cancelamento
            opt Item escolhido
                Inventario->>Item: usar(jogador)
                Item->>ItemEffect: aplicar(jogador, nomeItem)
            end
        else Ação 3 - Habilidade especial
            Batalha->>Personagem: habilidadeEspecial(dado, inimigo)
        else Ação 4 - Fugir
            Batalha->>Batalha: tentarFuga()
            opt Fuga bloqueada
                Batalha->>Personagem: receberDano(danoExtra)
            end
        end

        opt Inimigo vivo e sorteio menor que 15%
            Batalha->>Inimigo: habilidadeEspecial(dado, inimigo)
        end
    end

    alt Jogador vivo e inimigo derrotado
        Batalha->>Personagem: absorverInventario(inimigo)
        Batalha->>Personagem: setNivel(nivel + 1)
        Batalha->>Personagem: setAtaque(ataque + 2)
        Batalha->>Personagem: setDefesa(defesa + 1)
        Batalha->>Personagem: setPontosVida(pontosVida + 10)
        Batalha-->>Capitulos: batalha encerrada com vitória
    else Jogador derrotado
        Batalha-->>Capitulos: batalha encerrada com derrota
    end
```

## Regras Documentadas

- A leitura da ação do jogador passa por `InputUtil.lerIntervalo`.
- O ataque comum calcula dano com atributo de ataque, defesa do alvo e rolagem de
  dado.
- O uso de item passa por `Inventario`, `Item` e a estratégia `ItemEffect`.
- A habilidade especial é polimórfica: cada personagem implementa sua própria
  regra.
- Ao vencer, o jogador absorve itens do inimigo e recebe incremento de nível,
  ataque, defesa e pontos de vida.
