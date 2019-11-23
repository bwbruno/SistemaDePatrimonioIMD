# Introdução
  ## Breve descrição do problema abordado
  ## Descrição do que será apresentado no relatório

# Abordagem e solução do problema
  ## Descrever o projeto OO (diagramas de classes)

  ## Destacar decisões de projeto tomada
  - Porque escolhemos trabalhar com o padrão MVC?
  - Escolhemos trabalhar com o padrão MVC porque é uma maneira conhecida, além do JavaFX demandar esse modelo.

# Estrutura de dados utilizadas
  - HashMap é melhor para pesquisar, porém, é mais custoso para inserir e remover.
  - Usamos Set para armazenar os próximos códigos porque, ao inserir os elementos, a lista é ordenada automaticamente. Além de não haver possibilidade de existir elementos duplicados, o que é essencial quando estamos falando de índices.
  - Lógica ao pegar um código no Set proximoCodigo:
      -- O código é removido do Set e depois é adicionado no conjunto novamente. Dessa vez, somado mais um.
      -- Caso um dos elementos seja apagado da tabela, o código apagado é adicionado no Set proximoCodigo.

# Requisitos
  - Não deixar apagar uma localização ou categoria enquanto houver bens que as utilizam.
  - Criar bem só se existir pelo menos uma localização e uma categoria.

# Como foram implementados os requisitos funcionais

# Conclusão

# Referências

[Java 8 Concurrency Tutorial: Threads and Executors](https://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/)
[How to Run Multiple Threads Concurrently in Java? ExecutorService Approach](https://crunchify.com/how-to-run-multiple-threads-concurrently-in-java-executorservice-approach/)
https://github.com/DenisIzmaylov/awesome-telegram-bots
https://www.youtube.com/watch?v=EDn9g4j6wkw
https://stackoverflow.com/questions/12365871/java-threads-for-two-while-loops