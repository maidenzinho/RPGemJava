# RPGemJava
Colaboradores: Maria Eduarda Zampieron e Felipe Fernandes Bortolino (PjBL 6, Cibersegurança 2025)

## » Descrição do Projeto
O projeto consiste em um RPG em turnos desenvolvido totalmente em Java, no qual o jogador (usuário) controla personagens que enfrentam inimigos em batalhas estratégicas, alternando entre ataques do jogador e do inimigo.
## » Contexto
A ideia surgiu com o objetivo de aplicar os principais conceitos de Programação Orientada a Objetos (POO) em Java de forma prática, proporcionando uma experiência interativa. No contexto educacional, o jogo servirá como base para demonstrar encapsulamento, herança, polimorfismo, abstração e uso de coleções, entre outros.
## » Implementação
O sistema contará com uma interface gráfica, leitura e gravação de arquivos, além de salvar o progresso do jogo. O projeto também terá múltiplas classes e métodos, incluindo personagens, itens, batalhas e gerenciamento de turnos.
## » Estrutura
```
RPG/
├── Main.java                      // Classe principal
├── Game.java                      // Controla a lógica geral do jogo
├── interface/
│   └── GameUI.java                // Interface gráfica do jogo
├── personagem/
│   ├── Personagem.java            // Classe abstrata base
│   ├── Guerreiro.java             // Subclasse concreta
│   ├── Mago.java                  // Subclasse concreta
│   └── InvalidoException.java     // Exceção personalizada
├── mundo/
│   ├── Local.java                 // Representa um lugar no jogo
│   ├── Cidade.java                // Subclasse de Local
│   └── Floresta.java             // Subclasse de Local
├── combate/
│   ├── Inimigo.java               // Representa inimigos
│   └── Combate.java               // Controla batalhas
├── item/
│   ├── Item.java                  // Representa um item
│   └── Inventario.java            // Armazena itens
├── dados/
│   ├── LeitorDados.java           // Lê personagens e mapas de CSV
│   └── GravadorDados.java         // Salva progresso em arquivo

```
## » Como compilar
```
javac */*.java *.java
java Main
```