Neste diretório estão exemplos relacionados ao mecanismo para tratamento de exceções
presente na linguagem Java. São três subdiretórios, cada um referente a um cenário
distinto:

* media:

  Exemplos envolvendo um programa que deve ler 3 notas e realizar o cálculo da nota
  média. São apresentadas 3 versões que ilustram diferentes abordagens possíveis para
  a validação das notas lidas, partindo de uma solução mais rudimentar (sem empregar
  o mecanismo para tratamento de exceçoes) até chegar a uma solução mais elegante,
  (que faz uso do mecanismo).

* divzero:

  Exemplos envolvendo um programa que deve ler dois valores a e b, e calcular o valor
  de a/b. Também são apresentadas algumas versões (indo das mais rudimentares para
  as mais elaboradas) que ilustram formas possíveis de lidar com o potencial problema
  de uma divisão por zero. As versões mais sotisticadas relacionadas a este cenário 
  também considera problemas que podem ocorrer durante a leitura dos valores a e b, e
  como o mecanismo para tratamento de exceções permite "customizar" o tratamento de
  erro conforme a natureza do mesmo.

* finally:

  Exemplo que ilustra a utilização do bloco finally, que *garante* que a execução de
  um bloco de código independente do que aconteça no bloco try associado.

Não deixem de olhar os códigos referentes aos exemplos e lerem os comentários, eles
contem informações importantes e mais detalhadas sobre o funcionamento do mecanismo 
para tratamento de exceções presente no Java.

Pontos importantes:

- benefícios introduzidos pelo mecanismo.
- uso do throw e throws.
- uso dos blocos try/catch/finally.
- como lançamento de uma exceção afeta o fluxo de execução de um programa.
- diferenças entre checked e unchecked exceptions (verificadas e não-verificadas).
- como criar minha própria classe de exceção.

