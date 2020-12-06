# Transaction Status
## Aplicação SpringBoot que insere, exclui e mostra dados estatísticos sobre transações.

### Pré-requisitos
  * Java 8+.
  * Maven 3.2+
  * Java IDE (Eclipse, IntelliJ, etc.)

### Features

* Inserção de transações
  * Aceessando o endpoint: http://localhost:8080/api/transacao / POST, é possível incluir uma nova transação, que é enviada no formato JSON e servirá de entrada para os calculos estatísticos. Caso a inserção seja realizada com exito, é retornado um JSON com os dados da transação que foi inserida, e a resposta da requisição terá o status 201.
* Exclusão de transações
  * Acessando o endpoint: http://localhost:8080/api/transacao / DELETE, é possível deletar todas as transações. Caso a exclusão seja realizada com exito, a resposta da requisição terá o status 200.
* Listagem de estatisticas sobre as transações
  * Acessando o endpoint: http://localhost:8080/api/transacoes / GET, é possível visualizar dados estatísticos das ultimas transações inseridas. É possível enviar um parametro de requisição para retornar apenas as transações que aconteceram nos ultimos x segundos. Como resposta, retorna-se um json que possui os campos: count = quantidade de transações, sum = somatória dos valores das transações, avg = média dos valores das transações, min = valor mínimo dentre as transações e max = valor máximo dentre as transações.

### Observabilidade

* Actuator
  * Acessando o endpoint: http://localhost:8080/actuator/health / GET é possível verificar se o servidor está em execução através de um objeto JSON que indica o status da aplicação.
  ![actuator](https://user-images.githubusercontent.com/32377593/101293084-32deaf00-37f2-11eb-805d-ddc3ed7d5d85.jpg)
  
### Logs
  * Todas as rotinas da aplicação geram logs, que podem ser visualizados no console da aplicação, e servem para nortear os desenvolvedores para resolver algum eventual problema que possa aparecer.
![Logs](https://user-images.githubusercontent.com/32377593/101292984-974d3e80-37f1-11eb-95c3-6d58add08931.png)

### Tratamento de erros
  * Durante a inserção da transação, caso a transação informada não passe em algum dos critérios de validação, gera-se uma exceção e o status retornado pela API é o 422 indicando que há alguma irregularidade na transação. Também retorna-se um objeto JSON que possui o campo que está inválido, e a irregularidade.
![excessao](https://user-images.githubusercontent.com/32377593/101293989-2c9d0280-37f3-11eb-8c5d-bd9eff09fbab.jpg)
Ex: tentando inserir uma transação no futuro.

### Documentação da API
  * Swagger
    * Acessando o endpoint: http://localhost:8080//swagger-ui.html#/transacao-controller através do Browser, tem-se acesso a documentação da API, que foi escrita utilizando Swagger. Através dela é possível visualizar informações sobre os endpoints desenvolvidos, como: parametros da requisição, formado do retorno dos endpoints, possíveis status de retorno de acordo com as validações, etc. 
![swagger1](https://user-images.githubusercontent.com/32377593/101294148-3e32da00-37f4-11eb-9cbc-e6d695d9f129.jpg)
![swagger2](https://user-images.githubusercontent.com/32377593/101294150-3ffc9d80-37f4-11eb-8dcf-d3a29ebf4c19.jpg)

### Testes automatizados
  * JUnit
    Utilizando o JUnit na camada de testes da aplicação, é possível realizar os testes automatizados dos seguintes cenários ao inserir uma nova transação:
    * Transação sem erros de validação;
    * Transação com data no futuro;
    * Transação com valor monetário negativo;
    * Transação sem corpo (objeto nulo), sem permitir que seja identificado o corpo da requisição;
    
  ![image](https://user-images.githubusercontent.com/32377593/101294340-6a9b2600-37f5-11eb-9837-613f9803b55f.png)
  
  Console da aplicação após executar os testes automatizados. O exemplo mostrado refere-se ao cenário de tentar inserir uma transação com data no futuro.
  Após o teste, a aplicação informa que o status de retorno esperado para aquela requisição seria o 201 (que representaria a transação criada), porém o retornado foi o 422, indicando a irregularidade, e caracterizando uma falha no teste.
