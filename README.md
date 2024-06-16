# Hunt the Wumpus

---
## Visão Geral

Jogo Hunt the Wumpus feito em Java, para terminal, seguindo as descrições e requisitos descritos em [Trabalho de Estrutura de Dados - Wumpus.pdf](Trabalho%20de%20Estrutura%20de%20Dados%20-%20Wumpus.pdf)

---
## Estrutura de Dados

A implementação se baseia na estrutura de dados em grafos, sendo a classe [Caverna](src/main/java/maze/Cave.java) o nó, que permite a conexão com até quatro outras Cavernas.

O grafo é construído no método generate(), da classe [Maze](src/main/java/maze/Maze.java). \
Em resumo a lógica utilizada é:
 - Quando um objeto [Maze](src/main/java/maze/Maze.java) é instanciado, uma Caverna inicial/root é criada;
 - Após a instanciação é necessário chamar o método generate(), que:
   - Cria uma Lista de cavernas e adiciona a root nela;
   - Obtém um inteiro aleatório entre 20 e 30, para definir a quantidade limite de Cavernas do labirinto;
   - Inicia uma estrutura de repetição, será executada enquanto o número de Cavernas for menor que o limite, que:
     - Define uma Caverna para iteração, removendo-a da Lista.
     - Obtém um inteiro aleatório entre 1 e 4, para definir quantas tentativas de inserção de cavernas.
     - Inicia uma estrutura de repetição de inserção que será executada o número de vezes obtido na etapa anterior, que:
        - Obtém um inteiro entre 0 e 3, para definir uma direção que será feito a tentativa de criar e inserir uma caverna 'filho' para a Caverna da iteração atual;
        - [Se bem sucedido] A Caverna inserida é adicionada na Lista, para ser utilizada em uma próxima iteração futura (após encerrar essa estrutura de inserção);
        - [Se bem sucedido] A Caverna inserida é colocada em um Map de cavernas, com as coordenadas (x, y), que serão utilizadas para montar visualmente o mapa, na classe [VisualMaze](src/main/java/maze/VisualMaze.java)

---
## Regras Implementadas
### Especificadas no documento [Trabalho de Estrutura de Dados - Wumpus.pdf](Trabalho%20de%20Estrutura%20de%20Dados%20-%20Wumpus.pdf)
 - As Cavernas podem ter até quatro conexões (LESTE, OESTE, NORTE e SUL), sendo que sempre haverá ao menos uma;
 - As Cavernas são geradas de forma aleatória, seguindo a lógica explicada na seção [Estrutura de Dados](#estrutura-de-dados);
 - Os inimigos são posicionados de forma aleatória, através do método addEnemies(), na classe [Maze](src/main/java/maze/Maze.java);
 - Conforme o Jogador se movimenta, dicas são exibidas referente as cavernas ao redor, para indicar a presença de inimigos, por exemplo;
 - A única condição de vitória é atirar uma flecha no Wumpus.

### Decisões não especificadas
 - Os Morcegos podem te jogar na caverna do Wumpus ou dos Poços sem fundo (encerrando a partida);
 - Os Morcegos retornam para caverna que foram encontrados após transportarem o Jogador;
 - Foram criados quatro modos de jogo [GameModes](src/main/java/enums):
   - **FULL_MAP** - todas as cavernas 'C' e conexões '|' ou '-' são exibidas desde o início, inimigos são exibidos quando passar por ele (na prática somente o morcego 'M' pode ser visto no mapa, pois os demais encerram a partida).
   - **PATH_MAP** - as cavernas, conexões e inimigos são exibidos a medida que o Jogador percorre o labirinto.
   - **WITHOUT_MAP** - nenhum mapa é exibido, somente os textos.
   - **WALL_HACK** - tudo é exibido desde o início, incluindo a posição das flechas e inimigos (criado mais para testes)
 - Nos modos onde é exibido o mapa no terminal, o caminho percorrido pelo player é destacado na cor ciano;
 - O mapa no terminal possuem os seguintes elementos visuais:
   - **C** - indica uma Caverna não visitada (cor padrão) ou vazia após visitada (ciano);
   - **|** - indica um caminho para norte/sul não percorrido (cor padrão) ou percorrido (ciano);
   - **\-** - indica um caminho para leste/oeste não percorrido (cor padrão) ou percorrido (ciano);
   - **J** - indica a posição do Jogador (amarelo);
   - **M** - indica um Morcego (roxo);
   - **P** - indica um Poço sem fundo (azul) [visível somente no modo "Eu vejo de tudo!"];
   - **W** - indica o Wumpus (vermelho) [visível somente no modo "Eu vejo de tudo!"];
   - **F** - indica uma flecha (verde) [visível somente no modo "Eu vejo de tudo!"];
 - Os elementos na caverna poderiam ser simplificados para enum, por exemplo, mas como a documentação exigia o uso do atributo inimigo na caverna e que o morcego, poço e wumpus herdassem dessa classe, no fim essas classes ficaram vazias.
 - Para reutilizar a lógica de validação de uma caverna e ação com base no inimigo quando o jogador chega nela, a flecha herdou da classe Enemy.